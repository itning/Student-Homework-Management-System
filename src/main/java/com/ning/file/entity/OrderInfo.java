package com.ning.file.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangn on 2017/5/20.
 */
public class OrderInfo implements Serializable {
    private int oid;
    private String oname;
    private String osubject;
    private Boolean ostate;
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
