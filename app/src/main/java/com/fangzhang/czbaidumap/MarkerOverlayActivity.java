package com.fangzhang.czbaidumap;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MarkerOptions;

/**
 * Created by Administrator on 2017/11/24.
 */

public class MarkerOverlayActivity extends BaseActivity {
    @Override
    protected void init() {
        MarkerOptions options = new MarkerOptions();
        // 根据资源id创建bitmap的描述信息
        BitmapDescriptor descriptor = new BitmapDescriptorFactory().fromResource(R.drawable.icon_eat);
        options.position(hmPos)          // 位置
        .icon(descriptor)                // 图标
        .title("黑马")                    // title
        .draggable(true);                // 图标可以拖动
        mapController.addOverlay(options);
    }
}
