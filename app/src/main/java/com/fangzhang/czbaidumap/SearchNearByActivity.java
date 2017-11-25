package com.fangzhang.czbaidumap;

import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;

/**
 * Created by Administrator on 2017/11/24.
 */

public class SearchNearByActivity extends PoiSearchBaseActivity{

    @Override
    protected void initPoiSearch() {
        poiSearch.searchNearby(getSearchParams());
    }

    private PoiNearbySearchOption getSearchParams() {
        PoiNearbySearchOption option = new PoiNearbySearchOption();
        option.location(hmPos);   // 指定在那个位置搜索
        option.radius(1000);      // 指定搜索范围
        option.keyword("厕所");    // 指定搜索的内容
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
