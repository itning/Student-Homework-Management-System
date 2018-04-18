package com.ning.admin.service;

import com.ning.file.entity.History;
import com.ning.file.entity.OrderInfo;

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
     * 所有的已上传文件实体集合
     *
     * @param hoid 科目和批次ID
     * @return 上传历史集合
     */
    List<History> findFileListByHoid(Integer hoid);

    /**
     * 获取所有科目批次信息
     *
     * @return 所有科目批次信息集合
     * @throws Exception Exception
     */
    List<OrderInfo> getOrderInfoEntity() throws Exception;

    /**
     * 更新状态
     *
     * @param map map
     */
    void changeKeyByOID(Map<String, Object> map);

    /**
     * 添加科目批次
     *
     * @param orderInfo 科目批次实体
     */
    void addOrderInfo(OrderInfo orderInfo);

    /**
     * 删除科目批次
     *
     * @param oid 科目批次ID
     */
    void delOrderinfoByOID(Integer oid);
}
