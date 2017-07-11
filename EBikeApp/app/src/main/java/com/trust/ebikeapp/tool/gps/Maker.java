package com.trust.ebikeapp.tool.gps;

import android.graphics.Bitmap;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.bumptech.glide.load.data.DataFetcher;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.tool.TimeTool;
import com.trust.ebikeapp.tool.bean.CarLoationMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trust on 2017/5/16.
 */

public class Maker {

    public static void showMaker (AMap map, CarLoationMessage message){
        LatLng data = new LatLng(message.getLat(),message.getLon());
        map.addMarker(new MarkerOptions().
                position(data).
                title(message.getType()).snippet(TimeTool.getTimeAll(message.getGpsTime()))).showInfoWindow();

        map.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
                new LatLng(data.latitude,data.longitude),//新的中心点坐标
                500, //新的缩放级别
                0, //俯仰角0°~45°（垂直与地图时为0）
                0  ////偏航角 0~360° (正北方为0)
        )));
    }

    public static void showMaker(AMap map, double lat, double lon){
        LatLng data = new LatLng(lat,lon);
        map.addMarker(new MarkerOptions().
                position(data));
        map.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
                new LatLng(data.latitude,data.longitude),//新的中心点坐标
                500, //新的缩放级别
                0, //俯仰角0°~45°（垂直与地图时为0）
                0  ////偏航角 0~360° (正北方为0)
        )));
    }
    public static void showMakerGif(AMap aMap,CarLoationMessage message , boolean need , int size){
        LatLng data = new LatLng(message.getLat(),message.getLon());
        MarkerOptions markerOptions = new MarkerOptions();
        ArrayList<BitmapDescriptor> ml = new ArrayList<>();

        ml.add(BitmapDescriptorFactory.fromResource(R.drawable.gif1));
        ml.add(BitmapDescriptorFactory.fromResource(R.drawable.gif2));
        ml.add(BitmapDescriptorFactory.fromResource(R.drawable.gif3));
        ml.add(BitmapDescriptorFactory.fromResource(R.drawable.gif4));
        ml.add(BitmapDescriptorFactory.fromResource(R.drawable.gif5));
        ml.add(BitmapDescriptorFactory.fromResource(R.drawable.gif6));
        ml.add(BitmapDescriptorFactory.fromResource(R.drawable.gif7));
        ml.add(BitmapDescriptorFactory.fromResource(R.drawable.gif8));
        ml.add(BitmapDescriptorFactory.fromResource(R.drawable.gif9));
        ml.add(BitmapDescriptorFactory.fromResource(R.drawable.gif10));
        ml.add(BitmapDescriptorFactory.fromResource(R.drawable.gif11));
        ml.add(BitmapDescriptorFactory.fromResource(R.drawable.gif12));

        markerOptions.icons(ml);
        markerOptions.position(data);
        if(need){
            markerOptions.title(message.getType());
        }else{
            markerOptions.title("实时追踪");
        }
        markerOptions.snippet(TimeTool.getTimeAll(message.getGpsTime()));
        markerOptions.period(3);

        aMap.addMarker(markerOptions).showInfoWindow();
        aMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
                new LatLng(data.latitude,data.longitude),//新的中心点坐标
                size, //新的缩放级别
                0, //俯仰角0°~45°（垂直与地图时为0）
                0  ////偏航角 0~360° (正北方为0)
        )));
    }
}
