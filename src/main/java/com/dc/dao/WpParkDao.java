package com.dc.dao;

import com.dc.pojo.WpPark;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WpParkDao {
    void parkRegister(WpPark park);
    void upRemainSpot(@Param("park_id")int park_id,@Param("remain_spot")int remain_spot);
    void upParkGrade(@Param("park_id")int park_id,@Param("park_grade")int park_grade);
    void upTotalSpot(@Param("park_id")int park_id,@Param("total_spot")int total_spot);
    List<WpPark> getParkList();
    List<WpPark> searchParkList(@Param("keyword")String keyword);
    WpPark findParkById(@Param("park_id")int park_id);
    WpPark findParkByPoi(@Param("poi_id")String poi_id);
}
