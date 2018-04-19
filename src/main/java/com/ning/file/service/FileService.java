package com.ning.file.service;

import com.ning.exception.file.FileException;
import com.ning.file.entity.History;
import com.ning.file.entity.OrderInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wangn
 * @date 2017/5/19
 */
public interface FileService {
    /**
     * @param oname
     * @return
     * @throws FileException
     */
    public List<OrderInfo> getOnameBysubject(String oname) throws FileException;

    /**
     * 集合用于存储并清楚重复下拉框数据
     *
     * @return
     * @throws FileException
     */
    public Set<String> getOrderInfoEntity() throws FileException;

    /**
     * @param oid
     * @return
     * @throws FileException
     */
    public OrderInfo getOrderInfoEntityByOID(Integer oid) throws FileException;

    /**
     * @param history
     */
    public void insertDataByEntity(History history);

    /**
     * @param huid
     * @return
     */
    public List<History> getUpListByUID(String huid);

    /**
     * @param delHid
     */
    public void delEntityByHID(String delHid);

    /**
     * @param hid
     * @return
     */
    public History getEntityByHID(String hid);

    /**
     * @param hoidhuid
     * @return
     */
    public History findHuidExists(Map<String, Object> hoidhuid);

    /**
     * @param history
     */
    public void upHistoryData(History history);

    /**
     * @param hoid
     */
    public void delEntityByHOID(Integer hoid);
}
