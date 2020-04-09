package com.dc.dao;

import com.dc.pojo.WpSpotOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WpSpotOrderDao {
    WpSpotOrder getIfHaveOrder(@Param("user_id")int user_id);
    void addOrder(@Param("user_id")int user_id,@Param("spot_id")int spot_id);
    void cancelOrder(@Param("user_id")int user_id);
    void overtimeOrder(@Param("user_id")int user_id);
    void submitOrder(@Param("user_id")int user_id);
    void completeOrder(@Param("user_id")int user_id);
    List<WpSpotOrder> getOrderList(@Param("user_id")int user_id);
}
