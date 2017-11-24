package com.fangzhang.czbaidumap;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

/**
 * Created by Administrator on 2017/11/24.
 */

public class SearchInBoundActivity extends BaseActivity implements OnGetPoiSearchResultListener {
    @Override
    protected void init() {
        PoiSearch poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(SearchInBoundActivity.this);
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
     * 获取兴趣点信息
     * @param poiResult
     */
    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        if (poiResult == null || poiResult.error != SearchResult.ERRORNO.NO_ERROR) {
            showToast("没有搜索到结果");
            return;
        }
        PoiOverlay overlay = new PoiOverlay(mapController);
        overlay.setData(poiResult);   // 把数据设置给覆盖物
        overlay.addToMap();           // 把所有的数据变成覆盖物添加到BaiDuMap
        overlay.zoomToSpan();         // 把所有的搜索结果在一个屏幕内显示出来
    }

    /**
     * 获取兴趣点详情信息
     * @param poiDetailResult
     */
    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
    }
}
