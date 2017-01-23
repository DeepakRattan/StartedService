package com.example.deepakrattan.startedservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by deepak.rattan on 1/23/2017.
 */

public class MyService extends Service {
    private static final String TAG = "Service";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Service is created");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Service is destroyed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand is executing");

        //Creating a new thread

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    long endTine = System.currentTimeMillis() + 20 * 1000;
                    while (System.currentTimeMillis() < endTine){
                        synchronized (this){
                            try {
                                wait(endTine - System.currentTimeMillis());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                stopSelf();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        return START_STICKY;
    }
}
