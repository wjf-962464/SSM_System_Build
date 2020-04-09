package com.dc.service.impl;

import com.dc.base.pojo.BaseModel;
import com.dc.base.util.PageList;
import com.dc.dao.WpParkDao;
import com.dc.dao.WpSpotDao;
import com.dc.dao.WpSpotOrderDao;
import com.dc.pojo.WpPark;
import com.dc.pojo.WpSpot;
import com.dc.pojo.WpSpotList;
import com.dc.pojo.WpSpotOrder;
import com.dc.service.WpParkService;
import com.dc.service.WpSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WpSpotImpl implements WpSpotService {
    @Autowired
    private WpSpotDao spotDao;
    @Autowired
    private WpParkDao parkDao;
    @Autowired
    private WpSpotOrderDao orderDao;

    @Override
    public void getSpotPageList(int park_id, int page, int size, BaseModel model) throws Exception {
        List<WpSpotList> list = spotDao.getSpotList(park_id);
        PageList.pageQuery(list, page, size, "停车位", model);
    }

    @Override
    public void getSpotList(int park_id, BaseModel model) throws Exception {
        List<WpSpotList> list = spotDao.getSpotList(park_id);
        if (parkDao.findParkById(park_id) == null) {
            model.setFailure("不存在此停车场");
            return;
        }
        if (parkDao.findParkById(park_id).getPark_status() == 0) {
            model.setFailure("此停车场已被注销");
            return;
        }
        if (list.size() == 0) {
            model.setFailure("此停车场尚无停车位");
            return;
        }
        model.setSucceed("此停车场共停车位" + list.size() + "个", list);
    }

    @Override
    public void upSpotOccupyStatus(int spot_id, int occupy_status, BaseModel model) throws Exception {
        WpSpot spot = spotDao.findSpotById(spot_id);
        WpPark park = parkDao.findParkById(spot.getPark_id());
        if (spot == null || park == null || park.getPark_status() != 1) {
            model.setMessage("不存在该停车位");
            model.setResultCode(0);
            return;
        }
        if (spot.getWork_status() == 0) {
            model.setMessage("该停车位已被注销");
            model.setResultCode(0);
            return;
        }
        spotDao.upSpotOccupyStatus(spot_id, occupy_status);
        model.setResultCode(1);
        String status = "";
        switch (occupy_status) {
            case 1:
                status = "占用";
                break;
            case 2:
                status = "预约";
                break;
            case 0:
                status = "空闲";
                break;
        }
        String spot_name = "";
        int type = 0;
        if (spot.getSpot_serial().contains("-")) {
            spot_name = spot.getSpot_serial().substring(0, spot.getSpot_serial().indexOf("-"));
            type = 3;
        } else {
            spot_name = spot.getSpot_serial();
            type = 1;
        }
        WpSpotList spotList = spotDao.findSpotList(new WpSpotList(park.getPark_id(), spot_name, type));
        if ((spot.getOccupy_status() == 1 && occupy_status == 0) || (spot.getOccupy_status() == 2 && occupy_status == 0)) {
            parkDao.upRemainSpot(park.getPark_id(), park.getSpot_remain() + 1);
            spotList.setFree_spot(spotList.getFree_spot() + 1);
        }
        if ((spot.getOccupy_status() == 0 && occupy_status == 1) || (spot.getOccupy_status() == 0 && occupy_status == 2)) {
            parkDao.upRemainSpot(park.getPark_id(), park.getSpot_remain() - 1);
            spotList.setFree_spot(spotList.getFree_spot() - 1);
        }
        spotDao.upSpotListFreeSpot(spotList);
        model.setMessage("修改停车位占用状态成功，当前状态为：" + status);
    }

    @Override
    public void addSpot(WpSpot spot, BaseModel model) throws Exception {
        model.setResultCode(1);
        model.setMessage("增加停车位成功");
        model.setData(spot);
        spotDao.addSpot(spot);
        WpPark park = parkDao.findParkById(spot.getPark_id());
        parkDao.upTotalSpot(park.getPark_id(), park.getSpot_total() + 1);
        parkDao.upRemainSpot(park.getPark_id(), park.getSpot_remain() + 1);
    }

    @Override
    public void registerSpot(int park_id, String area, String site, int type, BaseModel model) throws Exception {
        WpPark park = parkDao.findParkById(park_id);
        if (park == null) {
            model.setFailure("找不到该停车场");
            return;
        }
        if (!area.matches("[A-Z]")) {
            model.setFailure("请正确填写停车区域");
            return;
        }
        if (!site.matches("[0-9]*")) {
            model.setFailure("请正确填写停车位编号");
            return;
        }
        if (type != 1 && type != 3) {
            model.setFailure("停车场类型错误");
            return;
        }
        String spot_name = area + site;
        WpSpotList newSpot=new WpSpotList(park_id, spot_name, type);
        if(spotDao.findSpotList(newSpot)!=null){
            model.setFailure("该停车位已注册");
            return;
        }
        if (type == 1) {
            spotDao.addSpotList(newSpot);
            spotDao.addSpot(new WpSpot(park_id, area, site));
            parkDao.upTotalSpot(park.getPark_id(), park.getSpot_total() + type);
            parkDao.upRemainSpot(park.getPark_id(), park.getSpot_remain() + type);
        } else if (type == 3) {
            spotDao.addSpotList(newSpot);
            for (int i = 0; i < 3; i++) {
                spotDao.addSpot(new WpSpot(park_id, area, site, i + 1));
            }
            parkDao.upTotalSpot(park.getPark_id(), park.getSpot_total() + type);
            parkDao.upRemainSpot(park.getPark_id(), park.getSpot_remain() + type);
        }
        model.setSucceed("停车位注册成功");
    }

    @Override
    public void getListDetail(int spot_list_id, BaseModel model) throws Exception {
        WpSpotList spotList=spotDao.findSpotListById(spot_list_id);
        if (spotList==null){
            model.setFailure("不存在此停车点");
            return;
        }
        if (spotList.getStatus()==0){
            model.setFailure("该停车点已被注销");
            return;
        }
        List<WpSpot> data=spotDao.getListDetail(spotList);
        model.setSucceed("查询成功",data);
    }

    @Override
    public void getSpotDetail(int spot_id, BaseModel model) throws Exception {
        WpSpot spot=spotDao.findSpotById(spot_id);
        if (spot==null){
            model.setFailure("不存在此停车位");
            return;
        }
        if (spot.getWork_status()==0){
            model.setFailure("此停车位已故障");
            return;
        }
        model.setSucceed("查询成功",spot);
    }

    @Override
    public void getSpotBelong(int spot_id, int user_id, BaseModel model) throws Exception {
        WpSpot spot=spotDao.findSpotById(spot_id);
        if (spot==null){
            model.setFailure("非法查询接口");
            return;
        }
        WpSpotOrder order=orderDao.getIfHaveOrder(user_id);
        if (order.getSpot_id()!=spot_id){
            model.setSucceed("该车位已被预约或占用，但不是您的车位");
            model.setResultCode(2);
            return;
        }
        model.setSucceed("这是您的车位");
    }

    @Override
    public void spotRise(int spot_id, int user_id, BaseModel model) throws Exception {
        WpSpot spot=spotDao.findSpotById(spot_id);
        if (spot==null){
            model.setFailure("非法查询接口");
            return;
        }
        spotDao.upSpotWordStatus(spot_id,2);
        model.setSucceed("车位上升过程中，请注意安全");
    }

    @Override
    public void spotFail(int spot_id, int user_id, BaseModel model) throws Exception {
        WpSpot spot=spotDao.findSpotById(spot_id);
        if (spot==null){
            model.setFailure("非法查询接口");
            return;
        }
        spotDao.upSpotWordStatus(spot_id,3);
        model.setSucceed("车位下降过程中，请注意安全");
    }

}
