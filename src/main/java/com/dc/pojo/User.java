package com.dc.pojo;

import com.dc.base.util.TimeUtil;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable {
    @ApiModelProperty(value = "用户id")
    private int user_id;
    @ApiModelProperty(value = "账号")
    private String user_iphone;
    @ApiModelProperty(value = "密码")
    private String user_pwd;
    @ApiModelProperty(value = "临时登录标识")
    private String login_flag;
    @ApiModelProperty(value = "上一次登录时间")
    private String last_login_time;
    private String register_time;
    @ApiModelProperty(value = "用户状态")
    private int user_status;

    public String getRegister_time() {
        return register_time;
    }

    public void setRegister_time(String register_time) {
        this.register_time = TimeUtil.timeSubstr(register_time);
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_iphone() {
        return user_iphone;
    }

    public void setUser_iphone(String user_iphone) {
        this.user_iphone = user_iphone;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public String getLogin_flag() {
        return login_flag;
    }

    public void setLogin_flag(String login_flag) {
        this.login_flag = login_flag;
    }

    public String getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(String last_login_time) {
        this.last_login_time = TimeUtil.timeSubstr(last_login_time);
    }

    public int getUser_status() {
        return user_status;
    }

    public void setUser_status(int user_status) {
        this.user_status = user_status;
    }
}
