package com.ning.file.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author wangn
 * @date 2017/5/22
 */
public class History implements Serializable {
    private String hid;
    private String huid;
    private Integer hoid;
    private String type;
    private String filepath;
    private Date uptime;
    private Double filesize;
    private String osubject;
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

