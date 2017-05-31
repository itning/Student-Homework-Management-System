package com.ning.login.action;

import com.ning.exception.login.LoginException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangn on 2017/5/19.
 */
@Controller
public class Login {
    @RequestMapping("login")
    public String Userlogin(Model model, HttpServletRequest request) throws LoginException {
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        if (exceptionClassName != null) {
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                model.addAttribute("returninfo","账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(
                    exceptionClassName)||AuthenticationException.class.getName().equals(exceptionClassName)) {
                model.addAttribute("returninfo","用户名/密码错误");
            } else {
                throw new LoginException(exceptionClassName);
            }
        }
        return "jsp/login.jsp";
    }
}
