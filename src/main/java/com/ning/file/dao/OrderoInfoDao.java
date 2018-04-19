package com.ning.file.dao;

import com.ning.exception.file.FileException;
import com.ning.file.entity.OrderInfo;

import java.util.List;
import java.util.Map;

/**
 * @author wangn
 * @date 2017/5/21
 */
public interface OrderoInfoDao {
    /**
     * @param oname
     * @return
     * @throws FileException
     */
    public List<OrderInfo> getOnameBysubject(String oname) throws FileException;

    /**
     * @return
     * @throws FileException
     */
    public List<OrderInfo> getOrderInfoEntity() throws FileException;

    /**
     * @param oid
     * @return
     * @throws FileException
     */
    public OrderInfo getOrderInfoEntityByOID(Integer oid) throws FileException;

    /**
     * @param map
     */
    public void changeKeyByOID(Map<String, Object> map);

    /**
     * @param orderInfo
     */
    public void addOrderInfo(OrderInfo orderInfo);

    /**
     * @param oid
     */
    public void delOrderinfoByOID(Integer oid);
}
