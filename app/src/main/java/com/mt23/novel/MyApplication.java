package com.mt23.novel;

import android.app.Application;
import android.content.Context;

/**
 * Created by mathcoder23 on 11/9/16.
 */
public class MyApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
