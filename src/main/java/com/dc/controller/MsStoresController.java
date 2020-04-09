package com.dc.controller;

import com.dc.base.pojo.BaseModel;
import com.dc.service.MsStoresService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("msStores")
public class MsStoresController {
    @Autowired
    private MsStoresService service;

    @ApiOperation("查询店铺")
    @ResponseBody
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public BaseModel findById(@ApiParam("店铺id") @PathVariable("id") int id) throws Exception {
        BaseModel model = new BaseModel();
        service.findById(id, model);
        return model;
    }

    @ApiOperation("店铺列表")
    @ResponseBody
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public BaseModel getStoreList(@RequestParam("page")int page,
                                  @RequestParam("size")int size)throws Exception{
        BaseModel model=new BaseModel();
        service.getStoreList(page-1,size,model);
        return model;
    }
}
