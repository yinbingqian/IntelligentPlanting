package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class FarmAlarmInfoList implements Serializable {
    private String createDate;//报警时间
    private String alarmInfo;//报警信息
    private String alarmType;//报警状态
    private String pengName;//大棚名称
    private String equipName;//网关名称
    
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public String getAlarmInfo() {
        return alarmInfo;
    }
    public void setAlarmInfo(String alarmInfo) {
        this.alarmInfo = alarmInfo;
    }
    public String getAlarmType() {
        return alarmType;
    }
    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }
    public String getPengName() {
        return pengName;
    }
    public void setPengName(String pengName) {
        this.pengName = pengName;
    }
    public String getEquipName() {
        return equipName;
    }
    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }
    
    

}
