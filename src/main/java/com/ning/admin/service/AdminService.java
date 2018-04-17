package com.ning.admin.service;

import com.ning.file.entity.History;
import com.ning.file.entity.OrderInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by wangn on 2017/5/24.
 */
public interface AdminService {
    //所有的已上传文件实体集合
    public List<History> findFileListByHoid(Integer hoid);

    public List<OrderInfo> getOrderInfoEntity() throws Exception;

    public void changeKeyByOID(Map<String, Object> map);

    public void addOrderInfo(OrderInfo orderInfo);

    public void delOrderinfoByOID(Integer oid);
}
