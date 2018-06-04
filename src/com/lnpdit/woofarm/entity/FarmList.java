package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class FarmList implements Serializable {
    private String id;
    private String farmCode;
    private String farmName;
    private String userCode;
    private String farmArea;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFarmCode() {
        return farmCode;
    }
    public void setFarmCode(String farmCode) {
        this.farmCode = farmCode;
    }
    public String getFarmName() {
        return farmName;
    }
    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }
    public String getUserCode() {
        return userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getFarmArea() {
        return farmArea;
    }
    public void setFarmArea(String farmArea) {
        this.farmArea = farmArea;
    }
    
  

}
