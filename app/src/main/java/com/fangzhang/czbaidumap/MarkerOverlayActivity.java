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
        // 用户点击标志覆盖物显示一个泡泡
        mapController.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (pop == null) {
                    pop = View.inflate(MarkerOverlayActivity.this, R.layout.pop, null);
                    tvTitle = pop.findViewById(R.id.tv_title);
                    mMapView.addView(pop, createLayoutParams(marker.getPosition()));
                } else {
                    mMapView.updateViewLayout(pop,createLayoutParams(marker.getPosition()));
                }
                tvTitle.setText(marker.getTitle());
                return true;
            }
        });
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
