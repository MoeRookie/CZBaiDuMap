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

    /** 黑马坐标（北京市海淀区东北旺南路45号）*/
    protected LatLng hmPos = new LatLng(40.050513, 116.30361);
    /** 传智坐标 */
    protected LatLng czPos = new LatLng(40.065817,116.349902);
    /** 天安门坐标 */
    protected LatLng tamPos = new LatLng(39.915112,116.403963);

    MapView mMapView;
    private BroadcastReceiver receiver;
    private BaiduMap mapController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 注册SDK的广播接收者监听
        registerSDKCheckReceiver();
        setContentView(R.layout.activity_main);
        //获取地图控件引用
        mMapView = findViewById(R.id.bmapView);
        mapController = mMapView.getMap();
//    6.	获取地图Ui控制器：隐藏指南针
//      1.隐藏缩放按钮、比例尺(默认显示缩放按钮、比例尺)
//        mMapView.showScaleControl(false);
//        mMapView.showScaleControl(false);
//      2.获取最小（3）、最大缩放级别（20）
        float maxZoomLevel = mapController.getMaxZoomLevel();
        float minZoomLevel = mapController.getMinZoomLevel();
        Log.e(TAG, "onCreate,maxZoomLevel = " + maxZoomLevel + ",minZoomLevel = " + minZoomLevel);
//      3.设置地图中心点为黑马
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(hmPos);
        mapController.setMapStatus(mapStatusUpdate);
//      4.设置地图缩放为15
        mapStatusUpdate = MapStatusUpdateFactory.zoomTo(15);
        mapController.setMapStatus(mapStatusUpdate);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        unregisterReceiver(receiver);
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}
