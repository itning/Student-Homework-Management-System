package com.ning.file.service;

import com.ning.exception.file.FileException;
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
     * @param oname
     * @return
     * @throws FileException
     */
    List<OrderInfo> getOnameBysubject(String oname) throws FileException;

    /**
     * @param oname
     * @return
     * @throws FileException
     */
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
    void insertDataByEntity(History history);

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
     */
    boolean deleteFile(User user, String hId) throws Exception;
}
