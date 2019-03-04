package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class CropDiseaseList implements Serializable {
    private String id;
    private String cropCode;
    private String disName;
    private String imgUrl;
    private String details;
    
    
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
    public String getDisName() {
        return disName;
    }
    public void setDisName(String disName) {
        this.disName = disName;
    }
    public String getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
   
 
}
