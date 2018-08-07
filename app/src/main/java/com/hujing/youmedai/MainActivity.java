package com.hujing.youmedai;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private final String url = "https://s.growingio.com/qvX5yl";
    private WebView webView;
    private Toolbar toolbar;
    private ImageView toolbarExit, imgBack, imgNext;
    private TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initWebView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        webView = findViewById(R.id.wv_main);
        toolbarExit = findViewById(R.id.toolbar_exit);
        toolbarTitle = findViewById(R.id.toolbar_title);
        imgBack = findViewById(R.id.img_back);
        imgNext = findViewById(R.id.img_next);

        toolbarExit.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        imgNext.setOnClickListener(this);
    }

    /**
     * 初始化webview,注入JS脚本
     */
    private void initWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);
        webView.addJavascriptInterface(new MyJavaScriptBridge(), "ANDROID_CLIENT");
        //android 5.0以上不允许WebView加载http的图片，通过以下设置可以解决
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 所有连接强制在当前WeiView加载，不跳服务器
                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:window.ANDROID_CLIENT.showSource("
                        + "document.getElementsByTagName('html')[0].innerHTML);");
                view.loadUrl("javascript:window.ANDROID_CLIENT.showDescription("
                        + "document.querySelector('meta[name=\"share-description\"]').getAttribute('content')"
                        + ");");
                // 注入Javascript实现监听点击事件获取电话号码的功能
                view.loadUrl("javascript:\t$(document).ready(function () {\n" +
                        "\t\t$(\"#dtmbtn\").click(function () {\n" +
                        "\t\t\tvar phone = $(\"#mobile\").val();\n" +
                        "\t\t\tif (phone.length > 0) {\n" +
                        "\t\t\t\twindow.ANDROID_CLIENT.showLoginPhone(phone);\n" +
                        "\t\t\t} else {\n" +
                        "\t\t\t}\n" +
                        "\t\t});\n" +
                        "\t});");
                super.onPageFinished(view, url);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_exit:

                break;
            case R.id.img_back:
                webView.goBack();
                break;

            case R.id.img_next:
                webView.goForward();
                break;
            default:

                break;
        }
    }

    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //弹出提示，可以有多种方式
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
