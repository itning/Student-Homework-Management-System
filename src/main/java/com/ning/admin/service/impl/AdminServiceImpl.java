package com.ning.admin.service.impl;

import com.ning.admin.service.AdminService;
import com.ning.file.dao.HistoryDao;
import com.ning.file.dao.OrderoInfoDao;
import com.ning.file.entity.History;
import com.ning.file.entity.OrderInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 管理员服务接口实现
 *
 * @author wangn
 * @date 2017/5/24
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private HistoryDao historyDao;

    @Resource
    private OrderoInfoDao orderoInfoDao;


    @Override
    public List<History> findFileListByHoid(Integer hoid) {
        return historyDao.findFileListByHoid(hoid);
    }

    @Override
    public List<OrderInfo> getOrderInfoEntity() throws Exception {
        return orderoInfoDao.getOrderInfoEntity();
    }

    @Override
    public void changeKeyByOID(Map<String, Object> map) {
        orderoInfoDao.changeKeyByOID(map);
    }

    @Override
    public void addOrderInfo(OrderInfo orderInfo) {
        orderoInfoDao.addOrderInfo(orderInfo);
    }

    @Override
    public void delOrderinfoByOID(Integer oid) {
        orderoInfoDao.delOrderinfoByOID(oid);
    }
}
