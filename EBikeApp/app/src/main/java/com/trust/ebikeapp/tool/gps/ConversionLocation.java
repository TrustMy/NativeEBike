package com.trust.ebikeapp.tool.gps;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.tool.L;
import com.trust.ebikeapp.tool.bean.CarStrokeBean;
import com.trust.ebikeapp.tool.bean.LocationAddressBean;
import com.trust.ebikeapp.tool.internet.PostResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

/**
 * Created by Trust on 2017/5/19.
 */

public class ConversionLocation implements Runnable{
    private GeocodeSearch geocodeSearch;
    private List<CarStrokeBean.ContentBean.TripsBean> ml;
    private List<String> onList = new ArrayList<>();
    private List<String> offList = new ArrayList<>();
    private PostResult.gpsLocationAddress addressCallBack;
    private boolean onStatus = false,offStatus = false;

    private String err = "获取位置失败!";
    List<LocationAddressBean> mls = new ArrayList<>();
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 0://起点
                    onStatus = true;
                    break;
                case 1://终点
                    offStatus = true;
                    break;
            }

            if(onStatus && offStatus && onList.size() == offList.size() ){

                for (int i = 0; i < onList.size(); i++) {
                    mls.add(new LocationAddressBean(onList.get(i),offList.get(i)));
                }
                addressCallBack.addressCallBack(mls);
                onStatus = false;
                offStatus = false;
            }else{
                L.e("onStatus:"+onStatus+"|offStatus:"+offStatus+"|onList.size():"+onList.size()+"|offList.size():"+offList.size());
            }

        }
    };


    public ConversionLocation(List<CarStrokeBean.ContentBean.TripsBean> ml, PostResult.gpsLocationAddress gpsLocationAddress) {
        this.ml = ml;
        addressCallBack = gpsLocationAddress;
    }

    private void init() {
        geocodeSearch =  new GeocodeSearch(Config.context);
        geocodeSearch.setOnGeocodeSearchListener(onGeocodeSearchListener);


        batchConversion(ml);
    }

    private GeocodeSearch.OnGeocodeSearchListener   onGeocodeSearchListener = new GeocodeSearch.OnGeocodeSearchListener() {
        @Override
        public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
            L.d("地理解析返回码:"+i);
        }

        @Override
        public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

        }
    };


    public void batchConversion(List<CarStrokeBean.ContentBean.TripsBean> ml){


        if(ml !=null && ml.size() != 0){


            List<LatLonPoint> fireOnList = new ArrayList<>();
            List<LatLonPoint> fireOffList = new ArrayList<>();
            for(CarStrokeBean.ContentBean.TripsBean bean :ml){
                fireOnList.add(new LatLonPoint(bean.getFireOnLat(),bean.getFireOnLng()));
                fireOffList.add(new LatLonPoint(bean.getFireOffLat(),bean.getFireOffLng()));
            }

            //起点
            for(LatLonPoint point : fireOnList){
                try {
                    RegeocodeQuery query = new RegeocodeQuery(point, 200,
                            GeocodeSearch.GPS);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
                    RegeocodeAddress result = geocodeSearch.getFromLocation(query);// 设置同步逆地理编码请求
                    if(result != null && result.getFormatAddress() != null && !result.getFormatAddress().equals("")){

                        String address = result.getFormatAddress()+"附近";

                        onList.add(address);
                    }else{
                        onList.add(err);
                    }

                } catch (AMapException e) {
                    e.printStackTrace();
                    L.e(e.toString());
                    onList.add(err);
                }

            }

            handler.sendEmptyMessage(0);




            //终点
            for(LatLonPoint point : fireOffList){
                try {
                    RegeocodeQuery query = new RegeocodeQuery(point, 200,
                            GeocodeSearch.GPS);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
                    RegeocodeAddress result = geocodeSearch.getFromLocation(query);// 设置同步逆地理编码请求
                    if(result != null && result.getFormatAddress() != null){

                        String address = result.getFormatAddress()+"附近";

                        offList.add(address);
                    }

                } catch (AMapException e) {
                    e.printStackTrace();
                    L.e(e.toString());
                    offList.add("获取位置失败!");
                }

            }


            handler.sendEmptyMessage(1);

        }else{
            L.e("ml == null || ml == 0");
            addressCallBack.addressCallBack(mls);
        }
    }

    @Override
    public void run() {
        Looper.prepare();
        init();
        Looper.loop();
    }
}
