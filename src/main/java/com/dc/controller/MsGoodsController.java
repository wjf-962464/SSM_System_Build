package com.dc.controller;

import com.dc.base.pojo.BaseModel;
import com.dc.service.MsGoodsService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("msGoods")
public class MsGoodsController {
    @Autowired
    private MsGoodsService service;

    @ApiOperation(value = "增加库存")
    @ResponseBody
    @RequestMapping(value = "/addInventory", method = RequestMethod.POST)
    public BaseModel addInventory(@ApiParam("商品id") @RequestParam(value = "good_id") int good_id,
                                  @ApiParam("增加库存") @RequestParam(value = "add_inventory") int add_inventory) throws Exception {
        BaseModel model = new BaseModel();
        service.addInventory(good_id,add_inventory,model);
        return model;
    }

    @ApiOperation("获取商品列表")
    @ResponseBody
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public BaseModel getGoodsList(@ApiParam("页号") @RequestParam(value = "page") int page,
                                  @ApiParam("查询数量") @RequestParam(value = "size") int size)throws Exception{
        BaseModel model = new BaseModel();
        service.getGoodsList(page-1,size,model);
        return model;
    }

    @ApiOperation("关键词查询")
    @ResponseBody
    @RequestMapping(value = "/search/{keyword}",method = RequestMethod.GET)
    public BaseModel searchGoodsByKeyword(@ApiParam("关键词") @PathVariable(value = "keyword")String keyword)throws Exception{
        BaseModel model=new BaseModel();
        service.getListByKeyword(keyword,model);
        return model;
    }

    @ApiOperation("商品详情")
    @ResponseBody
    @RequestMapping(value = "/detail/{good_id}",method = RequestMethod.GET)
    public BaseModel getGoodDetail(@ApiParam("商品id") @PathVariable(value = "good_id")int good_id)throws Exception{
        BaseModel model=new BaseModel();
        service.getGoodDetail(good_id,model);
        return model;
    }
}
