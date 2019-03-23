package com.ning.file.dao;

import com.ning.file.entity.History;

import java.util.List;
import java.util.Map;

/**
 * 科目批次DAO
 *
 * @author wangn
 * @date 2017/5/22
 */
public interface HistoryDao {
    /**
     * 新增科目批次
     *
     * @param history {@link History}
     */
    void insertDataByEntity(History history);

    /**
     * 根据用户ID获取用户上传的文件历史
     *
     * @param huid 用户ID
     * @return 文件历史集合
     */
    List<History> getUpListByUID(String huid);

    /**
     * 根据ID删除上传历史
     *
     * @param delHid 历史ID
     */
    void delEntityByHID(String delHid);

    /**
     * 根据ID获取上传历史
     *
     * @param hid 历史ID
     * @return 上传历史记录
     */
    History getEntityByHID(String hid);

    /**
     * 根据作业ID和用户ID查找上传历史<br>
     * <code>map.put("hoid", user.getUserSelectOid());</code><br>
     * <code>map.put("huid", user.getUid());</code>
     *
     * @param hoidhuid 作业ID和用户ID
     * @return 上传历史记录，可能为<code>null</code>
     */
    History findHuidExists(Map<String, Object> hoidhuid);

    /**
     * 更新上传历史
     *
     * @param history {@link History}
     */
    void upHistoryData(History history);

    /**
     * 根据作业批次ID获取上传文件历史
     *
     * @param hoid ID
     * @return 上传历史集合
     */
    List<History> findFileListByHoid(Integer hoid);

    /**
     * 根据作业批次ID删除上传历史
     *
     * @param hoid 作业批次ID
     */
    void delEntityByHoId(Integer hoid);
}
