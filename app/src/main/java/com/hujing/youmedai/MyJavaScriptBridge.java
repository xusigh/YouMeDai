package com.hujing.youmedai;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class MyJavaScriptBridge {
    @JavascriptInterface
    public void showSource(String html) {
        //TODO 打印HTML
        System.out.print(html);
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

    @JavascriptInterface
    public void showNameAndIdCard(String username, String idcardNo) {
        Log.i("test", "=====>name+idcard=" + username + idcardNo);
        toast(username + idcardNo);
    }

    public static void toast(String str) {
        Toast.makeText(MyApplication.getAppContext(), str, Toast.LENGTH_SHORT).show();
    }
}
