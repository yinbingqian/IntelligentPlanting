package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class PengAlarm implements Serializable {
    private String pengCode;
    private String alarmCount;
    private String occTime;
    public String getPengCode() {
        return pengCode;
    }
    public void setPengCode(String pengCode) {
        this.pengCode = pengCode;
    }
    public String getAlarmCount() {
        return alarmCount;
    }
    public void setAlarmCount(String alarmCount) {
        this.alarmCount = alarmCount;
    }
    public String getOccTime() {
        return occTime;
    }
    public void setOccTime(String occTime) {
        this.occTime = occTime;
    }
 
}
