package com.fangzhang.czbaidumap;

import com.baidu.mapapi.overlayutil.TransitRouteOverlay;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;

import java.util.List;

/**
 * Created by Administrator on 2017/11/25.
 */

public class TransitSearchActivity extends RoutePlanSearchBaseActivity {
    @Override
    protected void initRoutePlanSearch() {
        routePlanSearch.transitSearch(getRoutePlanOption());
    }

    private TransitRoutePlanOption getRoutePlanOption() {
        TransitRoutePlanOption option = new TransitRoutePlanOption();
        option.city("北京");               // 设置城市
        // 设置换乘策略 - 时间优先
        option.policy(TransitRoutePlanOption.TransitPolicy.EBUS_TIME_FIRST);
        //通过指定经纬度确定出行节点信息
        PlanNode node = PlanNode.withLocation(hmPos);
        option.from(node);                // 设置起点
        node = PlanNode.withLocation(czPos);
        option.to(node);                  // 设置终点
        return option;
    }

    /**
     * 获取换乘(公交、地铁)搜索结果的回调方法
     * @param transitRouteResult
     */
    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
        if (transitRouteResult == null || transitRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
            showToast("没有搜索到相关换乘路线信息");
            return;
        }
        TransitRouteOverlay overlay = new TransitRouteOverlay(mapController);
        mapController.setOnMarkerClickListener(overlay);                            // 给覆盖物设置点击事件的监听
        List<TransitRouteLine> routeLines = transitRouteResult.getRouteLines();     // 获取到所有的搜索路线,最优化的路线会在集合的前面
        TransitRouteLine line = routeLines.get(0);
        overlay.setData(line);                                                      // 把搜索结果设置给覆盖物
        overlay.addToMap();                                                         // 把覆盖物显示到地图上
        overlay.zoomToSpan();                                                       // 把搜索结果在一个屏幕内显示完
    }
    /**
     * 获取驾车搜索结果的回调方法
     * @param drivingRouteResult
     */
    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {                                                    // 让覆盖物一屏显示
    }

    /**
     * 获取步行搜索结果的回调方法
     * @param walkingRouteResult
     */
    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
    }
}
