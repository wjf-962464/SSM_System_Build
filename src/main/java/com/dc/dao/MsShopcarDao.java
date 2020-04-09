package com.dc.dao;

import com.dc.pojo.MsShopcar;
import org.apache.ibatis.annotations.Param;

public interface MsShopcarDao {
    MsShopcar getShopCar(@Param("user_id")int user_id);
    void createShopCar(@Param("user_id")int user_id);
    void upShopCar(MsShopcar shopcar);
}
