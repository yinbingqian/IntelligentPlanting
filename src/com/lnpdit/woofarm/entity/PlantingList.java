package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class PlantingList implements Serializable {

    private String plantId;
    private String plotName;
    private String beginTime;
    private String endTime;
    private String plantImg;
    private String varietyName;
    private String plantDays;
    private String plantSchedule;
    private String plantState;
    

    public String getPlantId() {
        return plantId;
    }
    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }
    public String getPlotName() {
        return plotName;
    }
    public void setPlotName(String plotName) {
        this.plotName = plotName;
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
    public String getPlantImg() {
        return plantImg;
    }
    public void setPlantImg(String plantImg) {
        this.plantImg = plantImg;
    }
    public String getVarietyName() {
        return varietyName;
    }
    public void setVarietyName(String varietyName) {
        this.varietyName = varietyName;
    }
    public String getPlantDays() {
        return plantDays;
    }
    public void setPlantDays(String plantDays) {
        this.plantDays = plantDays;
    }
    public String getPlantSchedule() {
        return plantSchedule;
    }
    public void setPlantSchedule(String plantSchedule) {
        this.plantSchedule = plantSchedule;
    }
    public String getPlantState() {
        return plantState;
    }
    public void setPlantState(String plantState) {
        this.plantState = plantState;
    }
  
    

   
}
