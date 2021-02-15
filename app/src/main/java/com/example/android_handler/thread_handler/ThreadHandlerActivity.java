package com.example.android_handler.thread_handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_handler.R;

public class ThreadHandlerActivity extends AppCompatActivity {
    public static final int MESSAGE_COUNT_DOWN = 1001;
    public static final int MESSAGE_DONE = 1002;
    private TextView textViewTimer;
    private Button buttonCount;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        initData();
        initHandler();
    }

    private void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case MESSAGE_COUNT_DOWN: {
                        textViewTimer.setText(String.valueOf(msg.arg1));
                        break;
                    }
                    case MESSAGE_DONE: {
                        Toast.makeText(ThreadHandlerActivity.this, "Done", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        };
    }

    private void initData() {

    }

    private void initEvent() {
        buttonCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doCountDown();
            }
        });
    }

    private void initView() {
        textViewTimer = findViewById(R.id.tv_timer);
        buttonCount = findViewById(R.id.bt_count);
    }

    private void doCountDown() {
        Toast.makeText(ThreadHandlerActivity.this, "Do Count Down", Toast.LENGTH_SHORT).show();
        WorkerThread workerThread = new WorkerThread(handler);
        Thread thread = new Thread(workerThread);
        thread.start();
    }
}