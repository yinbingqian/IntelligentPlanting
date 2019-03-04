package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class Originsinfo implements Serializable {
    private String company;
    private String address;
    private String manager;
    private String tel;
    private String plantid;
    private String cropName;
    private String storageMethod;
    private String harvestTime;
    
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getManager() {
        return manager;
    }
    public void setManager(String manager) {
        this.manager = manager;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getPlantid() {
        return plantid;
    }
    public void setPlantid(String plantid) {
        this.plantid = plantid;
    }
    public String getCropName() {
        return cropName;
    }
    public void setCropName(String cropName) {
        this.cropName = cropName;
    }
    public String getStorageMethod() {
        return storageMethod;
    }
    public void setStorageMethod(String storageMethod) {
        this.storageMethod = storageMethod;
    }
    public String getHarvestTime() {
        return harvestTime;
    }
    public void setHarvestTime(String harvestTime) {
        this.harvestTime = harvestTime;
    }

    
  

}
