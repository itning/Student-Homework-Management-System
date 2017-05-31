package com.ning.login.action;

import com.ning.exception.login.LoginException;
import com.ning.login.entity.User;
import com.ning.login.service.UserService;
import com.ning.util.qqlogin.QQLoginUtil;
import com.qq.connect.QQConnectException;
import com.qq.connect.oauth.Oauth;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangn on 2017/5/19.
 */
@Controller
public class Login {
    @Resource
    private UserService userService;

    @RequestMapping("login")
    public String Userlogin(Model model, HttpServletRequest request) throws LoginException {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(user!=null&&user.getUid()!=null){
            return "redirect:index.jsp";
        }
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

    @RequestMapping("qqLogin")
    public String qqLogin(HttpServletRequest request) throws QQConnectException {
        return "redirect:"+new Oauth().getAuthorizeURL(request);
    }

    @RequestMapping("qqLoginAfter")
    public String qqLoginAfter(HttpServletRequest request) throws LoginException {
        String userOpenID = QQLoginUtil.getUserOpenID(request);
        if(userOpenID==null){
            throw new LoginException("userOpenID==null");
        }
        User userByopenID = userService.getUserEntityByOpenID(userOpenID);
        if(userByopenID==null){
            request.getSession().setAttribute("userOpenID",userOpenID);
            return "jsp/BindQQ.jsp";
        }else {
            Subject currentUser = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userByopenID.getUsername(),userByopenID.getPassword(), false,request.getRemoteAddr());
            currentUser.login(token);
        }
        return "index.jsp";
    }

    @RequestMapping("bindQQ")
    public String bindQQ(String username,String password,Model model,HttpServletRequest request) throws LoginException {
        String userOpenID= (String) request.getSession().getAttribute("userOpenID");
        String passwd = userService.getPasswd(username);
        if(passwd==null){
            model.addAttribute("returninfo","输入的学号不存在，请重试！");
            return "jsp/BindQQ.jsp";
        }
        if(!(passwd.equals(password))){
            model.addAttribute("returninfo","密码错误，请重试！");
            return "jsp/BindQQ.jsp";
        }
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password, false,request.getRemoteAddr());
        currentUser.login(token);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        user.setUserOpenID(userOpenID);
        userService.insertQQIDByUID(user);
        return "index.jsp";
    }
}
