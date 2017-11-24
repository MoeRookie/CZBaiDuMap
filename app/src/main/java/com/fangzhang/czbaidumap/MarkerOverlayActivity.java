package com.fangzhang.czbaidumap;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapViewLayoutParams.ELayoutMode;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;

/**
 * Created by Administrator on 2017/11/24.
 */

public class MarkerOverlayActivity extends BaseActivity {
    private View pop;
    private TextView tvTitle;
    @Override
    protected void init() {
        initMarker();
        /**
         * 设置用户点击标志覆盖物后显示位置描述的气泡
         */
        mapController.setOnMarkerClickListener(onMarkerClickListener);
        /**
         * 设置用户拖拽标志覆盖物时位置描述气泡跟随
         */
        mapController.setOnMarkerDragListener(onMarkerDragListener);
    }
    private BaiduMap.OnMarkerDragListener onMarkerDragListener = new BaiduMap.OnMarkerDragListener() {

        @Override
        public void onMarkerDragStart(Marker marker) {
            tvTitle.setText(marker.getTitle());
            mMapView.updateViewLayout(pop,createLayoutParams(marker.getPosition()));
        }

        @Override
        public void onMarkerDrag(Marker marker) {
            mMapView.updateViewLayout(pop,createLayoutParams(marker.getPosition()));
        }

        @Override
        public void onMarkerDragEnd(Marker marker) {
            mMapView.updateViewLayout(pop,createLayoutParams(marker.getPosition()));
        }
    };
    private BaiduMap.OnMarkerClickListener onMarkerClickListener = new BaiduMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            onMarkerOperated(marker);
            return true;
        }
    };
    /**
     * 当操作Marker覆盖物的操作被监听到之后的操作
     * @param marker
     */
    private void onMarkerOperated(Marker marker) {
        if (pop == null) {
            pop = View.inflate(MarkerOverlayActivity.this, R.layout.pop, null);
            tvTitle = pop.findViewById(R.id.tv_title);
            mMapView.addView(pop, createLayoutParams(marker.getPosition()));
        } else {
            mMapView.updateViewLayout(pop,createLayoutParams(marker.getPosition()));
        }
        tvTitle.setText(marker.getTitle());
    }

    /**
     * 创建位置描述气泡的参数对象
     * @param position
     * @return
     */
    private ViewGroup.LayoutParams createLayoutParams(LatLng position) {
        // 把View添加到什么样的布局中,就使用什么样的LayoutParams布局参数对象
        MapViewLayoutParams layoutParams = new MapViewLayoutParams.Builder()
                .layoutMode(ELayoutMode.mapMode)     // 设置坐标类型为经纬度
                .position(position)                  // 设置标志的位置
                .yOffset(-85)                        // 设置View向上移
                .build();
        return layoutParams;
    }

    /**
     * 初始化标志覆盖物
     */
    private void initMarker() {
        MarkerOptions options = new MarkerOptions();
        // 根据资源id创建bitmap的描述信息
        BitmapDescriptor descriptor = new BitmapDescriptorFactory().fromResource(R.drawable.icon_eat);
        options.position(hmPos)                  // 位置
                .icon(descriptor)                // 图标
                .title("黑马")                    // title
                .draggable(true);                // 图标可以拖动
        mapController.addOverlay(options);
        options.position(new LatLng(hmPos.latitude + 0.001,hmPos.longitude))// 位置
                .icon(descriptor)                // 图标
                .title("向北")                    // title
                .draggable(true);                // 图标可以拖动
        mapController.addOverlay(options);
        options.position(new LatLng(hmPos.latitude,hmPos.longitude + 0.001))// 位置
                .icon(descriptor)                // 图标
                .title("向东")                    // title
                .draggable(true);                // 图标可以拖动
        mapController.addOverlay(options);
        options.position(new LatLng(hmPos.latitude - 0.001,hmPos.longitude - 0.001))// 位置
                .icon(descriptor)                // 图标
                .title("向西南")                  // title
                .draggable(true);                // 图标可以拖动
        mapController.addOverlay(options);
    }
}
