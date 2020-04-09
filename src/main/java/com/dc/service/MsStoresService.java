package com.dc.service;

import com.dc.base.pojo.BaseModel;

public interface MsStoresService {
    void findById(int store_id, BaseModel model) throws Exception;

    void getStoreList(int page, int size, BaseModel model) throws Exception;
}
