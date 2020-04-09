package com.dc.service;

import com.dc.base.pojo.BaseModel;
import com.dc.pojo.WpSpot;

import java.util.List;

public interface WpSpotService {
    void getSpotPageList(int park_id, int page, int size, BaseModel model) throws Exception;

    void getSpotList(int park_id, BaseModel model) throws Exception;

    void upSpotOccupyStatus(int spot_id, int occupy_status, BaseModel model) throws Exception;

    void addSpot(WpSpot spot, BaseModel model) throws Exception;

    void registerSpot(int park_id, String area, String site, int type, BaseModel model) throws Exception;

    void getListDetail(int spot_list_id, BaseModel model) throws Exception;

    void getSpotDetail(int spot_id, BaseModel model) throws Exception;

    void getSpotBelong(int spot_id,int user_id, BaseModel model) throws Exception;

    void spotRise(int spot_id,int user_id, BaseModel model) throws Exception;
    void spotFail(int spot_id,int user_id, BaseModel model) throws Exception;
}
