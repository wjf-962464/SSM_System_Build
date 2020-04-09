package com.dc.service.impl;

import com.dc.base.pojo.BaseModel;
import com.dc.dao.MsGoodsDao;
import com.dc.dao.MsOrdersDao;
import com.dc.pojo.MsGoods;
import com.dc.pojo.MsOrders;
import com.dc.service.MsOrdersService;
import com.dc.service.MsStoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MsOrderImpl implements MsOrdersService {
    @Autowired
    private MsOrdersDao ordersDao;
    @Autowired
    private MsGoodsDao goodsDao;

    public void createOrder(int user_id, String goods_id, String goods_num, String send_address, BaseModel model) throws Exception {
        String cost_list = "";
        BigDecimal total_money = new BigDecimal(0);
        if (goods_id.indexOf(",") != -1) {
            String[] goods_id_strArr = goods_id.split(",");
            String[] goods_num_strArr = goods_num.split(",");
            int len = goods_id_strArr.length;
            for (int i = 0; i < len; i++) {
                BigDecimal price = getGoodPrice(Integer.parseInt(goods_id_strArr[i]), Integer.parseInt(goods_num_strArr[i]));
                total_money = total_money.add(price);
                if (i == len - 1) {
                    cost_list += price.toString();
                    break;
                } else {
                    cost_list += price.toString() + ",";
                }
            }
        } else {
            BigDecimal price = getGoodPrice(Integer.parseInt(goods_id), Integer.parseInt(goods_num));
            total_money = total_money.add(price);
            cost_list += price.toString();
        }
        MsOrders order = new MsOrders();
        order.setUser_id(user_id);
        order.setGood_id_list(goods_id);
        order.setGood_amount_list(goods_num);
        order.setCost_list(cost_list);
        order.setTotal_money(total_money);
        order.setSend_address(send_address);
        ordersDao.createOrder(order);
        model.setResultCode(1);
        model.setMessage("生成订单成功");
    }

    public void getOrderList(int user_id, int page, int size, BaseModel model) throws Exception {
        List<MsOrders> list = ordersDao.getOrderList(user_id);
        int list_size=list.size();
        if (list_size == 0) {
            model.setResultCode(0);
            model.setMessage("尚无订单，快去买点犒劳自己吧");
            return;
        }
        if (page * size >= list_size) {
            model.setResultCode(0);
            model.setMessage("(*╹▽╹*)没有下一页了，已经是底线了喔-_-");
            return;
        }
        int end=(page*size+size)>=list_size?list_size:(page*size+size);
        List<MsOrders> data = list.subList(page * size, end);
        model.setResultCode(1);
        model.setMessage("订单查询成功，该页订单数为："+data.size());
        model.setData(data);
    }

    private BigDecimal getGoodPrice(int good_id, int good_num) {
        MsGoods good = goodsDao.findById(good_id);
        if (good.getPromotion_flag() == 0) {
            return good.getPrice().multiply(new BigDecimal(good_num));
        }
        return good.getPromotion_price().multiply(new BigDecimal(good_num));
    }
}
