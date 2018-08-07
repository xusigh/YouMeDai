package com.hujing.youmedai;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;

public class MyApplication extends Application {
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        Bmob.initialize(this, "*********************");
    }

    public static Context getAppContext() {
        return appContext;
    }

}
