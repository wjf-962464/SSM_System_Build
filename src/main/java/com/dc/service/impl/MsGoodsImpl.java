package com.dc.service.impl;

import com.dc.base.pojo.BaseModel;
import com.dc.dao.MsGoodsDao;
import com.dc.dao.MsStoresDao;
import com.dc.pojo.MsGoodDetail;
import com.dc.pojo.MsGoods;
import com.dc.service.MsGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsGoodsImpl implements MsGoodsService {
    @Autowired
    private MsGoodsDao goodsDao;
    @Autowired
    private MsStoresDao storesDao;

    public void getGoodsList(int page,int size,BaseModel model) throws Exception {
        List<MsGoods> list=goodsDao.getGoodsList();
        int list_size=list.size();
        if(list_size==0){
            model.setResultCode(0);
            model.setMessage("暂无商品，先看看别的吧");
            return;
        }
        if(page*size>=list_size){
            model.setResultCode(0);
            model.setMessage("(*╹▽╹*)没有下一页了，已经是底线了喔-_-");
            return;
        }
        int end=(page*size+size)>=list_size?list_size:(page*size+size);
        List<MsGoods> data=list.subList(page*size,end);
        model.setResultCode(1);
        model.setMessage("查询成功，该页商品数量为："+data.size());
        model.setData(data);
    }

    public void addInventory(int good_id, int add_inventory, BaseModel model) throws Exception {
        MsGoods good=goodsDao.findById(good_id);
        if(good==null){
            model.setResultCode(0);
            model.setMessage("该商品不存在");
            return;
        }
        if(good.getGood_status()!=1){
            model.setResultCode(0);
            model.setMessage("该商品已下线，不再出售，再看看别的吧");
            return;
        }
        if(good.getInventory()>=9999){
            model.setResultCode(0);
            model.setMessage("该商品库存已过量，请勿增加");
            return;
        }
        if ((good.getInventory()+add_inventory)>=9999){
            model.setResultCode(1);
            goodsDao.addInventory(good_id,9999);
            model.setMessage("库存增加过量，已调节至上限，增加库存："+(9999-good.getInventory()));
            return;
        }
        model.setResultCode(1);
        goodsDao.addInventory(good_id,(good.getInventory()+add_inventory));
        model.setMessage("增加库存成功，增量为："+add_inventory+"当前库存为：："+(add_inventory+good.getInventory()));
    }

    public void getListByKeyword(String keyword, BaseModel model) throws Exception {
        List<MsGoods> data=goodsDao.getListByKeyword(keyword);
        if(data.size()==0){
            model.setResultCode(0);
            model.setMessage("小柯尽力了*-*,换个关键词试试吧");
            return;
        }
        model.setResultCode(1);
        model.setMessage("查询成功");
        model.setData(data);
    }

    @Override
    public void getGoodDetail(int good_id, BaseModel model) throws Exception {
        MsGoods good=goodsDao.findById(good_id);
        if(good==null){
            model.setResultCode(0);
            model.setMessage("该商品不存在");
            return;
        }
        MsGoodDetail goodDetail=new MsGoodDetail(good,storesDao.findById(good.getStore_id()));
        model.setResultCode(1);
        model.setMessage("查询成功");
        model.setData(goodDetail);
    }
}
