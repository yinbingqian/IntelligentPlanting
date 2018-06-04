package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class ZuowukuList implements Serializable {
    private String id;
    private String cropCode;
    private String cropName;
    private String cropVariety;
    private String avatar;
    private String environment;
    private String standard;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getCropCode() {
        return cropCode;
    }
    public void setCropCode(String cropCode) {
        this.cropCode = cropCode;
    }
    public String getCropName() {
        return cropName;
    }
    public void setCropName(String cropName) {
        this.cropName = cropName;
    }
    public String getCropVariety() {
        return cropVariety;
    }
    public void setCropVariety(String cropVariety) {
        this.cropVariety = cropVariety;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getEnvironment() {
        return environment;
    }
    public void setEnvironment(String environment) {
        this.environment = environment;
    }
    public String getStandard() {
        return standard;
    }
    public void setStandard(String standard) {
        this.standard = standard;
    }
    

}
