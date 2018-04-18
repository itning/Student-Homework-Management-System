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

    /**
     * 所有的已上传文件实体集合
     *
     * @param hoid 科目和批次ID
     * @return 上传历史集合
     */
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
