package com.ning.login.service;

import com.ning.exception.login.LoginException;
import com.ning.login.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author wangn
 * @date 2017/5/19
 */
public interface UserService {
    /**
     * 根据用户名获取密码
     *
     * @param username
     * @return
     * @throws LoginException
     */
    String getPasswd(String username) throws LoginException;

    /**
     * 根据ID获取密码
     *
     * @param uid
     * @return
     * @throws LoginException
     */
    String getPasswdById(String uid) throws LoginException;

    /**
     * 获取是否为第一次登陆
     *
     * @param uid
     * @return
     * @throws LoginException
     */
    boolean isFirstLogin(String uid) throws LoginException;

    /**
     * 封装用户信息
     *
     * @param username
     * @return
     * @throws LoginException
     */
    User getUserEntity(String username) throws LoginException;

    /**
     * 修改密码
     *
     * @param map
     * @throws LoginException
     */
    void setUserPasswd(Map<String, String> map) throws LoginException;

    /**
     * 设置登陆标记
     *
     * @param isfirstlogin
     * @throws LoginException
     */
    void setFirstLogin(Map<String, Object> isfirstlogin) throws LoginException;

    /**
     * @param uid
     * @return
     */
    User getUserEntityByID(String uid);

    /**
     * @return
     */
    List<User> getUserList();

    /**
     * @param userOpenID
     * @return
     */
    User getUserEntityByOpenID(String userOpenID);

    /**
     * @param user
     */
    void insertQQIDByUID(User user);
}
