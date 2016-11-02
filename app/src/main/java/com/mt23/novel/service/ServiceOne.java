package com.mt23.novel.service;

/**
 * Created by mathcoder23 on 10/31/16.
 */

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.mt23.novel.novel.source.StoryBiquge;
import com.mt23.novel.utils.NotifyHelper;
import com.mt23.novel.utils.ServiceDaemon;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class ServiceOne extends Service {

    public final static String TAG = "xixi";
    StoryBiquge storyBiquge = null;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        storyBiquge = new StoryBiquge();
        thread.start();
        return START_STICKY;
    }

    Thread thread = new Thread(new Runnable() {

        @Override
        public void run() {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {

                @Override
                public void run() {
                   // Log.e(TAG, "ServiceOne Run: "+System.currentTimeMillis());
                    boolean b = ServiceDaemon.isServiceWorked(ServiceOne.this, "com.mt23.novel.service.ServiceTwo");
                    if(!b) {
                        Intent service = new Intent(ServiceOne.this, ServiceTwo.class);
                        startService(service);
                        Log.e(TAG, "Start ServiceTwo");
                    }

                }
            };
            timer.schedule(task, 0, 1000);
            Timer timer2 = new Timer();
            TimerTask task2 = new TimerTask() {

                @Override
                public void run() {
                    storyBiquge.getChaptersBuxiufanrenzhuan(StoryBiquge.API_BUXIUFANREN, new StoryBiquge.StoryData() {
                        @Override
                        public void StoryChapters(List<Map<String, Object>> chapters) {
                            Log.i("xixi",chapters.get(0).get("name")+"");
                            NotifyHelper.notify(ServiceOne.this,""+chapters.get(0).get("name"));
                        }

                        @Override
                        public void StoryChapterContent(String data) {

                        }
                    });

                }
            };
            timer2.schedule(task2, 0, 1000*60*60);

        }
    });

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

}
