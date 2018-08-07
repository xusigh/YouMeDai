package com.hujing.youmedai;

import android.webkit.JavascriptInterface;

public class MyJavaScriptBridge {
    private static String mPhoneNumber = null;
    private static String mUsername = null;
    private static String mIdCard = null;

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
        mPhoneNumber = phone;
    }

    @JavascriptInterface
    public void showNameAndIdCard(String username, String idcardNo) {
        mUsername = username;
        mIdCard = idcardNo;
    }
}
