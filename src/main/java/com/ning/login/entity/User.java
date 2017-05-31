package com.ning.login.entity;

import java.io.Serializable;

/**
 * Created by wangn on 2017/5/19.
 */
public class User implements Serializable {
    private String uid;
    private String username;
    private String password;
    private String headimg;
    private Boolean firstlogin;
    private String name;
    private Integer userselect_oid;
    private String percode;
    private String userOpenID;

    public String getUserOpenID() {
        return userOpenID;
    }

    public void setUserOpenID(String userOpenID) {
        this.userOpenID = userOpenID;
    }

    public String getPercode() {
        return percode;
    }

    public void setPercode(String percode) {
        this.percode = percode;
    }

    public Integer getUserselect_oid() {
        return userselect_oid;
    }

    public void setUserselect_oid(Integer userselect_oid) {
        this.userselect_oid = userselect_oid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public Boolean getFirstlogin() {
        return firstlogin;
    }

    public void setFirstlogin(Boolean firstlogin) {
        this.firstlogin = firstlogin;
    }
}
