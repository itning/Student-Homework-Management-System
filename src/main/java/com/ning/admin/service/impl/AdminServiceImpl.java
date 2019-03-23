package com.ning.admin.service.impl;

import com.ning.admin.service.AdminService;
import com.ning.file.dao.HistoryDao;
import com.ning.file.dao.OrderInfoDao;
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
    private OrderInfoDao orderInfoDao;


    @Override
    public List<History> findFileListByHoid(Integer hoid) {
        return historyDao.findFileListByHoid(hoid);
    }

    @Override
    public List<OrderInfo> getOrderInfoEntity() throws Exception {
        return orderInfoDao.getOrderInfoEntity();
    }

    @Override
    public void changeKeyByOID(Map<String, Object> map) {
        orderInfoDao.changeKeyByOID(map);
    }

    @Override
    public void addOrderInfo(OrderInfo orderInfo) {
        orderInfoDao.addOrderInfo(orderInfo);
    }

    @Override
    public void delOrderinfoByOID(Integer oid) {
        orderInfoDao.delOrderinfoByOID(oid);
    }
}
