package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class EquipmentChannel implements Serializable {

    private String channelStatus;
    private String channelType;
    private String channelName;
    private String channelNo;
  
    public String getChannelStatus() {
        return channelStatus;
    }
    public void setChannelStatus(String channelStatus) {
        this.channelStatus = channelStatus;
    }
    public String getChannelType() {
        return channelType;
    }
    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }
    public String getChannelName() {
        return channelName;
    }
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
    public String getChannelNo() {
        return channelNo;
    }
    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }
    
    
   

    

}
