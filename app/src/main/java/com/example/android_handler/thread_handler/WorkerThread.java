package com.example.android_handler.thread_handler;

import android.os.Handler;
import android.os.Message;

import static com.example.android_handler.thread_handler.ThreadHandlerActivity.MESSAGE_COUNT_DOWN;
import static com.example.android_handler.thread_handler.ThreadHandlerActivity.MESSAGE_DONE;

public class WorkerThread implements Runnable{
    Handler handler;

    public WorkerThread(Handler handler) {
        this.handler = handler;
    }

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
}
