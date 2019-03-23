package com.ning.file.service;

import com.ning.file.entity.History;
import com.ning.file.entity.OrderInfo;
import com.ning.login.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 文件服务
 *
 * @author wangn
 * @date 2017/5/19
 */
public interface FileService {
    /**
     * 根据科目批次名获取可上传的科目批次集合
     *
     * @param oname 科目批次名
     * @return 可上传的科目批次集合
     */
    List<OrderInfo> getONameBySubject(String oname);

    /**
     * 根据科目批次名获取所有科目批次集合
     *
     * @param oname 科目批次名
     * @return 科目批次集合
     */
    List<OrderInfo> getOnameBysubjectOfAll(String oname);

    /**
     * 获取所有开启的作业的作业名
     *
     * @return 作业名集合
     */
    Set<String> getOrderInfoEntity();

    /**
     * 获取所有作业的作业名
     *
     * @return 作业名集合
     */
    Set<String> getOrderInfoEntityOfAll();

    /**
     * 根据科目批次ID获取科目批次实体
     *
     * @param oid 科目批次ID
     * @return 科目批次实体
     */
    OrderInfo getOrderInfoEntityByOID(Integer oid);

    /**
     * 新增科目批次信息
     *
     * @param history {@link History}
     */
    void insertDataByEntity(History history);

    /**
     * 根据用户ID获取用户上传的文件历史
     *
     * @param huid 用户ID
     * @return 用户上传的文件历史集合
     */
    List<History> getUpListByUID(String huid);

    /**
     * 根据上传历史ID删除历史纪录
     *
     * @param delHid 上传历史ID
     */
    void delEntityByHID(String delHid);

    /**
     * 根据上传历史ID获取上传信息
     *
     * @param hid 上传历史ID
     * @return {@link History}
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
     * 根据作业批次ID删除上传历史
     *
     * @param hoid 作业批次ID
     */
    void delEntityByHOID(Integer hoid);

    /**
     * 根据用户ID获取用户上传历史
     *
     * @param uId 用户ID
     * @return 上传历史集合
     */
    List<History> getUserHistoryByUserId(String uId);

    /**
     * 上传文件
     *
     * @param file {@link MultipartFile}
     * @param user 用户
     * @throws Exception Exception
     */
    void uploadFile(MultipartFile file, User user) throws Exception;

    /**
     * 删除上传的文件
     *
     * @param user 用户
     * @param hId  文件ID
     * @return 删除成功否
     * @throws Exception Exception
     */
    boolean deleteFile(User user, String hId) throws Exception;
}
