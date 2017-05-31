package com.ning.admin.service.impl;

import com.ning.admin.service.AdminService;
import com.ning.exception.file.FileException;
import com.ning.file.dao.HistoryDao;
import com.ning.file.dao.OrderoInfoDao;
import com.ning.file.entity.History;
import com.ning.file.entity.OrderInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by wangn on 2017/5/24.
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private HistoryDao historyDao;

    @Resource
    private OrderoInfoDao orderoInfoDao;

    //所有的已上传文件实体集合
    public List<History> findFileListByHoid(Integer hoid) {
        return historyDao.findFileListByHoid(hoid);
    }

    public List<OrderInfo> getOrderInfoEntity() throws Exception {
        return orderoInfoDao.getOrderInfoEntity();
    }

    public void changeKeyByOID(Map<String, Object> map) {
        orderoInfoDao.changeKeyByOID(map);
    }

    public void addOrderInfo(OrderInfo orderInfo) {
        orderoInfoDao.addOrderInfo(orderInfo);
    }

    public void delOrderinfoByOID(Integer oid) {
        orderoInfoDao.delOrderinfoByOID(oid);
    }
}
