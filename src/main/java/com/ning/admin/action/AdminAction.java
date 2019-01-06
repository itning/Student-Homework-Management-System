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
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 管理员Action
 *
 * @author wangn
 * @date 2017/5/23
 */
@Controller
public class AdminAction {
    /**
     * 科目批次状态名
     */
    private static final String O_STATE = "ostate";

    @Resource
    private FileService fileService;

    @Resource
    private AdminService adminService;

    @Resource
    private UserService userService;

    /**
     * 后台入口方法
     * 管理员权限访问
     *
     * @param model 模型
     * @return jsp/admin.jsp
     * @throws Exception Exception
     */
    @RequestMapping("admin")
    @RequiresPermissions("admin")
    public String admin(Model model) throws Exception {
        //下拉框数据
        model.addAttribute("orderInfoList", fileService.getOrderInfoEntityOfAll());
        //登录用户信息实体（User）
        model.addAttribute("user", SecurityUtils.getSubject().getPrincipal());
        return "jsp/admin.jsp";
    }

    @RequestMapping("getOnameBysubjectOfAll")
    public @ResponseBody
    List<OrderInfo> getOnameBysubjectOfAll(String subject) throws Exception {
        if (subject == null || "".equals(subject)) {
            throw new FileException("获取失败：参数错误");
        }
        return fileService.getOnameBysubjectOfAll(subject);
    }

    /**
     * 后台入口方法
     * 管理员权限访问
     *
     * @param model 模型
     * @return jsp/subjectui.jsp
     * @throws Exception Exception
     */
    @RequestMapping("subjectui")
    @RequiresPermissions("admin")
    public String subjectui(Model model) throws Exception {
        //OrderInfo实体集合
        model.addAttribute("allOrderInfo", adminService.getOrderInfoEntity());
        return "jsp/subjectui.jsp";
    }

    /**
     * 获取已上传文件列表方法
     * 管理员权限访问
     *
     * @param hoid    作业批次ID
     * @param model   模型
     * @param session HttpSession
     * @return jsp/downfileui.jsp
     * @throws Exception Exception
     */
    @RequestMapping("getFileList")
    @RequiresPermissions("admin")
    public String getFileList(Integer hoid, Model model, HttpSession session) throws Exception {
        if (hoid == null) {
            throw new FileException("参数错误：hoid为空！");
        }
        //在Session中存储用户当前查看的文件列表
        session.setAttribute("hoid", hoid);
        //所有的已上传文件实体集合
        List<History> fileListByHoid = adminService.findFileListByHoid(hoid);
        //所有的学生实体集合
        List<User> users = userService.getUserList();
        //该集合用于存储所有用户上传实体信息，包括未上传文件的用户
        List<History> list = new ArrayList<>();
        for (User alluser : users) {
            //历史记录实体
            History history = new History();
            //将学号和姓名存入
            history.setOname(alluser.getName());
            history.setOsubject(alluser.getUsername());
            for (History historys : fileListByHoid) {
                User user = userService.getUserEntityByID(historys.getHuid());
                //如果数据库中已上传过文件的用户ID和用户列表中的ID相等
                if (user.getUid().equals(alluser.getUid())) {
                    //将数据库中信息存入历史记录实体
                    history.setFilesize(historys.getFilesize());
                    history.setHid(historys.getHid());
                    history.setUptime(historys.getUptime());
                }
            }
            list.add(history);
        }
        model.addAttribute("fileListByHoid", list);
        return "jsp/downfileui.jsp";
    }

    /**
     * 下载所有已上传的文件
     * 该方法需要管理员权限
     *
     * @param response HttpServletResponse
     * @param session  HttpSession
     * @throws Exception Exception
     */
    @RequestMapping("downAllFile")
    @RequiresPermissions("admin")
    public void downAllFile(HttpServletResponse response, HttpSession session) throws Exception {
        //所有的已上传文件实体集合
        List<History> fileListByHoid = adminService.findFileListByHoid((Integer) session.getAttribute("hoid"));
        if (fileListByHoid == null) {
            throw new FileException("下载失败：未找到数据！");
        }
        OrderInfo orderInfo = fileService.getOrderInfoEntityByOID(fileListByHoid.get(0).getHoid());
        String zipfilename = orderInfo.getOsubject() + orderInfo.getOname();
        List<String> filesname = new ArrayList<>();
        for (History history : fileListByHoid) {
            filesname.add(history.getFilepath());
        }
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((zipfilename + ".zip").getBytes(), StandardCharsets.ISO_8859_1));
        response.setContentType("application/octet-stream");
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

    /**
     * 更改科目批次启用状态
     * 该方法需要管理员权限
     *
     * @param oid   科目批次ID
     * @param key   key
     * @param value value
     * @return 更改是否成功
     * @throws Exception Exception
     */
    @RequestMapping("changeKeyByOID")
    @RequiresPermissions("admin")
    public @ResponseBody
    Boolean changeKeyByOID(Integer oid, String key, String value) throws Exception {
        if (oid == null || key == null || "".equals(key) || value == null || "".equals(value)) {
            throw new FileException("更改失败：参数不正确");
        }
        Map<String, Object> map = new HashMap<>(16);
        map.put("oid", oid);
        if (O_STATE.equals(key)) {
            map.put(key, Boolean.parseBoolean(value));
        } else {
            map.put(key, value);
        }
        adminService.changeKeyByOID(map);
        return true;
    }

    /**
     * 根据批次ID删除批次
     * 该方法需要管理员权限
     *
     * @param oid 批次ID
     * @return ResponseBody 是否删除成功
     * @throws Exception Exception
     */
    @RequestMapping("delOrderinfoByOID")
    @RequiresPermissions("admin")
    public @ResponseBody
    Boolean delOrderinfoByOID(Integer oid) throws Exception {
        if (oid == null) {
            throw new FileException("删除失败：参数不正确");
        }
        List<History> historyList = adminService.findFileListByHoid(oid);
        boolean delete = false;
        for (History history : historyList) {
            File file = new File(PropertiesUtil.getUpLoadFilePath() + history.getFilepath());
            if (file.exists()) {
                delete = file.delete();
            }
        }
        //删除历史记录
        fileService.delEntityByHOID(oid);
        //删除科目
        adminService.delOrderinfoByOID(oid);
        if (delete) {
            return true;
        }
        return true;
    }

    /**
     * 添加科目批次信息
     * 该方法需要管理员权限
     *
     * @param orderInfo 科目批次实体
     * @return 是否添加成功
     * @throws Exception Exception
     */
    @RequestMapping("addOrderInfo")
    @RequiresPermissions("admin")
    public @ResponseBody
    Boolean addOrderInfo(OrderInfo orderInfo) throws Exception {
        if (orderInfo == null) {
            throw new FileException("添加失败：参数为空");
        }
        if (orderInfo.getOsubject() == null || "".equals(orderInfo.getOsubject())) {
            return false;
        }
        if (orderInfo.getOname() == null || "".equals(orderInfo.getOsubject())) {
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
