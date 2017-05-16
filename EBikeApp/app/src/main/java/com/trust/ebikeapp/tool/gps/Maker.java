package com.trust.ebikeapp.tool.gps;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.trust.ebikeapp.tool.TimeTool;
import com.trust.ebikeapp.tool.bean.CarLoationMessage;

/**
 * Created by Trust on 2017/5/16.
 */

public class Maker {

    public static void showMaker (AMap map, CarLoationMessage message){
        LatLng data = new LatLng(message.getLat(),message.getLon());
        map.addMarker(new MarkerOptions().
                position(data).
                title(message.getType()).snippet(TimeTool.getTime(message.getGpsTime()))).showInfoWindow();

        map.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
                new LatLng(data.latitude,data.longitude),//新的中心点坐标
                500, //新的缩放级别
                0, //俯仰角0°~45°（垂直与地图时为0）
                0  ////偏航角 0~360° (正北方为0)
        )));
    }
}
