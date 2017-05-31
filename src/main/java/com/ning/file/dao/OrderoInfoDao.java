package com.ning.file.dao;

import com.ning.exception.file.FileException;
import com.ning.file.entity.OrderInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by wangn on 2017/5/21.
 */
public interface OrderoInfoDao {
    public List<OrderInfo> getOnameBysubject(String oname) throws FileException;

    public List<OrderInfo> getOrderInfoEntity() throws FileException;

    public OrderInfo getOrderInfoEntityByOID(Integer oid) throws FileException;

    public void changeKeyByOID(Map<String,Object> map);

    public void addOrderInfo(OrderInfo orderInfo);

    public void delOrderinfoByOID(Integer oid);
}
