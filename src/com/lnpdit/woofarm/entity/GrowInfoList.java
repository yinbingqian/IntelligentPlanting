package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class GrowInfoList implements Serializable {
    private String plantId;
    private String plantName;
    private String logAction;
    private String logImg;
    private String workContent;
    public String getPlantId() {
        return plantId;
    }
    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }
    public String getPlantName() {
        return plantName;
    }
    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }
    public String getLogAction() {
        return logAction;
    }
    public void setLogAction(String logAction) {
        this.logAction = logAction;
    }
    public String getLogImg() {
        return logImg;
    }
    public void setLogImg(String logImg) {
        this.logImg = logImg;
    }
    public String getWorkContent() {
        return workContent;
    }
    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }
   
    
    

   
}
