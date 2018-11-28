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
    List<OrderInfo> getOnameBysubject(String oname) throws FileException;

    List<OrderInfo> getOnameBysubjectOfAll(String oname) throws FileException;

    /**
     * @return
     * @throws FileException
     */
    Set<String> getOrderInfoEntity() throws FileException;

    /**
     * @return
     * @throws FileException
     */
    Set<String> getOrderInfoEntityOfAll() throws FileException;

    /**
     * @param oid
     * @return
     * @throws FileException
     */
    OrderInfo getOrderInfoEntityByOID(Integer oid) throws FileException;

    /**
     * @param history
     */
    public void insertDataByEntity(History history);

    /**
     * @param huid
     * @return
     */
    List<History> getUpListByUID(String huid);

    /**
     * @param delHid
     */
    void delEntityByHID(String delHid);

    /**
     * @param hid
     * @return
     */
    History getEntityByHID(String hid);

    /**
     * @param hoidhuid
     * @return
     */
    History findHuidExists(Map<String, Object> hoidhuid);

    /**
     * @param history
     */
    void upHistoryData(History history);

    /**
     * @param hoid
     */
    void delEntityByHOID(Integer hoid);
}
