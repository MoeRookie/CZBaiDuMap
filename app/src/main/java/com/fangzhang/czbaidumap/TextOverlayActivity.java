package com.fangzhang.czbaidumap;

import com.baidu.mapapi.map.TextOptions;

/**
 * Created by Administrator on 2017/11/23.
 */

public class TextOverlayActivity extends BaseActivity {
    @Override
    protected void init() {
        TextOptions options = new TextOptions();
        options.position(hmPos)         // 位置
                .text("黑马程序员")       // 文字内容
                .fontSize(50)           // 文字大小
                .fontColor(0xff000000)  // 文字颜色
                .bgColor(0x55ff0000)    // 背景颜色
                .rotate(30);            // 旋转
        mapController.addOverlay(options);
    }
}
