package com.example.android_handler.looper_messagequeue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.android_handler.R;

import static com.example.android_handler.looper_messagequeue.ExampleHandler.TASK_A;
import static com.example.android_handler.looper_messagequeue.ExampleHandler.TASK_B;

public class LooperMessageQueueActivity extends AppCompatActivity {
    private Button buttonStartThread, buttonStopThread, buttonTaskA, buttonTaskB;
    private static final String TAG = "LooperMessageQueueActiv";
    private ExampleLooperThread looperThread = new ExampleLooperThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper_message_queue);
        initView();
        initEvent();
        initData();
    }

    private void initData() {
    }

    private void initEvent() {
        buttonStartThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                looperThread.start();
            }
        });

        buttonTaskA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.obtain();
                message.what = TASK_A;
                looperThread.handler.sendMessage(message);

                looperThread.handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            Log.d(TAG, "run: " + i);
                            SystemClock.sleep(1000);
                        }
                    }
                });
            }
        });

        buttonStopThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                looperThread.looper.quit();
            }
        });

        buttonTaskB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.obtain();
                message.what = TASK_B;
                looperThread.handler.sendMessage(message);
            }
        });
    }

    private void initView() {
        buttonStartThread = findViewById(R.id.bt_start);
        buttonStopThread = findViewById(R.id.bt_stop);
        buttonTaskA = findViewById(R.id.bt_taska);
        buttonTaskB = findViewById(R.id.bt_taskb);
    }
}