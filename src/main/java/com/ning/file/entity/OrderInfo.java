package com.ning.file.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangn
 * @date 2017/5/20
 */
public class OrderInfo implements Serializable {
    private int oid;
    private String oname;
    private String osubject;
    private Boolean ostate;
    private Date otime;

    /**
     * @return
     */
    public Boolean getOstate() {
        return ostate;
    }

    /**
     * @param ostate
     */
    public void setOstate(Boolean ostate) {
        this.ostate = ostate;
    }

    /**
     * @return
     */
    public Date getOtime() {
        return otime;
    }

    /**
     * @param otime
     */
    public void setOtime(Date otime) {
        this.otime = otime;
    }

    /**
     * @return
     */
    public int getOid() {
        return oid;
    }

    /**
     * @param oid
     */
    public void setOid(int oid) {
        this.oid = oid;
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
    public String getOsubject() {
        return osubject;
    }

    /**
     * @param osubject
     */
    public void setOsubject(String osubject) {
        this.osubject = osubject;
    }
}
