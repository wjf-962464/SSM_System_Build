package com.dc.service;

import com.dc.base.pojo.BaseModel;

public interface MsGoodsService {
    void getGoodsList(int page,int size,BaseModel model)throws Exception;
    void addInventory(int good_id,int add_inventory,BaseModel model)throws Exception;
    void getListByKeyword(String keyword,BaseModel model)throws Exception;
    void getGoodDetail(int good_id,BaseModel model)throws Exception;
}
