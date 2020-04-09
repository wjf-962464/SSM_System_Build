package com.dc.service;

import com.dc.base.pojo.BaseModel;

public interface MsOrdersService {
    void createOrder(int user_id, String goods_id, String goods_num,String send_address, BaseModel model)throws Exception;
    void getOrderList(int user_id,int page,int size,BaseModel model)throws Exception;
}
