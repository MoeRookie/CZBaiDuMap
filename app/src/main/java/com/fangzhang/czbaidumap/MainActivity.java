package com.fangzhang.czbaidumap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private BroadcastReceiver receiver;

    @Override
    protected void init() {
        // 注册SDK的广播接收者监听
        registerSDKCheckReceiver();
    }

    private void registerSDKCheckReceiver() {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR.equals(action)) {
                    showToast("网络错误");
                } else if (SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR.equals(action)) {
                    showToast("key验证失败");
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        // 监听网络错误
        filter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        // 监听百度地图sdk的key是否正确
        filter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        MapStatusUpdate status = null;
//    5.更新地图状态
        switch (keyCode) {
//      1)	缩小
            case KeyEvent.KEYCODE_1:
                status = MapStatusUpdateFactory.zoomOut();
                break;
//      2)	放大
            case KeyEvent.KEYCODE_2:
                status = MapStatusUpdateFactory.zoomIn();
                break;
//      3)	旋转（0 ~ 360），每次在原来的基础上再旋转30度
            case KeyEvent.KEYCODE_3:
                // 获取当前地图的旋转角度
                MapStatus currentMapStatus = mapController.getMapStatus();
                float rotate = currentMapStatus.rotate + 30;
                MapStatus newMapStatus = new MapStatus.Builder().rotate(rotate).build();
                status = MapStatusUpdateFactory.newMapStatus(newMapStatus);
                break;
//      4)	俯仰（0 ~ -45），每次在原来的基础上再俯仰-5度
            case KeyEvent.KEYCODE_4:
                // 获取当前地图的俯仰角度
                currentMapStatus = mapController.getMapStatus();
                float overlook = currentMapStatus.overlook - 5;
                newMapStatus = new MapStatus.Builder().overlook(overlook).build();
                status = MapStatusUpdateFactory.newMapStatus(newMapStatus);
                break;
//      5)	移动
            case KeyEvent.KEYCODE_5:
                status = MapStatusUpdateFactory.newLatLng(czPos);
                mapController.animateMapStatus(status,2000);
                return super.onKeyDown(keyCode, event);
        }
        mapController.setMapStatus(status);
        return super.onKeyDown(keyCode, event);
    }
}
