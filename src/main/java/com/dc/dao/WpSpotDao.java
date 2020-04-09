package com.dc.dao;

import com.dc.pojo.WpPark;
import com.dc.pojo.WpSpot;
import com.dc.pojo.WpSpotList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WpSpotDao {
    List<WpSpot> getFreeSpotList(@Param("park_id")int park_id);
    List<WpSpot> getListDetail(WpSpotList spotList);
    List<WpSpotList> getSpotList(@Param("park_id")int park_id);
    WpSpot findSpotById(@Param("spot_id")int spot_id);
    void upSpotOccupyStatus(@Param("spot_id")int spot_id,@Param("occupy_status")int occupy_status);
    void upSpotWordStatus(@Param("spot_id")int spot_id,@Param("work_status")int work_status);
    void addSpot(WpSpot spot);
    void upSpotOrder(@Param("spot_id")int spot_id);
    void upSpotBusy(@Param("spot_id")int spot_id);
    void upSpotFree(@Param("spot_id")int spot_id);

    WpSpotList findSpotList(WpSpotList spotList);
    WpSpotList findSpotListById(@Param("spot_list_id")int spot_list_id);
    void addSpotList(WpSpotList spotList);
    void upSpotListFreeSpot(WpSpotList spotList);
}
