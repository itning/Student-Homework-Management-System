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
 * @author wangn
 * @date 2017/5/19
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public String getPasswd(String username) throws LoginException {
        return userDao.getPasswd(username);
    }

    @Override
    public String getPasswdById(String uid) throws LoginException {
        return userDao.getPasswdById(uid);
    }

    @Override
    public boolean isFirstLogin(String uid) throws LoginException {
        return userDao.isFirstLogin(uid);
    }

    @Override
    public User getUserEntity(String username) throws LoginException {
        return userDao.getUserEntity(username);
    }

    @Override
    public void setUserPasswd(Map<String, String> map) throws LoginException {
        userDao.setUserPasswd(map);
    }

    @Override
    public void setFirstLogin(Map<String, Object> isfirstlogin) throws LoginException {
        userDao.setFirstLogin(isfirstlogin);
    }

    @Override
    public User getUserEntityByID(String uid) {
        return userDao.getUserEntityByID(uid);
    }

    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    @Override
    public User getUserEntityByOpenID(String userOpenID) {
        return userDao.getUserEntityByOpenID(userOpenID);
    }

    @Override
    public void insertQQIDByUID(User user) {
        userDao.insertQQIDByUID(user);
    }

}
