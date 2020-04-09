package com.dc.dao;

import com.dc.pojo.MsGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MsGoodsDao {
    MsGoods findById(@Param("id")int good_id);
    List<MsGoods> getGoodsList();
    List<MsGoods> getListByKeyword(@Param("keyword")String keyword);
    void addInventory(@Param("id")int good_id,@Param("inventory") int inventory);

}
