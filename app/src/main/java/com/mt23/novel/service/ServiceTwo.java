package com.mt23.novel.service;

/**
 * Created by mathcoder23 on 10/31/16.
 */

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.mt23.novel.utils.ServiceDaemon;

import java.util.Timer;
import java.util.TimerTask;

public class ServiceTwo extends Service {

    public final static String TAG = "xixi";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");

        thread.start();
        return START_REDELIVER_INTENT;
    }

    Thread thread = new Thread(new Runnable() {

        @Override
        public void run() {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {

                @Override
                public void run() {
                    Log.e(TAG, "ServiceTwo Run: " + System.currentTimeMillis());
                    boolean b = ServiceDaemon.isServiceWorked(ServiceTwo.this, "com.mt23.novel.service.ServiceOne");
                    if(!b) {
                        Intent service = new Intent(ServiceTwo.this, ServiceOne.class);
                        startService(service);
                    }
                }
            };
            timer.schedule(task, 0, 1000);
        }
    });

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

}