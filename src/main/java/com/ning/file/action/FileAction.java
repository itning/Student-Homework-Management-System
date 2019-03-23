package com.ning.file.action;

import com.ning.exception.file.FileException;
import com.ning.exception.login.LoginException;
import com.ning.file.entity.History;
import com.ning.file.entity.OrderInfo;
import com.ning.file.service.FileService;
import com.ning.login.entity.User;
import com.ning.login.service.UserService;
import com.ning.util.properties.PropertiesUtil;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件Action
 * @author wangn
 * @date 2017/5/19
 */
@Controller
public class FileAction {
    private static final String ADMIN = "admin";
    private static final String FIRST_LOGIN_VALUE = "1";

    @Resource
    private UserService userService;

    @Resource
    private FileService fileService;

    /**
     * 自定义类型转换器
     *
     * @param binder {@link WebDataBinder}
     * @throws Exception Exception
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    }

    /**
     * 文件上传主页入口方法
     *
     * @param model {@link Model}
     * @return JSP页面
     * @throws Exception Exception
     */
    @RequestMapping("fileupload")
    public String index(Model model) throws Exception {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (user.getPercode().equals(ADMIN)) {
            return ADMIN;
        }
        boolean firstLogin = userService.isFirstLogin(user.getUid());
        if (firstLogin) {
            return "jsp/firstpd.jsp";
        }
        //用户上传历史实体
        List<History> userHistoryList = fileService.getUserHistoryByUserId(user.getUid());
        //下拉框数据
        model.addAttribute("orderInfoList", fileService.getOrderInfoEntity());
        model.addAttribute("user", user);
        model.addAttribute("userHistoryList", userHistoryList);
        return "jsp/fileupload.jsp";
    }

    /**
     * 更改密码方法
     *
     * @param model      {@link Model}
     * @param password   新密码
     * @param firstlogin 如果是第一次登陆，该值为<code>1</code>
     * @param session    {@link HttpSession}
     * @return JSP页面
     * @throws Exception Exception
     */
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public String changePassword(Model model, String password, String firstlogin, HttpSession session) throws Exception {
        if (password == null || "".equals(password)) {
            throw new LoginException("修改密码失败：参数为空");
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        // 条件判断 开始
        if (password.length() < 8) {
            model.addAttribute("user", user);
            model.addAttribute("errorinfo", "密码不能小于8位！");
            if (FIRST_LOGIN_VALUE.equals(firstlogin)) {
                return "jsp/firstpd.jsp";
            }
            return "jsp/cpasswd.jsp";
        }
        String uid = user.getUid();
        Map<String, String> map = new HashMap<>(2);
        map.put("uid", uid);
        map.put("password", password);
        String passwdById = userService.getPasswdById(uid);
        if (passwdById.equals(password)) {
            model.addAttribute("errorinfo", "新密码不能和原密码相同，请重新输入！");
            if (FIRST_LOGIN_VALUE.equals(firstlogin)) {
                return "jsp/firstpd.jsp";
            }
            return "jsp/cpasswd.jsp";
        }
        // 条件判断 结束

        userService.setUserPasswd(map);
        if (FIRST_LOGIN_VALUE.equals(firstlogin)) {
            Map<String, Object> isfirst = new HashMap<>(2);
            isfirst.put("uid", uid);
            isfirst.put("isfirst", false);
            userService.setFirstLogin(isfirst);
            session.removeAttribute("uid");
        }
        return "fileupload";
    }

    /**
     * 更改密码入口方法
     *
     * @param model {@link Model}
     * @return jsp/cpasswd.jsp
     */
    @RequestMapping("cpasswd")
    public String cpasswd(Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        return "jsp/cpasswd.jsp";
    }

    /**
     * 根据科目名查找所有科目作业信息
     *
     * @param subject 科目名
     * @return 科目作业信息集合
     * @throws Exception Exception
     */
    @RequestMapping("getOnameBysubject")
    @ResponseBody
    public List<OrderInfo> getOnameBysubject(String subject) throws Exception {
        if (subject == null || "".equals(subject)) {
            throw new FileException("获取失败：参数错误");
        }
        return fileService.getOnameBysubject(subject);
    }

    /**
     * 文件上传方法
     *
     * @param file {@link MultipartFile}
     * @return index.jsp
     * @throws Exception Exception
     */
    @RequestMapping("fileup")
    public String upfileByID(MultipartFile[] file) throws Exception {
        if (file == null) {
            throw new FileException("上传失败：未获取到上传内容！");
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        for (MultipartFile file1 : file) {
            if (user.getUserSelectOid() != null && !(file1.isEmpty())) {
                fileService.uploadFile(file1, user);
            }
        }
        return "index.jsp";
    }

    /**
     * 设置用户选择上传的科目ID
     *
     * @param userSelectOid 科目批次ID
     * @return 设置成功返回<code>true</code>
     */
    @RequestMapping("userselect")
    @ResponseBody
    public boolean userSelect(@RequestParam("userselect_oid") Integer userSelectOid) {
        if (userSelectOid != null) {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            user.setUserSelectOid(userSelectOid);
            return user.getUserSelectOid() != null;
        }
        return false;
    }

    /**
     * 删除文件方法
     *
     * @param delHid 历史记录ID
     * @return 删除是否成功
     * @throws Exception Exception
     */
    @RequestMapping("delEntityByHID")
    @ResponseBody
    public boolean delEntityByHID(String delHid) throws Exception {
        if (delHid == null || "".equals(delHid)) {
            throw new FileException("删除失败：参数为空");
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return fileService.deleteFile(user, delHid);
    }

    /**
     * 下载作业
     *
     * @param hid      科目批次ID
     * @param response {@link HttpServletResponse}
     * @throws Exception Exception
     */
    @RequestMapping("downFile")
    public void downLoadFile(String hid, HttpServletResponse response) throws Exception {
        if (hid == null || "".equals(hid)) {
            throw new FileException("下载失败：参数为空！");
        }
        History history = fileService.getEntityByHID(hid);
        String filename = PropertiesUtil.getUpLoadFilePath() + history.getFilepath();
        File file = new File(filename);
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(history.getFilepath().getBytes(), StandardCharsets.ISO_8859_1));
        response.setHeader("Content-Length", file.length() + "");
        response.setContentType(history.getType());
        try (OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
             InputStream fis = new BufferedInputStream(new FileInputStream(file))) {
            IOUtils.copy(fis, toClient);
            toClient.flush();
        } catch (Exception e) {
            if (!e.getMessage().contains("连接")) {
                throw e;
            }
        }
    }

}