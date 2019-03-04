package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class AreaHouseList implements Serializable {

	private String id;
	private String plantId;
    private String planName;
	private String beginDate;
	private String endDate;
    private String userItem;
    private String planType;
    private String planStatus;
    private String pengName;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPlantId() {
        return plantId;
    }
    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }
    public String getPlanName() {
        return planName;
    }
    public void setPlanName(String planName) {
        this.planName = planName;
    }
    public String getBeginDate() {
        return beginDate;
    }
    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getUserItem() {
        return userItem;
    }
    public void setUserItem(String userItem) {
        this.userItem = userItem;
    }
    public String getPlanType() {
        return planType;
    }
    public void setPlanType(String planType) {
        this.planType = planType;
    }
    public String getPlanStatus() {
        return planStatus;
    }
    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }
    public String getPengName() {
        return pengName;
    }
    public void setPengName(String pengName) {
        this.pengName = pengName;
    }
	
    
}
