package com.ning.file.service;

import com.ning.exception.file.FileException;
import com.ning.file.entity.History;
import com.ning.file.entity.OrderInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangn on 2017/5/19.
 */
public interface FileService {
    public List<OrderInfo> getOnameBysubject(String oname) throws FileException;

    public Set<String> getOrderInfoEntity() throws FileException;

    public OrderInfo getOrderInfoEntityByOID(Integer oid) throws FileException;

    public void insertDataByEntity(History history);

    public List<History> getUpListByUID(String huid);

    public void delEntityByHID(String delHid);

    public History getEntityByHID(String hid);

    public History findHuidExists(Map<String,Object> hoidhuid);

    public void upHistoryData(History history);

    public void delEntityByHOID(Integer hoid);
}
