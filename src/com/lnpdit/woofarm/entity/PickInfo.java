package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class PickInfo implements Serializable {
    private String cropName;
    private String plotName;
    private String picker;
    private String pickQuantity;
    private String pickDate;
    private String pickPhoto;
    public String getCropName() {
        return cropName;
    }
    public void setCropName(String cropName) {
        this.cropName = cropName;
    }
    public String getPlotName() {
        return plotName;
    }
    public void setPlotName(String plotName) {
        this.plotName = plotName;
    }
    public String getPicker() {
        return picker;
    }
    public void setPicker(String picker) {
        this.picker = picker;
    }
  
   
    public String getPickQuantity() {
        return pickQuantity;
    }
    public void setPickQuantity(String pickQuantity) {
        this.pickQuantity = pickQuantity;
    }
    public String getPickDate() {
        return pickDate;
    }
    public void setPickDate(String pickDate) {
        this.pickDate = pickDate;
    }
    public String getPickPhoto() {
        return pickPhoto;
    }
    public void setPickPhoto(String pickPhoto) {
        this.pickPhoto = pickPhoto;
    }
   
    
    
}
