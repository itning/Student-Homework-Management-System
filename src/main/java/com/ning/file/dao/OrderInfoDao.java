package com.ning.file.dao;

import com.ning.file.entity.OrderInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 作业批次DAO
 *
 * @author wangn
 * @date 2017/5/21
 */
public interface OrderInfoDao {
    /**
     * 根据作业名查找已经开启的所有作业
     *
     * @param oName 作业名
     * @return 已经开启的所有作业的集合
     */
    List<OrderInfo> getONameBySubject(String oName);

    /**
     * 根据作业名查找所有作业
     *
     * @param oName 作业名
     * @return 所有作业的集合
     */
    List<OrderInfo> getONameBySubjectOfAll(String oName);

    /**
     * 查找所有作业信息
     *
     * @return 所有作业信息集合
     */
    List<OrderInfo> getOrderInfoEntity();

    /**
     * 根据作业ID查找作业
     *
     * @param oid 作业批次ID
     * @return 作业批次信息
     */
    OrderInfo getOrderInfoEntityByOID(Integer oid);


    OrderInfo updateDeadlineByOID(Map<String, Object> map);

    /**
     * 更新作业批次信息
     * Map中的KEY可有的值
     * oname
     * osubject
     * ostate
     *
     * @param map 根据键更新值
     */
    void changeKeyByOID(Map<String, Object> map);

    /**
     * 新增作业批次
     *
     * @param orderInfo {@link OrderInfo}
     */
    void addOrderInfo(OrderInfo orderInfo);

    /**
     * 根据作业批次ID删除作业批次
     *
     * @param oid 作业批次ID
     */
    void delOrderinfoByOID(Integer oid);
}
