package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class BaseList implements Serializable {
    private String plotId;
    private String plotName;
    public String getPlotId() {
        return plotId;
    }
    public void setPlotId(String plotId) {
        this.plotId = plotId;
    }
    public String getPlotName() {
        return plotName;
    }
    public void setPlotName(String plotName) {
        this.plotName = plotName;
    }

    

   
}
