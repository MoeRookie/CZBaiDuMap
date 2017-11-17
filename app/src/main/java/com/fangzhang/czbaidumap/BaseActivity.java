package com.fangzhang.czbaidumap;

import android.app.Activity;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/11/17.
 */

public class BaseActivity extends Activity {
    /**
     * 在屏幕中央显示一个Toast
     * @param text 要显示的内容
     */
    public void showToast(CharSequence text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        // 设置toast显示在屏幕中间
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
