package com.fangzhang.czbaidumap;

import com.baidu.mapapi.overlayutil.TransitRouteOverlay;
import com.baidu.mapapi.overlayutil.WalkingRouteOverlay;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;

import java.util.List;

/**
 * Created by Administrator on 2017/11/25.
 */

public class WalkingSearchActivity extends RoutePlanSearchBaseActivity {
    @Override
    protected void initRoutePlanSearch() {
        routePlanSearch.walkingSearch(getRoutePlanOption());
    }

    private WalkingRoutePlanOption getRoutePlanOption() {
        WalkingRoutePlanOption option = new WalkingRoutePlanOption();
        //通过指定经纬度确定出行节点信息
        PlanNode node = PlanNode.withLocation(hmPos);
        option.from(node);                // 设置起点
        node = PlanNode.withLocation(czPos);
        option.to(node);                  // 设置终点
        return option;
    }

    /**
     * 获取步行搜索结果的回调方法
     * @param walkingRouteResult
     */
    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
        if (walkingRouteResult == null || walkingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
            showToast("没有搜索到相关换乘路线信息");
            return;
        }
        WalkingRouteOverlay overlay = new WalkingRouteOverlay(mapController);
        mapController.setOnMarkerClickListener(overlay);                               // 给覆盖物设置点击事件的监听
        List<WalkingRouteLine> routeLines = walkingRouteResult.getRouteLines();     // 获取到所有的搜索路线,最优化的路线会在集合的前面
        WalkingRouteLine line = routeLines.get(0);
        overlay.setData(line);                                                      // 把搜索结果设置给覆盖物
        overlay.addToMap();                                                         // 把覆盖物显示到地图上
        overlay.zoomToSpan();                                                       // 把搜索结果在一个屏幕内显示完
    }


    /**
     * 获取换乘(公交、地铁)搜索结果的回调方法
     * @param transitRouteResult
     */
    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
    }
    /**
     * 获取驾车搜索结果的回调方法
     * @param drivingRouteResult
     */
    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
    }
}
