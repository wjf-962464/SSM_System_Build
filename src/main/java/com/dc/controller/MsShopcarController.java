package com.dc.controller;

import com.dc.base.pojo.BaseModel;
import com.dc.service.MsShopcarService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("shopcar")
public class MsShopcarController {
    @Autowired
    private MsShopcarService shopcarService;

    @ApiOperation("查询购物车")
    @ResponseBody
    @RequestMapping(value = "/getShopCar/{user_id}", method = RequestMethod.GET)
    public BaseModel getShopCar(@ApiParam("用户id，之后改为登录标识")@PathVariable("user_id") int user_id) throws Exception {
        BaseModel model = new BaseModel();
        shopcarService.getShopCar(user_id,model);
        return model;
    }

    @ApiOperation("加入购物车")
    @ResponseBody
    @RequestMapping(value = "/addToShopCar", method = RequestMethod.POST)
    public BaseModel addToShopCar(@ApiParam("用户id，之后改为登录标识")@RequestParam("user_id") int user_id,
                                  @ApiParam("加入购物车的商品号") @RequestParam("goods_id")String goods_id) throws Exception {
        BaseModel model = new BaseModel();
        shopcarService.addToShopCar(user_id,goods_id,model);
        return model;
    }
    @ApiOperation("从购物车删除（可批量）")
    @ResponseBody
    @RequestMapping(value = "/deleteFromShopCar", method = RequestMethod.POST)
    public BaseModel deleteFromShopCar(@ApiParam("用户id，之后改为登录标识")@RequestParam("user_id") int user_id,
                                  @ApiParam("删除的商品号列表，以逗号间隔") @RequestParam("goods_id")String goods_id) throws Exception {
        BaseModel model = new BaseModel();
        shopcarService.deleteFromShopCar(user_id,goods_id,model);
        return model;
    }

    @ApiOperation("修改购物车商品数量（可批量）")
    @ResponseBody
    @RequestMapping(value = "/upShopCar", method = RequestMethod.POST)
    public BaseModel upShopCar(@ApiParam("用户id，之后改为登录标识")@RequestParam("user_id") int user_id,
                                  @ApiParam("需要修改的商品号列表，以逗号间隔") @RequestParam("goods_id")String goods_id,
                                  @ApiParam("修改后的商品数列表，以逗号间隔") @RequestParam("goods_num")String goods_num) throws Exception {
        BaseModel model = new BaseModel();
        if((goods_id.indexOf(",")==-1&&goods_num.indexOf(",")==-1)||goods_id.split(",").length==goods_num.split(",").length){
            shopcarService.upShopCar(user_id,goods_id,goods_num,model);
        }else {
            model.setMessage("修改失败，请注意参数格式规范");
            model.setResultCode(0);
        }
        return model;
    }
}
