package com.ning.login.service.impl;

import com.ning.exception.login.LoginException;
import com.ning.login.dao.UserDao;
import com.ning.login.entity.User;
import com.ning.login.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by wangn on 2017/5/19.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    public String getPasswd(String username) throws LoginException {
        String password = userDao.getPasswd(username);
        if (password == null && password.equals("")) {
            throw new LoginException("UserServiceImpl：getPasswd：密码不存在");
        }
        return password;
    }

    public String getPasswdById(String uid) throws LoginException {
        return userDao.getPasswdById(uid);
    }

    public boolean isFirstLogin(String uid) throws LoginException {
        return userDao.isFirstLogin(uid);
    }

    public User getUserEntity(String username) throws LoginException {
        return userDao.getUserEntity(username);
    }

    public void setUserPasswd(Map<String,String> map) throws LoginException {
        userDao.setUserPasswd(map);
    }

    public void setFirstLogin(Map<String,Object> isfirstlogin) throws LoginException {
        userDao.setFirstLogin(isfirstlogin);
    }

    public User getUserEntityByID(String uid) {
        return userDao.getUserEntityByID(uid);
    }

    public List<User> getUserList() {
        return userDao.getUserList();
    }

}
