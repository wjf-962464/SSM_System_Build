package com.dc.dao;

import com.dc.pojo.MsOrders;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface MsOrdersDao {
    void createOrder(MsOrders order);
    List<MsOrders> getOrderList(@Param("user_id")int user_id);
}
