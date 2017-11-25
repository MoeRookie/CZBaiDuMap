package com.fangzhang.czbaidumap;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
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
        option.location(hmPos);
        option.radius(1000);
        option.keyword("厕所");
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
