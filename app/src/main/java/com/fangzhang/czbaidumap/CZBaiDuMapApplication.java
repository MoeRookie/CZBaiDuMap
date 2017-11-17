package com.fangzhang.czbaidumap;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by Administrator on 2017/11/17.
 */

public class CZBaiDuMapApplication extends Application {
    @Override
    public void onCreate() {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        /**
         * 在该方法中,获取到key和权限
         */
        SDKInitializer.initialize(getApplicationContext());
    }
}
