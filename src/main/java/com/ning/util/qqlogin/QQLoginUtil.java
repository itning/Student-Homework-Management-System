package com.ning.util.qqlogin;

import com.ning.exception.login.LoginException;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author wangn
 * @date 2017/5/31
 */
public class QQLoginUtil {
    public static String getUserOpenID(HttpServletRequest request) throws LoginException {
        String openID;
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            if ("".equals(accessTokenObj.getAccessToken())) {
                throw new LoginException("未获取到参数！");
            } else {
                openID = new OpenID(accessTokenObj.getAccessToken()).getUserOpenID();
            }
        } catch (QQConnectException ignored) {
            throw new LoginException(ignored.getMessage());
        }
        return openID;
    }
}
