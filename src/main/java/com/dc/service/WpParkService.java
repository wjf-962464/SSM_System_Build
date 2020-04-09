package com.dc.service;

import com.dc.base.pojo.BaseModel;
import com.dc.pojo.WpPark;

public interface WpParkService {
    void parkRegister(WpPark park,BaseModel model)throws Exception;
    void upRemainSpot(int park_id,int spot_in,BaseModel model)throws Exception;
    void upParkGrade(int park_id,int grade_in,BaseModel model)throws Exception;
    void upTotalSpot(int park_id,int spot_in,BaseModel model)throws Exception;
    void getParkList(int page,int size,BaseModel model)throws Exception;
    void getParkDetailById(int page_id,BaseModel model)throws Exception;
    void getParkDetailByPoi(String poi_id,BaseModel model)throws Exception;
    void searchParkList(String keyword,BaseModel model)throws Exception;

}
