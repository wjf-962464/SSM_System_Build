package com.dc.service;

import com.dc.base.pojo.BaseModel;

public interface WpSpotOrderService {
    void addSpotOrder(int user_id, int park_id, BaseModel model)throws Exception;
    void getOrderPageList(int user_id,int page,int size,BaseModel model)throws Exception;
    void cancelOrder(int user_id,BaseModel model)throws Exception;
    void getOrderInfo(int user_id,BaseModel model)throws Exception;
    void completeOrder(int user_id,BaseModel model)throws Exception;
    void applyOrder(int user_id,int spot_id,BaseModel model)throws Exception;
    void submitOrder(int user_id,BaseModel model)throws Exception;
}
