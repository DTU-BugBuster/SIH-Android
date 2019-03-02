package com.example.android.waterborne;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.waterborne.ChatApp.AnonymousChat;

public class QuestionnaireActivity extends AppCompatActivity {

    private WebView googleForm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_questionnaire);
        googleForm = findViewById(R.id.googleForm);

        WebSettings webSettings = googleForm.getSettings();
        webSettings.setJavaScriptEnabled(true);
        QuestionnaireActivity.WebViewClientImpl webViewClient = new WebViewClientImpl(QuestionnaireActivity.this);
        googleForm.setWebViewClient(webViewClient);
        googleForm.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSf5NeHFWmo6eGa5ygwuBRLWnwqHNO6EnCfGBf1NdYse_kdliw/viewform");


    }

    public class WebViewClientImpl extends WebViewClient
    {

        private Activity activity = null;

        public WebViewClientImpl(QuestionnaireActivity activity) {
            this.activity = activity;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            if(url.indexOf("https://docs.google.com/forms/d/e/1FAIpQLSf5NeHFWmo6eGa5ygwuBRLWnwqHNO6EnCfGBf1NdYse_kdliw/viewform") > -1 ) return false;

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            activity.startActivity(intent);
            return true;
        }

    }
}
