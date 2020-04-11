package com.zcs.test.design.accessDemo.bean;

public class UserInfo {

    private String userName;

    private String passWord;

    private String CUserId;

    public String getCUserId() {
        return CUserId;
    }

    public void setCUserId(String CUserId) {
        this.CUserId = CUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
