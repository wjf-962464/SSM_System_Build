package com.dc.service;

import com.dc.base.pojo.BaseModel;

public interface MsShopcarService {
    void getShopCar(int user_id,BaseModel model)throws Exception;
    void addToShopCar(int user_id, String goods_id, BaseModel model)throws Exception;
    void upShopCar(int user_id, String goods_id, String goods_num, BaseModel model)throws Exception;
    void deleteFromShopCar(int user_id, String goods_id, BaseModel model)throws Exception;
}
