package com.example.android.waterborne.Models;

public class HomeRemedyModel {
    String Description,Heading;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getHeading() {
        return Heading;
    }

    public void setHeading(String heading) {
        Heading = heading;
    }


    public HomeRemedyModel(String description, String heading) {

        Description = description;
        Heading = heading;
    }
}
