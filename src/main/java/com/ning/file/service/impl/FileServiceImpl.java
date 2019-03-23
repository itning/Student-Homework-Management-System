package com.ning.file.service.impl;

import com.ning.exception.file.FileException;
import com.ning.file.dao.HistoryDao;
import com.ning.file.dao.OrderInfoDao;
import com.ning.file.entity.History;
import com.ning.file.entity.OrderInfo;
import com.ning.file.service.FileService;
import com.ning.login.entity.User;
import com.ning.util.properties.PropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by wangn on 2017/5/20.
 */
@Service
public class FileServiceImpl implements FileService {
    @Resource
    private OrderInfoDao orderInfoDao;

    @Resource
    private HistoryDao historyDao;

    @Override
    public List<OrderInfo> getONameBySubject(String oname) {
        return orderInfoDao.getONameBySubject(oname);
    }

    @Override
    public List<OrderInfo> getOnameBysubjectOfAll(String oname){
        return orderInfoDao.getONameBySubjectOfAll(oname);
    }

    @Override
    public Set<String> getOrderInfoEntity(){
        List<OrderInfo> orderInfoList = orderInfoDao.getOrderInfoEntity();
        //集合用于存储并清除重复下拉框数据
        Set<String> set = new HashSet<>();
        for (OrderInfo orderInfo : orderInfoList) {
            if (orderInfo.getOstate()) {
                set.add(orderInfo.getOsubject());
            }
        }
        return set;
    }

    @Override
    public Set<String> getOrderInfoEntityOfAll(){
        List<OrderInfo> orderInfoList = orderInfoDao.getOrderInfoEntity();
        //集合用于存储并清除重复下拉框数据
        Set<String> set = new HashSet<>();
        for (OrderInfo orderInfo : orderInfoList) {
            set.add(orderInfo.getOsubject());
        }
        return set;
    }

    @Override
    public OrderInfo getOrderInfoEntityByOID(Integer oid) {
        return orderInfoDao.getOrderInfoEntityByOID(oid);
    }

    @Override
    public void insertDataByEntity(History history) {
        historyDao.insertDataByEntity(history);
    }

    @Override
    public List<History> getUpListByUID(String huid) {
        return historyDao.getUpListByUID(huid);
    }

    @Override
    public void delEntityByHID(String delHid) {
        historyDao.delEntityByHID(delHid);
    }

    @Override
    public History getEntityByHID(String hid) {
        return historyDao.getEntityByHID(hid);
    }

    @Override
    public History findHuidExists(Map<String, Object> hoidhuid) {
        return historyDao.findHuidExists(hoidhuid);
    }

    @Override
    public void upHistoryData(History history) {
        historyDao.upHistoryData(history);
    }

    @Override
    public void delEntityByHOID(Integer hoid) {
        historyDao.delEntityByHoId(hoid);
    }

    @Override
    public List<History> getUserHistoryByUserId(String uId) {
        return this.getUpListByUID(uId).stream().peek(history -> {
            OrderInfo orderInfo = this.getOrderInfoEntityByOID(history.getHoid());
            if (orderInfo != null) {
                history.setOsubject(orderInfo.getOsubject());
                history.setOname(orderInfo.getOname());
                //设置文件扩展名
                history.setFilepath(history.getFilepath().substring(history.getFilepath().lastIndexOf(".") + 1));
            }
        }).collect(Collectors.toList());
    }

    @Override
    public void uploadFile(MultipartFile file, User user) throws Exception {
        OrderInfo orderInfo = this.getOrderInfoEntityByOID(user.getUserSelectOid());
        History history = new History();
        history.setHid(UUID.randomUUID().toString().replace("-", ""));
        history.setHuid(user.getUid());
        history.setHoid(orderInfo.getOid());
        String extensionName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String newfilename = user.getUsername() + user.getName() + orderInfo.getOsubject() + orderInfo.getOname() + extensionName;
        history.setFilepath(newfilename);
        history.setFilesize((double) file.getSize());
        history.setType(file.getContentType());
        history.setUptime(new Date());
        Map<String, Object> map = new HashMap<>(2);
        map.put("hoid", user.getUserSelectOid());
        map.put("huid", user.getUid());
        if ((this.findHuidExists(map)) != null) {
            this.delEntityByHID(this.findHuidExists(map).getHid());
        }
        this.insertDataByEntity(history);
        File newfile = new File(PropertiesUtil.getUpLoadFilePath() + newfilename);
        file.transferTo(newfile);
    }

    @Override
    public boolean deleteFile(User user, String hId) throws Exception {
        boolean isNotThisUser = true;
        List<History> historyList = this.getUpListByUID(user.getUid());
        for (History history : historyList) {
            if (history.getHid().equals(hId)) {
                isNotThisUser = false;
                break;
            }
        }
        if (isNotThisUser) {
            return false;
        }
        History history = this.getEntityByHID(hId);
        if (history == null) {
            return false;
        }
        File file = new File(PropertiesUtil.getUpLoadFilePath() + history.getFilepath());
        this.delEntityByHID(hId);
        //文件未被删除且存在
        return !file.exists() || file.delete();
    }
}
