package com.dc.controller;

import com.dc.base.pojo.BaseModel;

import com.dc.service.UserService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录")
    @ResponseBody
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public BaseModel userLogin(@ApiParam("账号") @RequestParam(value = "account")String account, @ApiParam("密码") @RequestParam(value = "pwd")String pwd)throws Exception{
        BaseModel model=new BaseModel();
        userService.userLogin(account,pwd,model);
        return model;
    }

    @ApiOperation(value = "注册")
    @ResponseBody
    @RequestMapping(value = "/register",method = {RequestMethod.POST})
    public BaseModel userRegister(@ApiParam("账号")@RequestParam("account")String account,
                                  @ApiParam("加密后的密码")@RequestParam("pwd")String pwd)throws Exception{
        BaseModel model=new BaseModel();
        userService.userRegister(account,pwd,model);
        return model;
    }
}
