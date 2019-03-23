package com.ning.file.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 上传历史记录
 *
 * @author wangn
 * @date 2017/5/22
 */
public class History implements Serializable {
    /**
     * 唯一ID
     */
    private String hid;
    /**
     * 对应的用户ID
     */
    private String huid;
    /**
     * 科目ID
     */
    private Integer hoid;
    /**
     * 上传的文件MIME类型
     */
    private String type;
    /**
     * 文件名
     */
    private String filepath;
    /**
     * 上传时间
     */
    private Date uptime;
    /**
     * 文件大小
     */
    private Double filesize;
    /**
     *
     */
    private String osubject;
    /**
     *
     */
    private String oname;

    /**
     * @return
     */
    public String getOsubject() {
        return osubject;
    }

    /**
     * @param osubject
     */
    public void setOsubject(String osubject) {
        this.osubject = osubject;
    }

    /**
     * @return
     */
    public String getOname() {
        return oname;
    }

    /**
     * @param oname
     */
    public void setOname(String oname) {
        this.oname = oname;
    }

    /**
     * @return
     */
    public String getHid() {
        return hid;
    }

    /**
     * @param hid
     */
    public void setHid(String hid) {
        this.hid = hid;
    }

    /**
     * @return
     */
    public String getHuid() {
        return huid;
    }

    /**
     * @param huid
     */
    public void setHuid(String huid) {
        this.huid = huid;
    }

    /**
     * @return
     */
    public Integer getHoid() {
        return hoid;
    }

    /**
     * @param hoid
     */
    public void setHoid(Integer hoid) {
        this.hoid = hoid;
    }

    /**
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * @param filepath
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * @return
     */
    public Date getUptime() {
        return uptime;
    }

    /**
     * @param uptime
     */
    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    /**
     * @return
     */
    public Double getFilesize() {
        return filesize;
    }

    /**
     * @param filesize
     */
    public void setFilesize(Double filesize) {
        this.filesize = filesize;
    }
}

