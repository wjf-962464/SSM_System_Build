package com.dc.service.impl;

import com.dc.base.pojo.BaseModel;
import com.dc.base.util.PageList;
import com.dc.base.util.TimeUtil;
import com.dc.dao.WpParkDao;
import com.dc.dao.WpSpotDao;
import com.dc.dao.WpSpotOrderDao;
import com.dc.pojo.WpPark;
import com.dc.pojo.WpSpot;
import com.dc.pojo.WpSpotOrder;
import com.dc.pojo.WpSpotOrderDetail;
import com.dc.service.UserService;
import com.dc.service.WpSpotOrderService;
import com.dc.service.WpSpotService;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

@Service
public class WpSpotOrderImpl implements WpSpotOrderService {
    @Autowired
    private WpParkDao parkDao;
    @Autowired
    private WpSpotOrderDao orderDao;
    @Autowired
    private WpSpotDao spotDao;
    @Autowired
    private WpSpotService spotService;
    private final int OVER_TIME=15;
    private Timer timer;

    @Override
    public void addSpotOrder(final int user_id, int park_id, final BaseModel model) throws Exception {
        if (parkDao.findParkById(park_id) == null) {
            model.setFailure("不存在此停车场");
            return;
        }
        //先查询是否有预约
        if (orderDao.getIfHaveOrder(user_id) != null) {
            WpSpot spot =spotDao.findSpotById(orderDao.getIfHaveOrder(user_id).getSpot_id());
            model.setFailure("此账户已预约",spot);
            return;
        }
        List<WpSpot> list = spotDao.getFreeSpotList(park_id);
        if (list.size() == 0) {
            model.setFailure("此停车场已无空闲停车位，看看周围别的停车场吧");
            return;
        }
        WpSpot spot = list.get((int) (Math.random() * (list.size() - 1)));
        final int spot_id=spot.getSpot_id();
        orderDao.addOrder(user_id, spot_id);
        spotService.upSpotOccupyStatus(spot_id, 2, model);
        spot = spotDao.findSpotById(spot_id);
        model.setSucceed("成功预约车位", spot);
        int delay=1000*60*OVER_TIME;
        ActionListener taskPerformer=new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    WpSpotOrder order=orderDao.getIfHaveOrder(user_id);
                    if (order.getUser_status()==3)
                        return;
                    spotService.upSpotOccupyStatus(spot_id,0,model);
                    orderDao.overtimeOrder(user_id);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        timer=new Timer(delay,taskPerformer);
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public void getOrderPageList(int user_id, int page, int size, BaseModel model) throws Exception {
        List<WpSpotOrder> list = orderDao.getOrderList(user_id);
        if (list.size() == 0) {
            model.setSucceed("还没有任何订单呢，快去下单吧");
            return;
        }
        List<WpSpotOrderDetail> data = new ArrayList<WpSpotOrderDetail>();
        for (WpSpotOrder order : list) {
            WpSpot spot=spotDao.findSpotById(order.getSpot_id());
            WpPark park=parkDao.findParkById(spot.getPark_id());
            WpSpotOrderDetail orderDetail=new WpSpotOrderDetail(order,spot,park,OVER_TIME);
            data.add(orderDetail);
        }
        PageList.pageQuery(data, page, size, "订单", model);
    }

    @Override
    public void cancelOrder(int user_id, BaseModel model) throws Exception {
        WpSpotOrder order = orderDao.getIfHaveOrder(user_id);
        if (order == null) {
            model.setFailure("该用户尚无预约");
            return;
        }
        orderDao.cancelOrder(user_id);
        spotService.upSpotOccupyStatus(order.getSpot_id(), 0, model);
        model.setSucceed("预约已取消");
    }

    @Override
    public void getOrderInfo(int user_id, BaseModel model) throws Exception {
        WpSpotOrder order = orderDao.getIfHaveOrder(user_id);
        if (order == null) {
            model.setFailure("该用户尚无预约");
            return;
        }
        WpSpot spot=spotDao.findSpotById(order.getSpot_id());
        WpPark park=parkDao.findParkById(spot.getPark_id());
        WpSpotOrderDetail detail=new WpSpotOrderDetail(order,spot,park,OVER_TIME);
        int min = TimeUtil.compareWithNow(order.getCreate_time(), "min");
        int sec = TimeUtil.compareWithNow(order.getCreate_time(), "sec");
        model.setSucceed("距离预约已过去" + sec + "秒",detail);
    }

    @Override
    public void completeOrder(int user_id, BaseModel model) throws Exception {
        WpSpotOrder order=orderDao.getIfHaveOrder(user_id);
        int cost=0;
        if (order==null){
            model.setFailure("您没有预约");
            return;
        }
        if (order.getOrder_status()==0){
            model.setFailure("您没有预约");
            return;
        }
        if (order.getUser_status()!=3){
            model.setMessage("您并没有停车无法取车，车位将在2分钟后回升");
            model.setResultCode(2);
            return;
        }
        spotService.upSpotOccupyStatus(order.getSpot_id(),0,model);
        spotDao.upSpotWordStatus(order.getSpot_id(),1);
        orderDao.completeOrder(user_id);
        model.setSucceed("成功取车，祝您一路顺风",cost);
    }

    @Override
    public void applyOrder(final int user_id, final int spot_id, final BaseModel model) throws Exception {
        WpSpot spot=spotDao.findSpotById(spot_id);
        if (spot==null){
            model.setFailure("非法访问接口");
            return;
        }
        orderDao.addOrder(user_id, spot_id);
        WpSpotOrder order = orderDao.getIfHaveOrder(user_id);
        if (order==null){
            model.setFailure("很抱歉，预约失败");
            return;
        }
        spotService.upSpotOccupyStatus(spot_id, 2, model);
        model.setSucceed("成功预约车位", spot);
        int delay=1000*60*OVER_TIME;
        ActionListener taskPerformer=new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    spotService.upSpotOccupyStatus(spot_id,0,model);
                    orderDao.overtimeOrder(user_id);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        timer=new Timer(delay,taskPerformer);
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public void submitOrder(int user_id, BaseModel model) throws Exception {
        WpSpotOrder order=orderDao.getIfHaveOrder(user_id);
        if (order==null){
            model.setFailure("您没有预约，无法提交订单");
        }
        int cost=-1;
        if (order.getOrder_status()==0){
            cost=10;
            model.setMessage("预约已超时,您需要重新预约");
            model.setResultCode(2);
            return;
        }
        cost=3;//按时间计算
        spotService.upSpotOccupyStatus(order.getSpot_id(),1,model);
        orderDao.submitOrder(user_id);
        model.setSucceed("您的预约已按时抵达",cost);
    }
}
