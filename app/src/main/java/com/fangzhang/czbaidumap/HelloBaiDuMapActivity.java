package com.fangzhang.czbaidumap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.KeyEvent;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;

public class HelloBaiDuMapActivity extends BaseActivity {
    private static final String TAG = "HelloBaiDuMapActivity";

    @Override
    protected void init() {
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
