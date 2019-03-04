package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class GrowCropList implements Serializable {
    private String plantName;
    private String plantId;
    public String getPlantName() {
        return plantName;
    }
    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }
    public String getPlantId() {
        return plantId;
    }
    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    

}
