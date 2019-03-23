package com.ning.login.service;

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
 * @author wangn
 * @date 2017/5/19
 */
public class CustomRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    /**
     * 授权
     *
     * @param principals {@link PrincipalCollection}
     * @return {@link AuthorizationInfo}
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermission(user.getPercode());
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     *
     * @param token {@link AuthenticationToken}
     * @return {@link AuthenticationInfo}
     * @throws AuthenticationException AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = userService.getPasswd(username);
        User user = userService.getUserEntity(username);
        return new SimpleAuthenticationInfo(user, password, "customrealm");
    }
}
