package com.example.android.waterborne;

public class UploadReport {
    private String mName;
    private String mImageUrl;
    private String mAddress, contact;
    private String mDesc;
    private String uid;

    public UploadReport() {
        //empty constructor needed
    }

    public UploadReport(String mName, String mImageUrl, String mAddress, String contact, String mDesc, String uid) {
        this.mName = mName;
        this.mImageUrl = mImageUrl;
        this.mAddress = mAddress;
        this.contact = contact;
        this.mDesc = mDesc;
        this.uid = uid;
    }
}
