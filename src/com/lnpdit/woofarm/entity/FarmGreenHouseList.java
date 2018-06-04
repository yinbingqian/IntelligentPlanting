package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class FarmGreenHouseList implements Serializable {
    private String id;
    private String pengCode;
    private String pengName;
    private String pengType;
    private String landarea;
    private String gpsPoint;
    private String farmCode;
    private String plantStatus;
    private String manager;
    private String controlCount;
    private String sensorCount;
    private String planting;
    
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
    public String getPengName() {
        return pengName;
    }
    public void setPengName(String pengName) {
        this.pengName = pengName;
    }
    public String getPengType() {
        return pengType;
    }
    public void setPengType(String pengType) {
        this.pengType = pengType;
    }
    public String getLandarea() {
        return landarea;
    }
    public void setLandarea(String landarea) {
        this.landarea = landarea;
    }
    public String getGpsPoint() {
        return gpsPoint;
    }
    public void setGpsPoint(String gpsPoint) {
        this.gpsPoint = gpsPoint;
    }
    public String getFarmCode() {
        return farmCode;
    }
    public void setFarmCode(String farmCode) {
        this.farmCode = farmCode;
    }
    public String getPlantStatus() {
        return plantStatus;
    }
    public void setPlantStatus(String plantStatus) {
        this.plantStatus = plantStatus;
    }
    public String getManager() {
        return manager;
    }
    public void setManager(String manager) {
        this.manager = manager;
    }
    public String getControlCount() {
        return controlCount;
    }
    public void setControlCount(String controlCount) {
        this.controlCount = controlCount;
    }
    public String getSensorCount() {
        return sensorCount;
    }
    public void setSensorCount(String sensorCount) {
        this.sensorCount = sensorCount;
    }
    public String getPlanting() {
        return planting;
    }
    public void setPlanting(String planting) {
        this.planting = planting;
    }


}
