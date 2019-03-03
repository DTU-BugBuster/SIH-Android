package com.example.android.waterborne.ReportIssuesRelated;

public class UploadReport {
    private String name;
    private int age;
    private String gender;
    private String h_name;
    private String pincode;
    private String state;
    private String water_borne;
    private String addresslat, addresslng;
    private String uid;
    private long Date;
    private String disease;
    boolean isweb;


    public UploadReport(String name, int age, String gender, String disease, String pincode, String h_name, String state, String uid, String lat, String lng) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.disease = disease;
        this.pincode = pincode;
        this.h_name = h_name;
        this.state = state;
        this.Date = 1541176836;
        //address lat lng
        this.addresslat = lat;
        this.addresslng = lng;
        this.isweb = false;
        this.water_borne = "Yes";
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWater_borne() {
        return water_borne;
    }

    public void setWater_borne(String water_borne) {
        this.water_borne = water_borne;
    }

    public String getAddresslat() {
        return addresslat;
    }

    public void setAddresslat(String addresslat) {
        this.addresslat = addresslat;
    }

    public String getAddresslng() {
        return addresslng;
    }

    public void setAddresslng(String addresslng) {
        this.addresslng = addresslng;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getDate() {
        return Date;
    }

    public void setDate(long date) {
        Date = date;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public boolean isIsweb() {
        return isweb;
    }

    public void setIsweb(boolean isweb) {
        this.isweb = isweb;
    }
}
