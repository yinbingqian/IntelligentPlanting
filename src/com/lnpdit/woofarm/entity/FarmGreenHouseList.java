package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class FarmGreenHouseList implements Serializable {

    private String pengName;
    private String pengCode;
    private String imgUrl;
    private String currentStage;
    private String plantDays;
    private String timeToMarket;
    private String yield;
    public String getPengName() {
        return pengName;
    }
    public void setPengName(String pengName) {
        this.pengName = pengName;
    }
    
    public String getPengCode() {
        return pengCode;
    }
    public void setPengCode(String pengCode) {
        this.pengCode = pengCode;
    }
    public String getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public String getCurrentStage() {
        return currentStage;
    }
    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }
    public String getPlantDays() {
        return plantDays;
    }
    public void setPlantDays(String plantDays) {
        this.plantDays = plantDays;
    }
    public String getTimeToMarket() {
        return timeToMarket;
    }
    public void setTimeToMarket(String timeToMarket) {
        this.timeToMarket = timeToMarket;
    }
    public String getYield() {
        return yield;
    }
    public void setYield(String yield) {
        this.yield = yield;
    }
    
   
}
