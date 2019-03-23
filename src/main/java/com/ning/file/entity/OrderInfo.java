package com.ning.file.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 科目批次实体
 *
 * @author wangn
 * @date 2017/5/20
 */
public class OrderInfo implements Serializable {
    /**
     * 科目ID
     */
    private int oid;
    /**
     * 科目名
     */
    private String oname;
    /**
     * 作业名
     */
    private String osubject;
    /**
     * 是否允许上传
     */
    private Boolean ostate;
    /**
     * 修改时间
     */
    private Date otime;

    public Boolean getOstate() {
        return ostate;
    }

    public void setOstate(Boolean ostate) {
        this.ostate = ostate;
    }

    public Date getOtime() {
        return otime;
    }

    public void setOtime(Date otime) {
        this.otime = otime;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getOsubject() {
        return osubject;
    }

    public void setOsubject(String osubject) {
        this.osubject = osubject;
    }
}
