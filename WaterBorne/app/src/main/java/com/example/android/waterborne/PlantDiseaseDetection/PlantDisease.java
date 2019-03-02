package com.example.android.waterborne.PlantDiseaseDetection;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.example.android.waterborne.R;

import com.example.android.waterborne.PlantDiseaseDetection.PlantDisease;

public class PlantDisease extends AppCompatActivity {

    private WebView plantDisease;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_plant_disease);
        plantDisease = findViewById(R.id.plantDisease);

        WebSettings webSettings = plantDisease.getSettings();
        webSettings.setJavaScriptEnabled(true);
        PlantDisease.WebViewClientImpl webViewClient = new PlantDisease.WebViewClientImpl(PlantDisease.this);
        plantDisease.setWebViewClient(webViewClient);
        plantDisease.loadUrl("https://plant-prediction.herokuapp.com/");


    }

    public class WebViewClientImpl extends WebViewClient
    {

        private Activity activity = null;

        public WebViewClientImpl(PlantDisease activity) {
            this.activity = activity;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            if(url.indexOf("https://plant-prediction.herokuapp.com/") > -1 ) return false;

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            activity.startActivity(intent);
            return true;
        }

    }
}
