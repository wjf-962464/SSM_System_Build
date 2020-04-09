package com.dc.service.impl;

import com.dc.base.pojo.BaseModel;
import com.dc.base.util.PageList;
import com.dc.dao.WpParkDao;
import com.dc.dao.WpSpotDao;
import com.dc.dao.WpSpotOrderDao;
import com.dc.pojo.WpPark;
import com.dc.pojo.WpSpot;
import com.dc.pojo.WpSpotOrder;
import com.dc.service.WpParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WpParkImpl implements WpParkService {
    @Autowired
    private WpParkDao parkDao;
    @Autowired
    private WpSpotOrderDao spotOrderDao;
    @Autowired
    private WpSpotDao spotDao;

    @Override
    public void parkRegister(WpPark park, BaseModel model) throws Exception {
        if(parkDao.findParkByPoi(park.getPoi_id())!=null){
            model.setResultCode(0);
            model.setMessage("该停车场已经注册");
            return;
        }
        parkDao.parkRegister(park);
        model.setResultCode(1);
        model.setMessage("停车场注册成功");
    }

    @Override
    public void upRemainSpot(int park_id, int spot_in, BaseModel model) throws Exception {
        WpPark park = parkDao.findParkById(park_id);
        if (park == null) {
            model.setResultCode(0);
            model.setMessage("不存在该停车场");
            return;
        }
        if (park.getPark_status() != 1) {
            model.setResultCode(0);
            model.setMessage("该停车场处于违规状态，已被注销");
            return;
        }
        model.setResultCode(1);
        model.setMessage("修改剩余停车位成功，当前剩余停车位数：" + (park.getSpot_remain() + spot_in));
        parkDao.upRemainSpot(park_id, park.getSpot_remain() + spot_in);
    }


    @Override
    public void upParkGrade(int park_id, int grade_in, BaseModel model) throws Exception {
        WpPark park = parkDao.findParkById(park_id);
        if (park == null) {
            model.setResultCode(0);
            model.setMessage("不存在该停车场");
            return;
        }
        if (park.getPark_status() != 1) {
            model.setResultCode(0);
            model.setMessage("该停车场处于违规状态，已被注销");
            return;
        }
        model.setResultCode(1);
        model.setMessage("修改停车场评分成功，当前评分为：" + (park.getPark_grade() + grade_in));
        parkDao.upParkGrade(park_id, park.getPark_grade() + grade_in);
    }

    @Override
    public void upTotalSpot(int park_id, int spot_in, BaseModel model) throws Exception {
        WpPark park = parkDao.findParkById(park_id);
        if (park == null) {
            model.setResultCode(0);
            model.setMessage("不存在该停车场");
            return;
        }
        if (park.getPark_status() != 1) {
            model.setResultCode(0);
            model.setMessage("该停车场处于违规状态，已被注销");
            return;
        }
        parkDao.upTotalSpot(park_id, park.getSpot_total() + spot_in);
        model.setResultCode(1);
        model.setMessage("修改停车位总数成功，当前停车位总数：" + (park.getSpot_total() + spot_in));
    }

    @Override
    public void getParkList(int page, int size, BaseModel model) throws Exception {
        List<WpPark> list = parkDao.getParkList();
        PageList.pageQuery(list, page, size, "停车场", model);
    }

    @Override
    public void getParkDetailById(int page_id, BaseModel model) throws Exception {
        WpPark park = parkDao.findParkById(page_id);
        if (park == null) {
            model.setResultCode(0);
            model.setMessage("没有该停车场");
            return;
        }
        if (park.getPark_status() == 0) {
            model.setResultCode(0);
            model.setMessage("该停车场已被注销");
            return;
        }
        model.setData(park);
        model.setResultCode(1);
        model.setMessage("停车场查询成功");
    }

    @Override
    public void getParkDetailByPoi(String poi_id, BaseModel model) throws Exception {
        WpPark park = parkDao.findParkByPoi(poi_id);
        if (park == null) {
            model.setResultCode(0);
            model.setMessage("没有该停车场");
            return;
        }
        if (park.getPark_status() == 0) {
            model.setResultCode(0);
            model.setMessage("该停车场已被注销");
            return;
        }
        model.setData(park);
        model.setResultCode(1);
        model.setMessage("停车场查询成功");
    }

    @Override
    public void searchParkList(String keyword, BaseModel model) throws Exception {
        List<WpPark> list =parkDao.searchParkList(keyword);
        if (list.size()==0){
            model.setResultCode(0);
            model.setMessage("抱歉，没有匹配关键字的停车场\n换个关键词试试吧o-o");
        }
        model.setData(list);
        model.setResultCode(1);
        model.setMessage("检索成功");
    }
}
