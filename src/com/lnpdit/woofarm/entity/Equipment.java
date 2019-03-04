package com.lnpdit.woofarm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Equipment implements Serializable {
    private String id;
    private String equipCode;
    private String equipName;
    List<EquipmentChannel> _List;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEquipCode() {
        return equipCode;
    }
    public void setEquipCode(String equipCode) {
        this.equipCode = equipCode;
    }
    public String getEquipName() {
        return equipName;
    }
    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }
    public List<EquipmentChannel> get_List() {
        return _List;
    }
    public void set_List(List<EquipmentChannel> _List) {
        this._List = _List;
    }
    
}
