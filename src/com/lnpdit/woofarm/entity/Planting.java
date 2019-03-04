package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class Planting implements Serializable {
    private String id;
    private String cropCode;
    private String pengCode;
    private String beginTime;
    private String endTime;
    private String cropVariety;
    private String plantStandard;
    private String plantStatus;
    private String cropName;
    private String avatar;
    private String varietyName;
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
    public String getPengCode() {
        return pengCode;
    }
    public void setPengCode(String pengCode) {
        this.pengCode = pengCode;
    }
    public String getBeginTime() {
        return beginTime;
    }
    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getCropVariety() {
        return cropVariety;
    }
    public void setCropVariety(String cropVariety) {
        this.cropVariety = cropVariety;
    }
    public String getPlantStandard() {
        return plantStandard;
    }
    public void setPlantStandard(String plantStandard) {
        this.plantStandard = plantStandard;
    }
    public String getPlantStatus() {
        return plantStatus;
    }
    public void setPlantStatus(String plantStatus) {
        this.plantStatus = plantStatus;
    }
    public String getCropName() {
        return cropName;
    }
    public void setCropName(String cropName) {
        this.cropName = cropName;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getVarietyName() {
        return varietyName;
    }
    public void setVarietyName(String varietyName) {
        this.varietyName = varietyName;
    }

    
    
}
