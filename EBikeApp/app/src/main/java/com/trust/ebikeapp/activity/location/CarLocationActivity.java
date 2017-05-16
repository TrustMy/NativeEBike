package com.trust.ebikeapp.activity.location;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.BaseActivity;
import com.trust.ebikeapp.tool.L;
import com.trust.ebikeapp.tool.T;
import com.trust.ebikeapp.tool.bean.CarLoationMessage;
import com.trust.ebikeapp.tool.gps.Maker;
import com.trust.ebikeapp.tool.gps.Positioning;
import com.trust.ebikeapp.tool.trustinterface.PositionCallBack;

import java.util.Map;
import java.util.WeakHashMap;

public class CarLocationActivity extends BaseActivity {
    private MapView mapView;
    private AMap aMap;
    private Activity activity = CarLocationActivity.this;
    private Context context = CarLocationActivity.this;
    private ImageButton trackBtn,locationBtn,routePlanBtn,foundCarBtn,closeTrackBtn,
    closeFoundCarBtn;
    private boolean isTrack = false , isRoute = false;

    private LatLng mDate;
    private Positioning positioning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_location);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_car_location_toolbar);
        setSupportActionBar(toolbar);

        //获取地图控件引用
        mapView = (MapView) findViewById(R.id.activity_car_location_map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
        }

        initView();
        initDate();
    }

    private void initDate() {
        positioning = new Positioning(positionCallBack);
    }

    private void initView() {
        trackBtn = (ImageButton) findViewById(R.id.activity_car_location_track);
        locationBtn = (ImageButton) findViewById(R.id.activity_car_location_car_location);
        routePlanBtn = (ImageButton) findViewById(R.id.activity_car_location_route_plan);
        foundCarBtn = (ImageButton) findViewById(R.id.activity_car_location_found_car);
        closeTrackBtn = (ImageButton) findViewById(R.id.activity_car_location_track_close);
        closeFoundCarBtn = (ImageButton) findViewById(R.id.
                activity_car_location_found_car_close);
        onClick(trackBtn);
        onClick(locationBtn);
        onClick(routePlanBtn);
        onClick(foundCarBtn);
        onClick(closeTrackBtn);
        onClick(closeFoundCarBtn);
    }

    @Override
    public void clickResult(View v) {
        isRoute = false;
        Map<String, Object> map = new WeakHashMap<>();
        switch (v.getId()){

            case R.id.activity_car_location_track:
                isTrack(map,Config.startInterval,Config.startDurationtime);
                break;

            case R.id.activity_car_location_car_location:
                carLocation();
                break;

            case R.id.activity_car_location_route_plan:
                isRoute = true;
                foundCar(map,Config.startFoundCarStatus);
                break;

            case R.id.activity_car_location_found_car:

                foundCar(map,Config.startFoundCarStatus);
                break;


            case R.id.activity_car_location_track_close:
                isTrack(map,Config.endInterval,Config.endDurationtime);
                break;


            case R.id.activity_car_location_found_car_close:
                foundCar(map,Config.endFoundCarStatus);
                break;

        }
    }

    /**
     * 车辆位置
     */
    private void carLocation() {
        Map<String, Object> map = new WeakHashMap<>();
        map.put("termId", Config.termId);
        post.Request(Config.car_location_url,map,Config.location,Config.needAdd);
    }


    /**
     * 打开/关闭实时追踪
     * @param map
     * @param interval
     * @param durationtime
     */
    private void isTrack(Map<String, Object> map ,int interval,int durationtime) {
        map.put("termId", Config.termId);
        map.put("userCellPhone",Config.phone);
        map.put("appSN", Config.appSN);
        map.put("interval", interval);
        map.put("duration", durationtime);
        post.Request(Config.car_time_tracking_lcation_url,map,Config.isTrack,
                Config.needAdd);
    }

    /**
     * 寻车
     * @param map
     * @param status
     */
    private void foundCar(Map<String, Object> map,boolean status) {
        map.put("termId", Config.termId);
        map.put("userCellPhone", Config.phone);
        map.put("appSN", Config.appSN);
        map.put("on", status);
        post.Request(Config.car_buzzer,map,Config.foundCar,Config.needAdd);
    }

    /**
     * 路径规划
     * @param carLoationMessage
     */
    private void route(CarLoationMessage carLoationMessage) {
        if(mDate!=null){
            if(carLoationMessage.getGpsType() == 1){
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("当前数据为基站数据,可能与实际位置有所偏差是否继续?");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        T.showToast(context,"已取消!");
                    }
                });
                builder.setPositiveButton("继续", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        initRoutePlanning(startLat, endLat, popopWindow);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }

    /**
     * 获取定位坐标
     * @param data
     */
    private PositionCallBack positionCallBack = new PositionCallBack() {
        @Override
        public void positionCallBack(LatLng data) {
            mDate = data;
        }
    };



    @Override
    public void resultCallBeack(Object obj, int type, int status) {

        if(status == Config.SUCCESS){
            switch (type){

                case Config.location:
                    CarLoationMessage carLoationMessage = (CarLoationMessage) obj;
                    if(!isRoute){
                        Maker.showMaker(aMap,carLoationMessage);

                    }else{
                        //路径规划
                        route(carLoationMessage);
                    }
                    break;

                case Config.isTrack:
                    L.d("isTrack"+obj.toString());
                    break;

                case Config.foundCar:
                    L.d("foundCar:"+obj.toString());
                    break;

            }

        }else{
            L.e(obj.toString());
        }
    }



    //-------------------------------------------------------------------------------------

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
      mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，
        // 保存地图当前的状态
       mapView.onSaveInstanceState(outState);
    }


    @Override
    protected void onResume() {
        super.onResume();

       mapView.onResume();
    }

    public void ext(View v){
        onClickFinsh(activity);
    }
}
