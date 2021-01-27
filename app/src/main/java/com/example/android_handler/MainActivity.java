package com.example.android_handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textViewTimer;
    private Button buttonCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView()
    }

    initView() {
        textViewTimer = findViewById(R.id.tv_timer);
        buttonCount = findViewById(R.id.bt_count);
    }
}