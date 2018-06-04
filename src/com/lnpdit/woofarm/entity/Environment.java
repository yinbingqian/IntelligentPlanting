package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class Environment implements Serializable {
    private String createDate;
    private String equipCode;
    private String equipType;
    private String id;
    private String pengCode;
    private String count;
    private String sensorData;
    private String switchId;
    
    
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public String getEquipCode() {
        return equipCode;
    }
    public void setEquipCode(String equipCode) {
        this.equipCode = equipCode;
    }
    public String getEquipType() {
        return equipType;
    }
    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPengCode() {
        return pengCode;
    }
    public void setPengCode(String pengCode) {
        this.pengCode = pengCode;
    }
    public String getCount() {
        return count;
    }
    public void setCount(String count) {
        this.count = count;
    }
    public String getSensorData() {
        return sensorData;
    }
    public void setSensorData(String sensorData) {
        this.sensorData = sensorData;
    }
    public String getSwitchId() {
        return switchId;
    }
    public void setSwitchId(String switchId) {
        this.switchId = switchId;
    }


}
