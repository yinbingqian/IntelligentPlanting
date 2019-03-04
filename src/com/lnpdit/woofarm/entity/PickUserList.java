package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class PickUserList implements Serializable {
    private String userCode;
    private String userName;
    public String getUserCode() {
        return userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    
}
