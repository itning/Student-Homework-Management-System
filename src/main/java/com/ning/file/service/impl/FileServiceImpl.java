package com.ning.file.service.impl;

import com.ning.exception.file.FileException;
import com.ning.file.dao.HistoryDao;
import com.ning.file.dao.OrderoInfoDao;
import com.ning.file.entity.History;
import com.ning.file.entity.OrderInfo;
import com.ning.file.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangn on 2017/5/20.
 */
@Service
public class FileServiceImpl implements FileService {
    @Resource
    private OrderoInfoDao orderoInfoDao;

    @Resource
    private HistoryDao historyDao;

    public List<OrderInfo> getOnameBysubject(String oname) throws FileException {
        return orderoInfoDao.getOnameBysubject(oname);
    }

    public Set<String> getOrderInfoEntity() throws FileException {
        List<OrderInfo> orderInfoList = orderoInfoDao.getOrderInfoEntity();
        Set<String> set = new HashSet<String>();//集合用于存储并清楚重复下拉框数据
        for (OrderInfo orderInfo : orderInfoList) {
            if(orderInfo.getOstate()){
                set.add(orderInfo.getOsubject());
            }
        }
        return set;
    }

    public OrderInfo getOrderInfoEntityByOID(Integer oid) throws FileException {
        return orderoInfoDao.getOrderInfoEntityByOID(oid);
    }

    public void insertDataByEntity(History history) {
        historyDao.insertDataByEntity(history);
    }

    public List<History> getUpListByUID(String huid) {
        return historyDao.getUpListByUID(huid);
    }

    public void delEntityByHID(String delHid) {
        historyDao.delEntityByHID(delHid);
    }

    public History getEntityByHID(String hid) {
        return historyDao.getEntityByHID(hid);
    }

    public History findHuidExists(Map<String,Object> hoidhuid) {
        return historyDao.findHuidExists(hoidhuid);
    }

    public void upHistoryData(History history) {
        historyDao.upHistoryData(history);
    }

    public void delEntityByHOID(Integer hoid) {
        historyDao.delEntityByHOID(hoid);
    }
}
