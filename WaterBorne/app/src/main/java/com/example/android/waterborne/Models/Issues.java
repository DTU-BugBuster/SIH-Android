package com.example.android.waterborne.Models;

public class Issues {

    private String ActionsTaken,ChangeTaste,DateOfComplain,Name,RegularFluctuations,ReportedAuthorities,WaterTaste;

    public Issues(String actionsTaken, String changeTaste, String dateOfComplain, String name, String regularFluctuations, String reportedAuthorities, String waterTaste) {
        ActionsTaken = actionsTaken;
        ChangeTaste = changeTaste;
        DateOfComplain = dateOfComplain;
        Name = name;
        RegularFluctuations = regularFluctuations;
        ReportedAuthorities = reportedAuthorities;
        WaterTaste = waterTaste;
    }

    public String getActionsTaken() {
        return ActionsTaken;
    }

    public void setActionsTaken(String actionsTaken) {
        ActionsTaken = actionsTaken;
    }

    public String getChangeTaste() {
        return ChangeTaste;
    }

    public void setChangeTaste(String changeTaste) {
        ChangeTaste = changeTaste;
    }

    public String getDateOfComplain() {
        return DateOfComplain;
    }

    public void setDateOfComplain(String dateOfComplain) {
        DateOfComplain = dateOfComplain;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRegularFluctuations() {
        return RegularFluctuations;
    }

    public void setRegularFluctuations(String regularFluctuations) {
        RegularFluctuations = regularFluctuations;
    }

    public String getReportedAuthorities() {
        return ReportedAuthorities;
    }

    public void setReportedAuthorities(String reportedAuthorities) {
        ReportedAuthorities = reportedAuthorities;
    }

    public String getWaterTaste() {
        return WaterTaste;
    }

    public void setWaterTaste(String waterTaste) {
        WaterTaste = waterTaste;
    }
}
