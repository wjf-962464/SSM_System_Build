package com.dc.service.impl;

import com.dc.base.pojo.BaseModel;
import com.dc.base.util.Md5Code;
import com.dc.dao.UserDao;
import com.dc.pojo.User;
import com.dc.service.UserService;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public void userLogin(String account, String pwd, BaseModel model) throws Exception {
        User user=userDao.userLogin(account);
        if(user==null){
            model.setResultCode(0);
            model.setMessage("该用户不存在，请检查账号是否正确");
            return;
        }
        if(user.getUser_status()!=1){
            model.setResultCode(0);
            model.setMessage("账号存在非法操作，已被封停");
            return;
        }
        if(!user.getUser_pwd().equals(pwd)){
            model.setResultCode(0);
            model.setMessage("密码错误，请检查账号或密码是否正确");
            return;
        }
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        if(user.getLast_login_time()==null){
            generateLoginFlag(user);
            model.setResultCode(1);
            model.setMessage("近期首次登陆");
            model.setData(user);
            userDao.upUserLogin(user);
            return;
        }
        DateTime last_time=DateTime.parse(user.getLast_login_time(),format);
        DateTime now_time=new DateTime();
        int days= Days.daysBetween(last_time,now_time).getDays();
        if(days>10){
            model.setResultCode(0);
            model.setMessage("长时间未登录，请重新登录");
            userDao.clearLastLoginTime(user.getUser_id());
            return;
        }
        generateLoginFlag(user);
        model.setResultCode(1);
        model.setMessage("登陆成功");
        model.setData(user);
        userDao.upUserLogin(user);
        return;
    }

    @Override
    public int getUserLoginFlag(String login_flag) throws Exception {
        User user=userDao.findUserByLoginFlag(login_flag);
        if (user==null){
            return -1;
        }
        return user.getUser_id();
    }

    public void userRegister(String account, String pwd, BaseModel model) throws Exception {
        if(account.isEmpty()||pwd.isEmpty()){
            model.setResultCode(0);
            model.setMessage("账号或密码不能为空");
            return;
        }
        if(account.matches("/^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/")){
            model.setResultCode(0);
            model.setMessage("请填写符合规范的手机号");
            return;
        }
        if(userDao.userLogin(account)!=null){
            model.setResultCode(0);
            model.setMessage("该账号已被注册");
            return;
        }
        userDao.userRegister(account,pwd);
        model.setResultCode(1);
        model.setMessage("注册成功");
    }
    private void generateLoginFlag(User user){
        long timeStamp=System.currentTimeMillis();
        String time=Md5Code.getMD5(String.valueOf(timeStamp),false,16);
        String phone=Md5Code.getMD5(user.getUser_iphone(),false,16);
        System.out.println("sqltest："+time+"位数"+time.length());
        System.out.println("sqltest："+ phone);
        String loginFlag=Md5Code.getMD5(time+phone,false,32);
        System.out.println("sqltest："+loginFlag);
        user.setLogin_flag(loginFlag);
    }
}
