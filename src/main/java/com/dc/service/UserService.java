package com.dc.service;


import com.dc.base.pojo.BaseModel;

public interface UserService {
    void userLogin(String account, String pwd, BaseModel model) throws Exception;
    int getUserLoginFlag(String login_flag)throws Exception;
    void userRegister(String account, String pwd, BaseModel model) throws Exception;
}
