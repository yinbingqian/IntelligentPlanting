package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class Scenes implements Serializable {
    private String id;
    private String parameter;
    private String pengCode;
    private String scenesName;
    private String action;
    private String conditionType;
    private String effectEndTime;
    private String effectBeginTime;
    private String equipCode;
    private String scenesValue;
    private String continued;
    private String effectCodition;
    
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getParameter() {
        return parameter;
    }
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
    public String getPengCode() {
        return pengCode;
    }
    public void setPengCode(String pengCode) {
        this.pengCode = pengCode;
    }
    public String getScenesName() {
        return scenesName;
    }
    public void setScenesName(String scenesName) {
        this.scenesName = scenesName;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public String getConditionType() {
        return conditionType;
    }
    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }
    public String getEffectEndTime() {
        return effectEndTime;
    }
    public void setEffectEndTime(String effectEndTime) {
        this.effectEndTime = effectEndTime;
    }
    public String getEffectBeginTime() {
        return effectBeginTime;
    }
    public void setEffectBeginTime(String effectBeginTime) {
        this.effectBeginTime = effectBeginTime;
    }
    public String getEquipCode() {
        return equipCode;
    }
    public void setEquipCode(String equipCode) {
        this.equipCode = equipCode;
    }
    public String getScenesValue() {
        return scenesValue;
    }
    public void setScenesValue(String scenesValue) {
        this.scenesValue = scenesValue;
    }
    public String getContinued() {
        return continued;
    }
    public void setContinued(String continued) {
        this.continued = continued;
    }
    public String getEffectCodition() {
        return effectCodition;
    }
    public void setEffectCodition(String effectCodition) {
        this.effectCodition = effectCodition;
    }

  
}
