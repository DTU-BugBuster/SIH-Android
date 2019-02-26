package com.example.android.waterborne.DiseasesHeatMapRelated;

public class ReportedCases {
    Double addresslat;
    Double addresslng;
    String d_name;
    String h_name;

    public ReportedCases() {

    }

    public Double getAddresslat() {
        return addresslat;
    }

    public Double getAddresslng() {
        return addresslng;
    }

    public String getD_name() {
        return d_name;
    }

    public String getH_name() {
        return h_name;
    }

    public ReportedCases(Double addresslat, Double addresslng, String d_name, String h_name) {
        this.addresslat = addresslat;
        this.addresslng = addresslng;
        this.d_name = d_name;
        this.h_name = h_name;
    }
}
