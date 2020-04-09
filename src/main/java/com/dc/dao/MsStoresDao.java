package com.dc.dao;

import com.dc.pojo.MsStores;
import com.wordnik.swagger.annotations.ApiParam;

import java.util.List;

public interface MsStoresDao {
    MsStores findById(@ApiParam("id")int store_id);
    List<MsStores> getStoreList();
}
