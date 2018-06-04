package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class Equipment implements Serializable {
    private String id;
    private String createDate;
    private String status;
    private String equipCode;
    private String equipName;
    private String equipKind;
    private String equipStatus;
    private String equipType;
    private String pengCode;
    private String switchId;
    private String switchType;
    private String switchCount;
    
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
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
    public String getEquipKind() {
        return equipKind;
    }
    public void setEquipKind(String equipKind) {
        this.equipKind = equipKind;
    }
    public String getEquipStatus() {
        return equipStatus;
    }
    public void setEquipStatus(String equipStatus) {
        this.equipStatus = equipStatus;
    }
    public String getEquipType() {
        return equipType;
    }
    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }
    public String getPengCode() {
        return pengCode;
    }
    public void setPengCode(String pengCode) {
        this.pengCode = pengCode;
    }
    public String getSwitchId() {
        return switchId;
    }
    public void setSwitchId(String switchId) {
        this.switchId = switchId;
    }
    public String getSwitchType() {
        return switchType;
    }
    public void setSwitchType(String switchType) {
        this.switchType = switchType;
    }
    public String getSwitchCount() {
        return switchCount;
    }
    public void setSwitchCount(String switchCount) {
        this.switchCount = switchCount;
    }

    

}
