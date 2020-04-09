package com.dc.service.impl;

import com.dc.base.pojo.BaseModel;
import com.dc.base.util.PageList;
import com.dc.dao.MsStoresDao;
import com.dc.pojo.MsOrders;
import com.dc.pojo.MsStores;
import com.dc.service.MsStoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsStoresImpl implements MsStoresService {
    @Autowired
    private MsStoresDao storesDao;

    public void findById(int store_id, BaseModel model) throws Exception {
        MsStores store=storesDao.findById(store_id);
        if(store==null){
            model.setResultCode(0);
            model.setMessage("查无此店");
            return;
        }
        if (store.getStatus()!=1){
            model.setResultCode(0);
            model.setMessage("此店涉嫌违规操作，已被封停");
            return;
        }
        model.setResultCode(1);
        model.setMessage("店铺查询成功");
        model.setData(store);
    }


    public void getStoreList(int page, int size, BaseModel model) throws Exception {
        List<MsStores> list=storesDao.getStoreList();
        PageList.pageQuery(list,page,size,"店铺",model);
/*        int list_size=list.size();
        if(list.size()==0){
            model.setResultCode(0);
            model.setMessage("还没有店铺开张哦");
            return;
        }
        if (page * size > list_size) {
            model.setResultCode(0);
            model.setMessage("(*╹▽╹*)没有下一页了，已经是底线了喔-_-");
            return;
        }
        int end=(page*size+size)>list_size?list_size:(page*size+size);
        List<MsStores> data = list.subList(page * size, end);
        model.setResultCode(1);
        model.setMessage("店铺查询成功，该页店铺数为："+data.size());
        model.setData(data);*/
    }
}
