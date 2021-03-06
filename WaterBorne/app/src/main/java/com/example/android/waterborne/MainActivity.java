package com.example.android.waterborne;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.waterborne.ChatBotRelated.ChatbotActivity;
import com.example.android.waterborne.DiseasesHeatMapRelated.HeatmapsDemoActivity;
import com.example.android.waterborne.NearbyHospitalsRelated.NearbyHospitalsActivity;
import com.example.android.waterborne.ReportIssuesRelated.ReportProblemActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewGroup mListView;
    Button watson, report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.list);
        watson = findViewById(R.id.bt_watson);
        report = findViewById(R.id.bt_report);
        addDemo("☃ Cold? See Heatmap 🔥", HeatmapsDemoActivity.class);
        addDemo("Emergency? NearbyHospitals here", NearbyHospitalsActivity.class);
        watson.setOnClickListener(view -> {
            startActivity(new Intent(this, ChatbotActivity.class));
        });
        report.setOnClickListener(view1 -> {
            startActivity(new Intent(this, ReportProblemActivity.class));
        });


    }
    private void addDemo(String demoName, Class<? extends Activity> activityClass) {
        Button b = new Button(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        b.setLayoutParams(layoutParams);
        b.setText(demoName);
        b.setTag(activityClass);
        b.setOnClickListener(this);
        mListView.addView(b);
    }

    @Override
    public void onClick(View v) {
        Class activityClass = (Class) v.getTag();
        startActivity(new Intent(this, activityClass));
    }
}