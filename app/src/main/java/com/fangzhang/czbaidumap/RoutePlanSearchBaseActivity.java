package com.fangzhang.czbaidumap;

import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.RoutePlanSearch;

/**
 * Created by Administrator on 2017/11/25.
 */

public abstract class RoutePlanSearchBaseActivity extends BaseActivity implements OnGetRoutePlanResultListener {
    @Override
    protected void init() {
        RoutePlanSearch routePlanSearch = RoutePlanSearch.newInstance();
        routePlanSearch.setOnGetRoutePlanResultListener(RoutePlanSearchBaseActivity.this);
        initRoutePlanSearch();
    }

    /**
     * 初始化路线规划搜素对象的方法,由子类去实现
     */
    protected abstract void initRoutePlanSearch();
}
