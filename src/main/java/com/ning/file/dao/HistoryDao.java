package com.ning.file.dao;

import com.ning.file.entity.History;

import java.util.List;
import java.util.Map;

/**
 * @author wangn
 * @date 2017/5/22
 */
public interface HistoryDao {
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
     * @return
     */
    public List<History> findFileListByHoid(Integer hoid);

    /**
     * @param hoid
     */
    public void delEntityByHOID(Integer hoid);
}
