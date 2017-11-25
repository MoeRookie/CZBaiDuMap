package com.fangzhang.czbaidumap;

import android.view.KeyEvent;

import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;

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
        option.city("诸城");        // 指定在哪个城市内进行搜索
        option.keyword("超市");     // 指定搜索的什么内容
        option.pageCapacity(10);   // 设置一页获取 10 条数据
        option.pageNum(pageNum);   // 指定获取那哪一页
        return option;
    }

    @Override
    public boolean onPoiClick(int index) {
        PoiInfo poiInfo = overlay.getPoiResult().getAllPoi().get(index);
        poiSearch.searchPoiDetail(getSearchDetailParams(poiInfo.uid));
        return true;
    }

    private PoiDetailSearchOption getSearchDetailParams(String poiUid) {
        PoiDetailSearchOption option = new PoiDetailSearchOption();
        option.poiUid(poiUid);
        return option;
    }

    /**
     * 获取搜索详情信息
     * @param poiDetailResult
     */
    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
        if (poiDetailResult == null || poiDetailResult.error != SearchResult.ERRORNO.NO_ERROR) {
            showToast("没有搜索到详情信息");
            return;
        }
        showToast(poiDetailResult.getName() + poiDetailResult.getShopHours() + poiDetailResult.getTelephone());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_1:
                pageNum++;
                poiSearch.searchInCity(getSearchParams());
                break;
            case KeyEvent.KEYCODE_BACK:
                finish();
        }
        return true;
    }
}
