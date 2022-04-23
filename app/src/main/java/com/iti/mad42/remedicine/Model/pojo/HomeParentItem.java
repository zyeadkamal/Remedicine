package com.iti.mad42.remedicine.Model.pojo;

import com.iti.mad42.remedicine.Model.pojo.MedicationPojo;

import java.util.ArrayList;
import java.util.List;

public class HomeParentItem {

    private String doseTime;
    private List<MedicationPojo> childItemList;

    public HomeParentItem() {
    }

    public HomeParentItem(String doseTime, List<MedicationPojo> childItemList) {
        this.doseTime = doseTime;
        this.childItemList = childItemList;
    }

    public String getDoseTime() {
        return doseTime;
    }

    public void setDoseTime(String doseTime) {
        this.doseTime = doseTime;
    }

    public List<MedicationPojo> getChildItemList() {
        return childItemList;
    }

    public void setChildItemList(List<MedicationPojo> childItemList) {
        this.childItemList = childItemList;
    }

}