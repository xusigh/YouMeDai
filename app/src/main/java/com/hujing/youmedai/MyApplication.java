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
        Bmob.initialize(this, "f6d7abbe9b6b863ab3e769a3a23b3024");
    }

    public static Context getAppContext() {
        return appContext;
    }

}
