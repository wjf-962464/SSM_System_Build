package com.dc.controller;

import com.dc.base.pojo.BaseModel;
import com.dc.pojo.WpPark;
import com.dc.service.WpParkService;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("wpPark")
public class WpParkController {
    @Autowired
    private WpParkService parkService;

    @ApiOperation("获取停车场详细信息")
    @ResponseBody
    @RequestMapping(value = "/getDetailById/{park_id}", method = RequestMethod.GET)
    public BaseModel getParkDetailById(@PathVariable("park_id") int park_id) throws Exception {
        BaseModel model = new BaseModel();
        parkService.getParkDetailById(park_id, model);
        return model;
    }
    @ApiOperation("获取停车场详细信息,POI")
    @ResponseBody
    @RequestMapping(value = "/getDetailByPoi/{poi_id}", method = RequestMethod.GET)
    public BaseModel getParkDetailByPoi(@PathVariable("poi_id") String poi_id) throws Exception {
        BaseModel model = new BaseModel();
        parkService.getParkDetailByPoi(poi_id, model);
        return model;
    }
    @ApiOperation("检索停车场列表")
    @ResponseBody
    @RequestMapping(value = "/search/{keyword}", method = RequestMethod.GET)
    public BaseModel searchParkList(@PathVariable("keyword") String keyword) throws Exception {
        BaseModel model = new BaseModel();
        parkService.searchParkList(keyword, model);
        return model;
    }

    @ApiOperation("获取停车场列表")
    @ResponseBody
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public BaseModel getParkList(@RequestParam("page") int page,
                                 @RequestParam("size") int size) throws Exception {
        BaseModel model = new BaseModel();
        parkService.getParkList(page-1, size, model);
        return model;
    }




    @ApiOperation("修改停车位总数")
    @ResponseBody
    @RequestMapping(value = "/update/totalSpot", method = RequestMethod.POST)
    public BaseModel upTotalSpot(@RequestParam("park_id") int park_id,
                                 @RequestParam("spot_in") int spot_in)throws Exception {
        BaseModel model = new BaseModel();
        parkService.upTotalSpot(park_id,spot_in,model);
        return model;
    }

    @ApiOperation("修改停车场评分")
    @ResponseBody
    @RequestMapping(value = "/update/parkGrade", method = RequestMethod.POST)
    public BaseModel upParkGrade(@RequestParam("park_id") int park_id,
                                 @RequestParam("grade_in") int grade_in) throws Exception {
        BaseModel model = new BaseModel();
        parkService.upParkGrade(park_id,grade_in,model);
        return model;
    }

    @ApiOperation("修改剩余停车位数")
    @ResponseBody
    @RequestMapping(value = "/update/remainSpot", method = RequestMethod.POST)
    public BaseModel upRemainSpot(@RequestParam("park_id") int park_id,
                                  @RequestParam("spot_in") int spot_in) throws Exception {
        BaseModel model = new BaseModel();
        parkService.upRemainSpot(park_id,spot_in,model);
        return model;
    }

    @ApiOperation("注册停车场")
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public BaseModel parkRegister(@RequestParam("park_name") String name,
                                  @RequestParam("poi_id") String poi_id,
                                  @RequestParam("park_gps") String gps,
                                  @RequestParam("park_info") String info,
                                  @RequestParam("spot_total") int spot_total) throws Exception {
        BaseModel model = new BaseModel();
        WpPark park=new WpPark();
        park.setPark_name(name);
        park.setPoi_id(poi_id);
        park.setPark_gps(gps);
        park.setPark_info(info);
        park.setSpot_total(spot_total);
        parkService.parkRegister(park,model);
        return model;
    }
}
