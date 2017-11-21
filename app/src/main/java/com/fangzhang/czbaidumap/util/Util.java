package com.fangzhang.czbaidumap.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/11/21.
 */
public class Util {
    /**
     * 在屏幕中央显示一个Toast
     * @param text 要显示的内容
     */
    public static void showToast(Context context,String text) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        // 设置toast显示在屏幕中间
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
