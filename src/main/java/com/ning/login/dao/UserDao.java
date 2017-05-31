package com.ning.login.dao;

import com.ning.login.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by wangn on 2017/5/19.
 */
public interface UserDao {
    /*根据用户名获取密码*/
    public String getPasswd(String username);

    /*根据ID获取密码*/
    public String getPasswdById(String uid);

    /*获取是否为第一次登陆*/
    public boolean isFirstLogin(String uid);

    /*封装用户信息*/
    public User getUserEntity(String username);

    /*修改密码*/
    public void setUserPasswd(Map<String,String> map);

    /*设置登陆标记*/
    public void setFirstLogin(Map<String,Object> isfirstlogin);

    public User getUserEntityByID(String uid);

    public List<User> getUserList();
}
