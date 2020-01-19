package com.ning.file.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作业名称实体
 *
 * @author wangn
 * @date 2017/5/20
 */
public class OrderInfo implements Serializable {
    /**
     * 作业名称ID
     */
    private int oid;
    /**
     * 课程名
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

    private Date odeadline;
    private String odeadlinestr;

    public Boolean getOstate() {
        return ostate;
    }

    public void setOstate(Boolean ostate) {
        this.ostate = ostate;
    }

    public Date getOtime() {
        return otime;
    }

    // 截止时间
    public Date getOdeadline() {return odeadline;}
    public void setOdeadline(Date odeadline) {this.odeadline = odeadline;}
    public String getOdeadlinestr() {return odeadlinestr;}
    public void setOdeadlinestr(String odeadlinestr) {this.odeadlinestr = odeadlinestr;}

    // convert string to java date
    //https://stackoverflow.com/questions/2318719/how-to-convert-timestamp-string-to-java-util-date
    public void setOdeadlineFromStr(String odeadlinestr) throws ParseException {
        // unix timestamp counts seconds, need to multiply 1000
        Date deadlineDate = new Date(Long.parseLong(odeadlinestr) * 1000);
        this.odeadline = deadlineDate;
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
