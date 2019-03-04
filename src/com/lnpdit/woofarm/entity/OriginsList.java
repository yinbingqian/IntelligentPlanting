package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class OriginsList implements Serializable {
    
    private String id;
    private String cavatar;
    private String ccropName;
    
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCavatar() {
        return cavatar;
    }
    public void setCavatar(String cavatar) {
        this.cavatar = cavatar;
    }
    public String getCcropName() {
        return ccropName;
    }
    public void setCcropName(String ccropName) {
        this.ccropName = ccropName;
    }
   
    
}
