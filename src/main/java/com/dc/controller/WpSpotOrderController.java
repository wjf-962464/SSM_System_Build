package com.dc.controller;

import com.dc.base.pojo.BaseModel;
import com.dc.pojo.WpSpotOrder;
import com.dc.service.UserService;
import com.dc.service.WpParkService;
import com.dc.service.WpSpotOrderService;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("wpSpotOrder")
public class WpSpotOrderController {
    @Autowired
    private WpSpotOrderService orderService;
    @Autowired
    private UserService userService;

    @ApiOperation("预约车位")
    @ResponseBody
    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public BaseModel spotOrder(@RequestParam("login_flag") String login_flag,
                               @RequestParam("park_id") int park_id) throws Exception {
        BaseModel model = new BaseModel();
        int user_id=userService.getUserLoginFlag(login_flag);
        if (user_id==-1){
            model.setFailure("非法访问接口");
            return model;
        }
        orderService.addSpotOrder(user_id, park_id, model);
        return model;
    }
    @ApiOperation("订单申请")
    @ResponseBody
    @RequestMapping(value = "/applyOrder", method = RequestMethod.POST)
    public BaseModel applyOrder(@RequestParam("login_flag") String login_flag,
                               @RequestParam("spot_id") int spot_id) throws Exception {
        BaseModel model = new BaseModel();
        int user_id=userService.getUserLoginFlag(login_flag);
        if (user_id==-1){
            model.setFailure("非法访问接口");
            return model;
        }
        orderService.applyOrder(user_id, spot_id, model);
        return model;
    }
    @ApiOperation("订单提交，即停车")
    @ResponseBody
    @RequestMapping(value = "/submitOrder", method = RequestMethod.POST)
    public BaseModel submitOrder(@RequestParam("login_flag") String login_flag) throws Exception {
        BaseModel model = new BaseModel();
        int user_id=userService.getUserLoginFlag(login_flag);
        if (user_id==-1){
            model.setFailure("非法访问接口");
            return model;
        }
        orderService.submitOrder(user_id, model);
        return model;
    }
    @ApiOperation("取消预约")
    @ResponseBody
    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    public BaseModel cancelOrder(@RequestParam("login_flag") String login_flag) throws Exception {
        BaseModel model = new BaseModel();
        int user_id=userService.getUserLoginFlag(login_flag);
        if (user_id==-1){
            model.setFailure("非法访问接口");
            return model;
        }
        orderService.cancelOrder(user_id, model);
        return model;
    }

    @ApiOperation("预约查询")
    @ResponseBody
    @RequestMapping(value = "/getOrder", method = RequestMethod.GET)
    public BaseModel getOrderInfo(@RequestParam("login_flag") String login_flag) throws Exception {
        BaseModel model = new BaseModel();
        int user_id=userService.getUserLoginFlag(login_flag);
        if (user_id==-1){
            model.setFailure("非法访问接口");
            return model;
        }
        orderService.getOrderInfo(user_id, model);
        return model;
    }

    @ApiOperation("获取订单列表")
    @ResponseBody
    @RequestMapping(value = "/getPageList", method = RequestMethod.GET)
    public BaseModel getOrderPageList(@RequestParam("login_flag") String login_flag,
                                      @RequestParam("page") int page,
                                      @RequestParam("size") int size) throws Exception {
        BaseModel model = new BaseModel();
        int user_id=userService.getUserLoginFlag(login_flag);
        if (user_id==-1){
            model.setFailure("非法访问接口");
            return model;
        }
        orderService.getOrderPageList(user_id, page - 1, size, model);
        return model;
    }
    @ApiOperation("完成订单即取车")
    @ResponseBody
    @RequestMapping(value = "/completeOrder", method = RequestMethod.POST)
    public BaseModel completeOrder(@RequestParam("login_flag") String login_flag) throws Exception {
        BaseModel model = new BaseModel();
        int user_id=userService.getUserLoginFlag(login_flag);
        if (user_id==-1){
            model.setFailure("非法访问接口");
            return model;
        }
        if (getOrderInfo(login_flag).getData()==null){
            model.setFailure("没有预约，使用非法");
            return model;
        }
        orderService.completeOrder(user_id,model);
        return model;
    }

}
