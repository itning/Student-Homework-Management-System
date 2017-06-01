package com.ning.admin.action;

import com.ning.admin.service.AdminService;
import com.ning.exception.file.FileException;
import com.ning.file.entity.History;
import com.ning.file.entity.OrderInfo;
import com.ning.file.service.FileService;
import com.ning.login.entity.User;
import com.ning.login.service.UserService;
import com.ning.util.properties.PropertiesUtil;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by wangn on 2017/5/23.
 */
@Controller
public class AdminAction {
    @Resource
    private FileService fileService;

    @Resource
    private AdminService adminService;

    @Resource
    private UserService userService;

    @RequestMapping("admin")//后台入口方法
    @RequiresPermissions("admin")//管理员权限访问
    public String admin(Model model) throws Exception {
        model.addAttribute("orderInfoList", fileService.getOrderInfoEntity());//下拉框数据
        model.addAttribute("user", SecurityUtils.getSubject().getPrincipal());//登录用户信息实体（User）
        return "jsp/admin.jsp";
    }

    @RequestMapping("subjectui")//后台入口方法
    @RequiresPermissions("admin")//管理员权限访问
    public String subjectui(Model model) throws Exception {
        model.addAttribute("allOrderInfo", adminService.getOrderInfoEntity());//OrderInfo实体集合
        return "jsp/subjectui.jsp";
    }

    @RequestMapping("getFileList")//获取已上传文件列表方法
    @RequiresPermissions("admin")//管理员权限访问
    public String getFileList(Integer hoid, Model model, HttpSession session) throws Exception {
        if (hoid == null) {
            throw new FileException("参数错误：hoid为空！");
        }
        session.setAttribute("hoid", hoid);//在Session中存储用户当前查看的文件列表
        List<History> fileListByHoid = adminService.findFileListByHoid(hoid);//所有的已上传文件实体集合
        List<User> users = userService.getUserList();//所有的学生实体集合
        List<History> list = new ArrayList<History>();//该集合用于存储所有用户上传实体信息，包括未上传文件的用户
        for (User alluser : users) {
            History history = new History();//历史记录实体
            history.setOname(alluser.getName());//将学号和姓名存入
            history.setOsubject(alluser.getUsername());
            for (History historys : fileListByHoid) {
                User user = userService.getUserEntityByID(historys.getHuid());
                if (user.getUid().equals(alluser.getUid())) {//如果数据库中已上传过文件的用户ID和用户列表中的ID相等
                    history.setFilesize(historys.getFilesize());//将数据库中信息存入历史记录实体
                    history.setHid(historys.getHid());
                    history.setUptime(historys.getUptime());
                }
            }
            list.add(history);
        }
        model.addAttribute("fileListByHoid", list);
        return "jsp/downfileui.jsp";
    }

    @RequestMapping("downAllFile")//下载所有已上传的文件
    @RequiresPermissions("admin")//该方法需要管理员权限
    public void downAllFile(HttpServletResponse response, HttpSession session) throws Exception {
        List<History> fileListByHoid = adminService.findFileListByHoid((Integer) session.getAttribute("hoid"));//所有的已上传文件实体集合
        if (fileListByHoid == null) {
            throw new FileException("下载失败：未找到数据！");
        }
        OrderInfo orderInfo = fileService.getOrderInfoEntityByOID(fileListByHoid.get(0).getHoid());
        String zipfilename = orderInfo.getOsubject() + orderInfo.getOname();
        List<String> filesname = new ArrayList<String>();
        for (History history : fileListByHoid) {
            filesname.add(history.getFilepath());
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((zipfilename + ".zip").getBytes(), "ISO-8859-1"));
        ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
        for (String filename : filesname) {
            File file = new File(PropertiesUtil.getUpLoadFilePath() + filename);
            InputStream input = new FileInputStream(file);
            zipOut.putNextEntry(new ZipEntry(file.getName()));
            IOUtils.copy(input, zipOut);
            input.close();
        }
        zipOut.close();
    }

    @RequestMapping("changeKeyByOID")
    @RequiresPermissions("admin")//该方法需要管理员权限
    public @ResponseBody
    Boolean changeKeyByOID(Integer oid, String key, String value) throws Exception {
        if (oid == null || key == null || key.equals("") || value == null || value.equals("")) {
            throw new FileException("更改失败：参数不正确");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("oid", oid);
        if (key.equals("ostate")) {
            map.put(key, Boolean.parseBoolean(value));
        } else {
            map.put(key, value);
        }
        adminService.changeKeyByOID(map);
        return true;
    }

    @RequestMapping("delOrderinfoByOID")
    @RequiresPermissions("admin")//该方法需要管理员权限
    public @ResponseBody
    Boolean delOrderinfoByOID(Integer oid) throws Exception {
        if (oid == null) {
            throw new FileException("删除失败：参数不正确");
        }
        Boolean del=true;
        List<History> historyList = adminService.findFileListByHoid(oid);
        for (History history : historyList) {
            File file = new File(PropertiesUtil.getUpLoadFilePath() + history.getFilepath());
            if(file.delete()){
                history.setFiledeleted(true);
                fileService.upHistoryData(history);
            }
            else {
                del=false;
            }
        }
        if(del){
            adminService.delOrderinfoByOID(oid);
        }
        return true;
    }

    @RequestMapping("addOrderInfo")
    @RequiresPermissions("admin")//该方法需要管理员权限
    public @ResponseBody
    Boolean addOrderInfo(OrderInfo orderInfo) throws Exception {
        if (orderInfo == null) {
            throw new FileException("添加失败：参数为空");
        }
        if (orderInfo.getOsubject() == null || orderInfo.getOsubject().equals("")) {
            return false;
        }
        if (orderInfo.getOname() == null || orderInfo.getOsubject().equals("")) {
            return false;
        }
        if (orderInfo.getOstate() == null) {
            return false;
        }
        Integer oid = (orderInfo.getOname().hashCode()) + (orderInfo.getOsubject().hashCode());
        orderInfo.setOid(oid);
        orderInfo.setOtime(new Date());
        adminService.addOrderInfo(orderInfo);
        return true;
    }
}
