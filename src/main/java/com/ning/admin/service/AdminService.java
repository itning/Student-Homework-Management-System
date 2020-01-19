package com.ning.admin.service;

import com.ning.file.entity.History;
import com.ning.file.entity.OrderInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 管理员服务接口
 *
 * @author wangn
 * @date 2017/5/24
 */
public interface AdminService {
    /**
     * 获取所有的已上传文件实体集合
     *
     * @param hoid {@link History }实体的ID
     * @return 上传历史集合
     * @see History
     */
    List<History> findFileListByHoid(Integer hoid);

    /**
     * 获取所有课程名称信息
     *
     * @return 所有课程名称信息集合
     */
    List<OrderInfo> getOrderInfoEntity();



    void updateDeadlineByOID(Map<String, Object> map);

    /**
     * 更新状态
     *
     * @param map map
     */
    void changeKeyByOID(Map<String, Object> map);

    void updateOrderByOID(Map<String, Object> map);

    /**
     * 添加课程名称
     *
     * @param orderInfo 课程名称实体
     */
    void addOrderInfo(OrderInfo orderInfo);

    /**
     * 删除课程名称
     *
     * @param oid 课程名称ID
     */
    void delOrderinfoByOID(Integer oid);

    /**
     * 根据作业ID查找所有的已上传文件实体集合
     *
     * @param hoid 作业ID
     * @return 上传历史集合
     */
    List<History> getAllUploadedByHoid(int hoid);

    /**
     * 根据名称ID删除名称
     *
     * @param oid 名称ID
     * @throws Exception Exception
     */
    void delOrderInfosAndFilesByOID(int oid) throws Exception;
}
