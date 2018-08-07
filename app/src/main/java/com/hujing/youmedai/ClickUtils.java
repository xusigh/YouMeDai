package com.hujing.youmedai;
/**
 * 验证短时间内重复点击事件
 */
public class ClickUtils {
    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 5000;
    private static long lastClickTime;

    /**
     * 判断是否是快速点击
     *
     * @returntr 返回true不是快速点击 返回false是快速点击
     */
    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }
}