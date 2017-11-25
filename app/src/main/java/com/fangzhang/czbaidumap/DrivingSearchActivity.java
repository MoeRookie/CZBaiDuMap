package com.fangzhang.czbaidumap;

import com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/25.
 */

public class DrivingSearchActivity extends RoutePlanSearchBaseActivity {
    @Override
    protected void initRoutePlanSearch() {
        routePlanSearch.drivingSearch(getRoutePlanOption());
    }

    private DrivingRoutePlanOption getRoutePlanOption() {
        DrivingRoutePlanOption option = new DrivingRoutePlanOption();
        //通过指定经纬度确定出行节点信息
        PlanNode node = PlanNode.withLocation(hmPos);
        option.from(node);                // 设置起点
        List<PlanNode> planNodeList = new ArrayList<>();
        planNodeList.add(PlanNode.withCityNameAndPlaceName("北京市","天安门"));
        option.passBy(planNodeList);      // 设置途经点
        node = PlanNode.withLocation(czPos);
        option.to(node);                  // 设置终点
        return option;
    }

    /**
     * 获取驾车搜索结果的回调方法
     * @param drivingRouteResult
     */
    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
        if (drivingRouteResult == null || drivingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
            showToast("没有搜索到相关驾车路线信息");
            return;
        }
        DrivingRouteOverlay overlay = new DrivingRouteOverlay(mapController);
        mapController.setOnMarkerClickListener(overlay);                            // 给覆盖物设置点击事件的监听
        List<DrivingRouteLine> routeLines = drivingRouteResult.getRouteLines();     // 获取到所有的搜索路线,最优化的路线会在集合的前面
        DrivingRouteLine line = routeLines.get(0);
        overlay.setData(line);                                                      // 把搜索结果设置给覆盖物
        overlay.addToMap();
        overlay.zoomToSpan();                                                       // 让覆盖物一屏显示
    }

    /**
     * 获取步行搜索结果的回调方法
     * @param walkingRouteResult
     */
    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
    }

    /**
     * 获取换乘(公交、地铁)搜索结果的回调方法
     * @param transitRouteResult
     */
    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
    }
}
