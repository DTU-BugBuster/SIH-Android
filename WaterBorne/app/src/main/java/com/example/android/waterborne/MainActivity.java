package com.example.android.waterborne;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.android.waterborne.ChatBotRelated.ChatbotActivity;

public class MainActivity extends AppCompatActivity {
    Button watson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        watson = findViewById(R.id.bt_watson);
        watson.setOnClickListener(view -> {
            startActivity(new Intent(this, ChatbotActivity.class));
        });
    }
}
