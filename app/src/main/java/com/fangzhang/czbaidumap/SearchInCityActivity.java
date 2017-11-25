package com.fangzhang.czbaidumap;

import android.view.KeyEvent;

import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;

/**
 * Created by Administrator on 2017/11/25.
 */

public class SearchInCityActivity extends PoiSearchBaseActivity {
    private int pageNum;
    @Override
    protected void initPoiSearch() {
        poiSearch.searchInCity(getSearchParams());
    }

    private PoiCitySearchOption getSearchParams() {
        PoiCitySearchOption option = new PoiCitySearchOption();
        option.city("诸城");
        option.keyword("超市");
        option.pageCapacity(10);
        option.pageNum(pageNum);
        return option;
    }

    /**
     * 获取搜索详情信息
     * @param poiDetailResult
     */
    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_1) {
            pageNum++;
            poiSearch.searchInCity(getSearchParams());
        }
        return true;
    }
}
