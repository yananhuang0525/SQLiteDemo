package com.panku.sqlitedemo.db;

/**
 * Date：2017/6/13
 * Time: 11:57
 * author: huangyanan
 */

public class OrderInfo {
    private String code;
    private String username;
    private String state;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "code:" + code + "username:" + username + "state:" + state;
    }
}
