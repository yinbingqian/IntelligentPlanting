package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class CropInfoList implements Serializable {
    private String id;
    private String cropname;
    private String cropdetails;
    private String plantdays;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCropname() {
        return cropname;
    }
    public void setCropname(String cropname) {
        this.cropname = cropname;
    }
    public String getCropdetails() {
        return cropdetails;
    }
    public void setCropdetails(String cropdetails) {
        this.cropdetails = cropdetails;
    }
    public String getPlantdays() {
        return plantdays;
    }
    public void setPlantdays(String plantdays) {
        this.plantdays = plantdays;
    }

    
}
