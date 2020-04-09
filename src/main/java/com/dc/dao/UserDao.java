package com.dc.dao;



import com.dc.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    User userLogin(@Param("account") String account);
    User findUserByLoginFlag(@Param("login_flag")String login_flag);
    void upUserLogin(User user);
    void clearLastLoginTime(@Param("id") int user_id);
    void userRegister(@Param("account")String account,@Param("pwd")String pwd);
}
