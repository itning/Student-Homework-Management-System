package com.ning.login.service;

import com.ning.exception.login.LoginException;
import com.ning.login.entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * Created by wangn on 2017/5/19.
 */
public class CustomRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    /*授权*/
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermission(user.getPercode());
        return simpleAuthorizationInfo;
    }

    /*认证*/
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = null;
        User user = new User();
        try {
            password = userService.getPasswd(username);
            user = userService.getUserEntity(username);
        } catch (LoginException e) {
            e.printStackTrace();
        }
        return new SimpleAuthenticationInfo(user, password, "customrealm");
    }
}
