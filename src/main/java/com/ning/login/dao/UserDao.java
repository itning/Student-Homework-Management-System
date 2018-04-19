package com.ning.login.dao;

import com.ning.login.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author wangn
 * @date 2017/5/19
 */
public interface UserDao {
    /**
     * 根据用户名获取密码
     *
     * @param username
     * @return
     */
    public String getPasswd(String username);

    /**
     * 根据ID获取密码
     *
     * @param uid
     * @return
     */
    public String getPasswdById(String uid);

    /**
     * 获取是否为第一次登陆
     *
     * @param uid
     * @return
     */
    public boolean isFirstLogin(String uid);

    /**
     * 封装用户信息
     *
     * @param username
     * @return
     */
    public User getUserEntity(String username);

    /**
     * 修改密码
     *
     * @param map
     */
    public void setUserPasswd(Map<String, String> map);

    /**
     * 设置登陆标记
     *
     * @param isfirstlogin
     */
    public void setFirstLogin(Map<String, Object> isfirstlogin);

    /**
     * @param uid
     * @return
     */
    public User getUserEntityByID(String uid);

    /**
     * @return
     */
    public List<User> getUserList();

    /**
     * @param userOpenID
     * @return
     */
    public User getUserEntityByOpenID(String userOpenID);

    /**
     * @param user
     */
    public void insertQQIDByUID(User user);
}
