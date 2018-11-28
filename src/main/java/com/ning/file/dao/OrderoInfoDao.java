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
    List<OrderInfo> getOnameBysubject(String oname) throws FileException;

    /**
     * @param oname
     * @return
     * @throws FileException
     */
    List<OrderInfo> getOnameBysubjectOfAll(String oname) throws FileException;

    /**
     * @return
     * @throws FileException
     */
    List<OrderInfo> getOrderInfoEntity() throws FileException;

    /**
     * @param oid
     * @return
     * @throws FileException
     */
    OrderInfo getOrderInfoEntityByOID(Integer oid) throws FileException;

    /**
     * @param map
     */
    void changeKeyByOID(Map<String, Object> map);

    /**
     * @param orderInfo
     */
    void addOrderInfo(OrderInfo orderInfo);

    /**
     * @param oid
     */
    void delOrderinfoByOID(Integer oid);
}
