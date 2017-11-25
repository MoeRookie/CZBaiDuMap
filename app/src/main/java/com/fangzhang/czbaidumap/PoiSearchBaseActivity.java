package com.fangzhang.czbaidumap;

import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

/**
 * Created by Administrator on 2017/11/24.
 */

public abstract class PoiSearchBaseActivity extends BaseActivity implements OnGetPoiSearchResultListener{
    protected PoiSearch poiSearch;
    protected PoiOverlay overlay;
    @Override
    protected final void init() {
        // 因为其它搜索也需要这个实例，所以放在父类初始，这样的话子类就不需要再实例化
        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(PoiSearchBaseActivity.this);
        overlay = new PoiOverlay(mapController){
            @Override
            public boolean onPoiClick(int index) {
                return PoiSearchBaseActivity.this.onPoiClick(index);
            }
        };
        mapController.setOnMarkerClickListener(overlay);
        initPoiSearch();
    }

    /**
     * 生成这个方法,是为了让子类可以覆盖
     * @param index
     * @return
     */
    public boolean onPoiClick(int index) {
        PoiInfo poiInfo = overlay.getPoiResult().getAllPoi().get(index);
        showToast(poiInfo.name + "," + poiInfo.address);
        return true;
    }

    /**
     * poi搜索的初始化代码写在这个方法中
     */
    protected abstract void initPoiSearch();

    /**
     * 因为其它搜索结果的处理都是相同的,所以放在父类
     * 获取兴趣点信息
     * @param poiResult
     */
    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        if (poiResult == null || poiResult.error != SearchResult.ERRORNO.NO_ERROR) {
            showToast("没有搜索到结果");
            return;
        }
        overlay.setData(poiResult);   // 把数据设置给覆盖物
        overlay.addToMap();           // 把所有的数据变成覆盖物添加到BaiDuMap
        overlay.zoomToSpan();         // 把所有的搜索结果在一个屏幕内显示出来
    }
}
