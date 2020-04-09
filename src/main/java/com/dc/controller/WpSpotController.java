package com.dc.controller;

import com.dc.base.pojo.BaseModel;
import com.dc.pojo.WpSpot;
import com.dc.service.UserService;
import com.dc.service.WpParkService;
import com.dc.service.WpSpotService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("wpSpot")
public class WpSpotController {
    @Autowired
    private WpSpotService spotService;
    @Autowired
    private UserService userService;

    @ApiOperation("控制车位上升")
    @ResponseBody
    @RequestMapping(value = "/control/spotRise",method = RequestMethod.POST)
    public BaseModel spotRise(@RequestParam("spot_id")int spot_id,
                                   @RequestParam("login_flag")String login_flag)throws Exception{
        BaseModel model=new BaseModel();
        int user_id=userService.getUserLoginFlag(login_flag);
        if (user_id==-1){
            model.setFailure("非法访问接口");
            return model;
        }
        spotService.spotRise(spot_id,user_id,model);
        return model;
    }
    @ApiOperation("控制车位上升")
    @ResponseBody
    @RequestMapping(value = "/control/spotFail",method = RequestMethod.POST)
    public BaseModel spotFail(@RequestParam("spot_id")int spot_id,
                              @RequestParam("login_flag")String login_flag)throws Exception{
        BaseModel model=new BaseModel();
        int user_id=userService.getUserLoginFlag(login_flag);
        if (user_id==-1){
            model.setFailure("非法访问接口");
            return model;
        }
        spotService.spotFail(spot_id,user_id,model);
        return model;
    }

    @ApiOperation("查询车位归属")
    @ResponseBody
    @RequestMapping(value = "/getSpotBelong",method = RequestMethod.GET)
    public BaseModel getSpotBelong(@RequestParam("spot_id")int spot_id,
                                   @RequestParam("login_flag")String login_flag)throws Exception{
        BaseModel model=new BaseModel();
        int user_id=userService.getUserLoginFlag(login_flag);
        if (user_id==-1){
            model.setFailure("非法访问接口");
            return model;
        }
        spotService.getSpotBelong(spot_id,user_id,model);
        return model;
    }

    @ApiOperation("获取停车位列表")
    @ResponseBody
    @RequestMapping(value = "/getPageList",method = RequestMethod.GET)
    public BaseModel getParkPageList(@RequestParam("park_id")int park_id,
                                 @RequestParam("page")int page,
                                 @RequestParam("size")int size)throws Exception{
        BaseModel model=new BaseModel();
        spotService.getSpotPageList(park_id,page-1,size,model);
        return model;
    }
    @ApiOperation("获取停车位列表")
    @ResponseBody
    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    public BaseModel getParkList(@RequestParam("park_id")int park_id)throws Exception{
        BaseModel model=new BaseModel();
        spotService.getSpotList(park_id,model);
        return model;
    }

    @ApiOperation("获取停车点详情")
    @ResponseBody
    @RequestMapping(value = "/getListDetail",method = RequestMethod.GET)
    public BaseModel getListDetail(@RequestParam("spot_list_id")int spot_list_id)throws Exception{
        BaseModel model=new BaseModel();
        spotService.getListDetail(spot_list_id,model);
        return model;
    }
    @ApiOperation("获取停车位详情")
    @ResponseBody
    @RequestMapping(value = "/getSpotDetail",method = RequestMethod.POST)
    public BaseModel getSpotDetail(@RequestParam("login_flag")String login_flag,
            @RequestParam("spot_id")int spot_id)throws Exception{
        BaseModel model=new BaseModel();
        int user_id=userService.getUserLoginFlag(login_flag);
        if (user_id==-1){
            model.setFailure("非法访问接口");
            return model;
        }
        spotService.getSpotDetail(spot_id,model);
        return model;
    }
    @ApiOperation("修改停车位占用状态")
    @ResponseBody
    @RequestMapping(value = "/update/occupyStatus",method = RequestMethod.POST)
    public BaseModel upSpotOccupyStatus(@RequestParam("spot_id")int spot_id,
                                        @RequestParam("occupy_status")int status)throws Exception{
        BaseModel model=new BaseModel();
        spotService.upSpotOccupyStatus(spot_id,status,model);
        return model;
    }

    @ApiOperation("注册车位")
    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public BaseModel addSpot(@RequestParam("park_id")int park_id,
                             @ApiParam("分区") @RequestParam("area")String area,
                             @ApiParam("编号") @RequestParam("site")String site,
                             @ApiParam("类型，1或3") @RequestParam("type")int type)throws Exception{
        BaseModel model=new BaseModel();
        spotService.registerSpot(park_id,area,site,type,model);
        return model;
    }
}
