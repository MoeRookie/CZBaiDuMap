package com.fangzhang.czbaidumap;

import android.util.Log;
import android.view.KeyEvent;

import com.baidu.mapapi.map.BaiduMap;

/**
 * Created by Administrator on 2017/11/23.
 */

public class MapLayerActivity extends BaseActivity {
    private static final String TAG = "MapLayerActivity";
    @Override
    protected void init() {
        Log.e(TAG, "原来这个方法真的执行了哦 . . .");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_1:
                // 显示普通地图
                mapController.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                mapController.setTrafficEnabled(false);
                break;
            case KeyEvent.KEYCODE_2:
                // 显示卫星地图
                mapController.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                mapController.setTrafficEnabled(false);
                break;
            case KeyEvent.KEYCODE_3:
                // 显示交通图
                mapController.setTrafficEnabled(true);
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
