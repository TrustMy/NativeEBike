package com.phonegap.ebike.tool.gps;


import android.content.Context;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.phonegap.ebike.Config;
import com.phonegap.ebike.tool.L;
import com.phonegap.ebike.tool.T;
import com.phonegap.ebike.tool.gps.gpsconfig.WalkRouteOverlay;


/**
 * Created by Trust on 2016/12/17.
 */
public class GPSRoutePlanning implements RouteSearch.OnRouteSearchListener{

    private RouteSearch routeSearch;
    private Context context = Config.context;
    private RouteSearch.FromAndTo fromAndTol;
    private LatLonPoint startLat;
    private LatLonPoint endLat;
    private AMap aMap;




//    private AMapNavi mAMapNavi;


    public GPSRoutePlanning( LatLonPoint start, LatLonPoint end, AMap aMap) {

        this.startLat = start;
        this.endLat = end;
        this.aMap = aMap;

        fromAndTol = new RouteSearch.FromAndTo(this.startLat,this.endLat);
        init();
    }

    private void init() {
        routeSearch = new RouteSearch(context);
        routeSearch.setRouteSearchListener(this);

        RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTol, 1);
        routeSearch.calculateWalkRouteAsyn(query);//开始算路



//        //获取AMapNavi实例
//        mAMapNavi = AMapNavi.getInstance(context);
//        //添加监听回调，用于处理算路成功
//        mAMapNavi.addAMapNaviListener(this);


    }


    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {
        L.i("onBusRouteSearched");
    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {
        L.i("onDriveRouteSearched");
    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

       if (i == 3003) {
           Toast.makeText(context,"距离车辆位置过长!",Toast.LENGTH_SHORT).show();
        }else if(i ==3001)
        {
            Toast.makeText(context,"附近搜不到路!",Toast.LENGTH_SHORT).show();
        }else if(i == 3002)
        {
            Toast.makeText(context,"路线计算失败!",Toast.LENGTH_SHORT).show();
        }else if(i == 3000)
        {
            Toast.makeText(context,"不在中国陆地范围内!",Toast.LENGTH_SHORT).show();
        }else
        {

            WalkPath walkPath = walkRouteResult.getPaths().get(0);


            WalkRouteOverlay walkRouteOverlay = new WalkRouteOverlay(context,
                    aMap, walkPath, walkRouteResult.getStartPos(),
                    walkRouteResult.getTargetPos());
            walkRouteOverlay.removeFromMap();
            walkRouteOverlay.addToMap();
            walkRouteOverlay.zoomToSpan();
        }




    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {
        L.d("onRideRouteSearched");
    }

    public void setFromAndTo(LatLonPoint start , LatLonPoint end){
        this.startLat = start;
        this.endLat = end;
    }


}
