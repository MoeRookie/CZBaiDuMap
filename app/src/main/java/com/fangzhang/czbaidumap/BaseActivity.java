package com.fangzhang.czbaidumap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;

/**
 * Created by Administrator on 2017/11/17.
 */

public abstract class BaseActivity extends Activity {

    private static final String TAG = "BaseActivity";

    /** 黑马坐标（北京市海淀区东北旺南路45号）*/
    protected LatLng hmPos = new LatLng(40.050513, 116.30361);
    /** 传智坐标 */
    protected LatLng czPos = new LatLng(40.065817,116.349902);
    /** 天安门坐标 */
    protected LatLng tamPos = new LatLng(39.915112,116.403963);

    MapView mMapView;
    protected BaiduMap mapController;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        //获取地图控件引用
        mMapView = findViewById(R.id.bmapView);
        mapController = mMapView.getMap();
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
//      6.获取地图Ui控制器：隐藏指南针
//        UiSettings uiSettings = mapController.getUiSettings();
//        uiSettings.setCompassEnabled(false);
        init();
    }

    protected abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
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
