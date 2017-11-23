package com.fangzhang.czbaidumap;

import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.Stroke;
import com.fangzhang.czbaidumap.BaseActivity;

/**
 * Created by Administrator on 2017/11/23.
 */

public class CircleOverlayActivity extends BaseActivity {
    @Override
    protected void init() {
        // 创建一个圆形覆盖物参数
        CircleOptions options = new CircleOptions();
        options.center(hmPos) // 圆心
        .radius(1000) // 半径 - 以米为单位
        .stroke(new Stroke(20,0x55ff0000)) // 线条宽度、颜色
        .fillColor(0x5500ff00); // 圆的填充颜色
        // 添加一个覆盖物
        mapController.addOverlay(options);
    }
}
