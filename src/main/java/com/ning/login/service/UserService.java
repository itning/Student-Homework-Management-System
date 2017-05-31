package com.ning.login.service;

import com.ning.exception.login.LoginException;
import com.ning.login.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by wangn on 2017/5/19.
 */
public interface UserService {
    /*根据用户名获取密码*/
    public String getPasswd(String username) throws LoginException;

    /*根据ID获取密码*/
    public String getPasswdById(String uid) throws LoginException;

    /*获取是否为第一次登陆*/
    public boolean isFirstLogin(String uid) throws LoginException;

    /*封装用户信息*/
    public User getUserEntity(String username) throws LoginException;

    /*修改密码*/
    public void setUserPasswd(Map<String,String> map) throws LoginException;

    /*设置登陆标记*/
    public void setFirstLogin(Map<String,Object> isfirstlogin) throws LoginException;

    public User getUserEntityByID(String uid);

    public List<User> getUserList();

    public User getUserEntityByOpenID(String userOpenID);

    public void insertQQIDByUID(User user);
}
