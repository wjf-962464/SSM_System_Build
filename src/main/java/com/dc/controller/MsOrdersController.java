package com.dc.controller;

import com.dc.base.pojo.BaseModel;
import com.dc.service.MsOrdersService;
import com.wordnik.swagger.annotations.Api;
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
@RequestMapping("order")
public class MsOrdersController {
    @Autowired
    private MsOrdersService service;

    @ApiOperation("生成订单")
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseModel createOrder(@ApiParam("用户id，之后改为登录标识")@RequestParam("user_id") int user_id,
                                 @ApiParam("商品号列表，以逗号间隔") @RequestParam("goods_id")String goods_id,
                                 @ApiParam("商品数列表，以逗号间隔") @RequestParam("goods_num")String goods_num,
                                 @ApiParam("收件地址") @RequestParam("send_add")String send_add) throws Exception {
        BaseModel model = new BaseModel();
        service.createOrder(user_id,goods_id,goods_num,send_add,model);
        return model;
    }

    @ApiOperation("查询订单列表")
    @ResponseBody
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public BaseModel getOrderList(@RequestParam("user_id")int user_id,
                                  @RequestParam("page")int page,
                                  @RequestParam("size")int size)throws Exception{
        BaseModel model = new BaseModel();
        service.getOrderList(user_id,page-1,size,model);
        return model;
    }
}
