package com.ning.util.qqlogin;

import com.ning.exception.login.LoginException;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangn on 2017/5/31.
 */
public class QQLoginUtil {
    public static String getUserOpenID(HttpServletRequest request) throws LoginException {
        String accessToken = null, openID = null;
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            long tokenExpireIn = 0L;
            if (accessTokenObj.getAccessToken().equals("")) {
//                我们的网站被CSRF攻击了或者用户取消了授权
//                做一些数据统计工作
                System.out.print("没有获取到响应参数");
            } else {
                accessToken = accessTokenObj.getAccessToken();
                tokenExpireIn = accessTokenObj.getExpireIn();
                request.getSession().setAttribute("demo_access_token", accessToken);
                request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));
                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj = new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();
                System.out.println(openID);
                request.getSession().setAttribute("demo_openid", openID);
            }
        } catch (QQConnectException ignored) {
            throw new LoginException(ignored.getMessage());
        }
        return openID;
    }
}
