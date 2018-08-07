package com.hujing.youmedai;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class MyJavaScriptBridge {
    @JavascriptInterface
    public void showSource(String html) {
        //TODO 打印HTML
    }

    @JavascriptInterface
    public void showDescription(String str) {
        //TODO 描述
    }

    @JavascriptInterface
    public void showLoginPhone(String phone) {
        //返回手机号码的回调
        Log.i("test", "====>phone=" + phone);
        toast(phone);
    }

    public static void toast(String str) {
            Toast.makeText(MyApplication.getAppContext(), str, Toast.LENGTH_SHORT).show();
    }
}
