package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class DeviceList implements Serializable {
    private String id;
    private String equipCode;
    private String equipName;
    private String equipStatus;
    private String pengName;
    private String farmName;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEquipCode() {
        return equipCode;
    }
    public void setEquipCode(String equipCode) {
        this.equipCode = equipCode;
    }
    public String getEquipName() {
        return equipName;
    }
    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }
    public String getEquipStatus() {
        return equipStatus;
    }
    public void setEquipStatus(String equipStatus) {
        this.equipStatus = equipStatus;
    }
    public String getPengName() {
        return pengName;
    }
    public void setPengName(String pengName) {
        this.pengName = pengName;
    }
    public String getFarmName() {
        return farmName;
    }
    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }
    

}
