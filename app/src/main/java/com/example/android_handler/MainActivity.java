package com.example.android_handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int MESSAGE_COUNT_DOWN = 1001;
    private static final int MESSAGE_DONE = 1002;
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
                        Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
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
        Thread threadCountDown = new Thread(new Runnable() {
            @Override
            public void run() {
                int time = 10;
                do {
                    time--;

                    // không thể trực tiếp cập nhật lên view, sử dụng handler như một cầu nối để gửi dữ liệu
                    Message message = new Message();
                    message.what = MESSAGE_COUNT_DOWN;
                    message.arg1 = time;
                    handler.sendMessage(message);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (time > 0);
                handler.sendEmptyMessage(MESSAGE_DONE);
            }
        });

        threadCountDown.start();


    }
}