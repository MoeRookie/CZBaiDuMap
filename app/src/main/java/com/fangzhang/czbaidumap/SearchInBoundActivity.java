package com.fangzhang.czbaidumap;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;

/**
 * Created by Administrator on 2017/11/24.
 */

public class SearchInBoundActivity extends PoiSearchBaseActivity{

    @Override
    protected void initPoiSearch() {
        poiSearch.searchInBound(getSearchParams());
    }

    /**
     * 获取范围内搜索兴趣点参数
     * @return
     */
    private PoiBoundSearchOption getSearchParams() {
        PoiBoundSearchOption option = new PoiBoundSearchOption();
//        	西南（左下）：40.048459,116.302072
//        	东北（右上）：40.050675,116.304317
        // 指定搜索范围,由西南以及东北坐标点确认
        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(new LatLng(40.048459,116.302072))
                .include(new LatLng(40.050675,116.304317))
                .build();
        option.bound(bounds);     // 指定搜索范围
        option.keyword("加油站");  // 指定搜索什么内容
        return option;
    }

    /**
     * 获取兴趣点详情信息
     * @param poiDetailResult
     */
    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
    }
}
