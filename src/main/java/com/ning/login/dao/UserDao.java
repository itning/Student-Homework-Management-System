package com.ning.login.dao;

import com.ning.login.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 用户DAO
 *
 * @author wangn
 * @date 2017/5/19
 */
public interface UserDao {
    /**
     * 根据用户名获取密码
     *
     * @param username 用户名
     * @return 密码
     */
    String getPasswd(String username);

    /**
     * 根据ID获取密码
     *
     * @param uid 用户ID
     * @return 密码
     */
    String getPasswdById(String uid);

    /**
     * 获取是否为第一次登陆
     *
     * @param uid 用户ID
     * @return 第一次登陆返回<code>true</code>，否则返回<code>false</code>
     */
    boolean isFirstLogin(String uid);

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    User getUserEntity(String username);

    /**
     * 根据用户ID修改密码
     *
     * @param map 用户ID，新密码
     */
    void setUserPasswd(Map<String, String> map);

    /**
     * 根据用户ID设置登陆标记
     *
     * @param firstLogin 是第一次登陆
     */
    void setFirstLogin(Map<String, Object> firstLogin);

    /**
     * 根据用户ID返回用户信息
     *
     * @param uid 用户ID
     * @return 用户信息
     */
    User getUserEntityByID(String uid);

    /**
     * 获取所有用户
     *
     * @return 用户集合
     */
    List<User> getUserList();

    /**
     * 根据OPEN ID获取用户
     *
     * @param userOpenID OPEN ID
     * @return 用户信息
     */
    User getUserEntityByOpenID(String userOpenID);

    /**
     * 根据用户ID插入用户OPEN ID
     *
     * @param user {@link User}
     */
    void insertQQIDByUID(User user);
}
