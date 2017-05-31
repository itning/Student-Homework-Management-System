package com.ning.file.dao;

import com.ning.file.entity.History;

import java.util.List;
import java.util.Map;

/**
 * Created by wangn on 2017/5/22.
 */
public interface HistoryDao {
    public void insertDataByEntity(History history);

    public List<History> getUpListByUID(String huid);

    public void delEntityByHID(String delHid);

    public History getEntityByHID(String hid);

    public History findHuidExists(Map<String, Object> hoidhuid);

    public void upHistoryData(History history);

    public List<History> findFileListByHoid(Integer hoid);

    public void delEntityByHOID(Integer hoid);
}
