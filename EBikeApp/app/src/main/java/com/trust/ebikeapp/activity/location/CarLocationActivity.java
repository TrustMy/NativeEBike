package com.trust.ebikeapp.activity.location;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;
import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.BaseActivity;
import com.trust.ebikeapp.tool.T;
import com.trust.ebikeapp.tool.TimeTool;
import com.trust.ebikeapp.tool.bean.CarLoationMessage;
import com.trust.ebikeapp.tool.bean.LocationResultBean;
import com.trust.ebikeapp.tool.gps.DrawLiner;
import com.trust.ebikeapp.tool.gps.GPSRoutePlanning;
import com.trust.ebikeapp.tool.gps.Maker;
import com.trust.ebikeapp.tool.gps.Positioning;
import com.trust.ebikeapp.tool.internet.PostTrack;
import com.trust.ebikeapp.tool.trustinterface.PositionCallBack;
import com.trust.ebikeapp.tool.trustinterface.ResultCallBack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class CarLocationActivity extends BaseActivity {
    private MapView mapView;
    private AMap aMap;
    private Activity activity = CarLocationActivity.this;
    private Context context = CarLocationActivity.this;
    private ImageButton trackBtn,locationBtn,routePlanBtn,foundCarBtn;
    private boolean isTrack = false , isRoute = false , isFound = false;

    private LatLonPoint mDate;
    private Positioning positioning;
    private GPSRoutePlanning routePlanning;
    private int count = 0;
    private DrawLiner drawLiner;
    private List<LatLng> latLngs;

    private TickerView locationTimeTv;

    private int sumSecond = 300,minute,second;

    public ResultCallBack resultCallBack = new ResultCallBack() {
        @Override
        public void CallBeck(Object obj, int type, int status) {
            if(status == Config.SUCCESS){
                successCallBeackLocation(obj,type);
            }else{
                showErrorToast(context,obj.toString(),1);
            }
        }
    };

    /**
     * 实时追踪请求坐标成功回调
     * @param object
     * @param type
     */
    public void successCallBeackLocation(Object object , int type){
            if(type == Config.trickLocation){

                LocationResultBean bean = (LocationResultBean) object;
                if(bean.getContent().getLat() != 0.0 && bean.getContent().getType() != 1){
                    doTrickLine(bean);

                }else{
                    showErrorToast(context,"车辆定位无效,请将车辆挪到空旷地带!",1);
                }
            }
    }


    /**
     * 循环请求 轨迹
     */
    private Handler trickHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Config.trickLocation:
                    count++;

                    if(count <= 20){
                        PostTrack postTrack = new PostTrack(resultCallBack);
                        Map<String, Object> map = new WeakHashMap<>();
                        map.put("termId", Config.termId);
                        postTrack.Request(Config.car_location_url,map,Config.trickLocation,Config.needAdd);
                        trickHandler.sendEmptyMessageDelayed(Config.trickLocation,1000 * 15);
                    }else{
                        closeTrick();
                    }
                    break;
                case Config.locationTime:
                    showTime();
                    break;
            }


        }
    };

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
        drawLiner = new DrawLiner(context);
        latLngs = new ArrayList<>();
        carLocation();
    }

    private void initView() {
        trackBtn = (ImageButton) findViewById(R.id.activity_car_location_track);
        locationBtn = (ImageButton) findViewById(R.id.activity_car_location_car_location);
        routePlanBtn = (ImageButton) findViewById(R.id.activity_car_location_route_plan);
        foundCarBtn = (ImageButton) findViewById(R.id.activity_car_location_found_car);

        onClick(trackBtn);
        onClick(locationBtn);
        onClick(routePlanBtn);
        onClick(foundCarBtn);


        locationTimeTv = (TickerView) findViewById(R.id.activity_car_location_time);
        locationTimeTv.setCharacterList(TickerUtils.getDefaultListForUSCurrency());

    }

    @Override
    public void clickResult(View v) {
        isRoute = false;
        Map<String, Object> map = new WeakHashMap<>();
        long appSn = TimeTool.getSystemTimeDate()/1000;
        switch (v.getId()){

            case R.id.activity_car_location_track:
                isTrack(map,appSn);

                break;

            case R.id.activity_car_location_car_location:
                carLocation();
                break;

            case R.id.activity_car_location_route_plan:
                isRoute = true;
                carLocation();
                break;

            case R.id.activity_car_location_found_car:
                foundCar(map,appSn);
                break;


//            case R.id.activity_car_location_track_close:
//                isTrack(map,appSn);
//                break;

        }
    }

    /**
     * 车辆位置
     */
    private void carLocation() {
        Map<String, Object> map = new WeakHashMap<>();
        map.put("termId", Config.termId);
        requestCallBeack(Config.car_location_url,map,Config.location,Config.needAdd);
    }


    /**
     * 打开/关闭实时追踪
     * @param map
     */
    private void isTrack(Map<String, Object> map ,long appSn) {
        int interval;int durationtime;
        if(isTrack){
            //结束实时追踪
            interval = Config.endInterval;
            durationtime = Config.endDurationtime;
        }else{
            //开始实时追踪
            interval = Config.startInterval;
            durationtime = Config.startDurationtime;
        }

        map.put("termId", Config.termId);
        map.put("userCellPhone",Config.phone);
        map.put("appSN", appSn);
        map.put("interval", interval);
        map.put("duration", durationtime);

        requestCallBeack(Config.car_time_tracking_lcation_url,map,Config.isTrack,
                Config.needAdd);
    }

    /**
     * 寻车
     * @param map
     *
     */
    private void foundCar(Map<String, Object> map,long appSN) {
        map.put("termId", Config.termId);
        map.put("userCellPhone", Config.phone);
        map.put("appSN", appSN);
        if(isFound){
            //结束
            map.put("on", false);
        }else{
           //开始寻车
            map.put("on", true);
        }
        requestCallBeack(Config.car_buzzer,map,Config.foundCar,Config.needAdd);
    }

    /**
     * 路径规划
     * @param carLoationMessage
     */
    private void route(final CarLoationMessage carLoationMessage) {
        if(mDate!=null){
            if(carLoationMessage.getGpsType() == 1){
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("当前数据为基站数据,可能与实际位置有所偏差是否继续?");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setPositiveButton("继续", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LatLonPoint startDate = new LatLonPoint(carLoationMessage.getLat(),carLoationMessage.
                                getLon());

                        routePlanning = new GPSRoutePlanning(startDate,mDate,aMap);
//
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }else{
                LatLonPoint startDate = new LatLonPoint(carLoationMessage.getLat(),carLoationMessage.
                        getLon());
                routePlanning = new GPSRoutePlanning(startDate,mDate,aMap);

            }

        }
    }

    /**
     * 获取定位坐标
     * @param data
     */
    private PositionCallBack positionCallBack = new PositionCallBack() {
        @Override
        public void positionCallBack(LatLonPoint data) {
            mDate = data;
        }
    };

    @Override
    public void successCallBeack(Object obj, int type) {
        aMap.clear();
        switch (type){
            case Config.location:
                CarLoationMessage carLoationMessage = (CarLoationMessage) obj;
                if(!isRoute){
                    Maker.showMakerGif(aMap,carLoationMessage);

                }else{
                    //路径规划
                    route(carLoationMessage);
                }
                break;

            case Config.isTrack:
                if (isTrack) {
                    closeTrick();
                    locationTimeTv.setVisibility(View.GONE);
                }else{
                    isTrack = true;
                    trackBtn.setBackgroundResource(R.drawable.location_trick_on_btn_bg);
                    trickHandler.sendEmptyMessage(Config.trickLocation);
                    //开始
                    trickHandler.sendEmptyMessage(Config.locationTime);
                    locationTimeTv.setVisibility(View.VISIBLE);
                }

                break;

            case Config.foundCar:
                if(isFound){
                    isFound = false;
                    foundCarBtn.setBackgroundResource(R.drawable.location_found_car_btn_bg);
                }else{
                    isFound = true;
                    foundCarBtn.setBackgroundResource(R.drawable.location_trick_on_btn_bg);
                }
                break;

        }
    }


    /**
     * 切换关闭状态配置
     */
    private void closeTrick() {
        isTrack = false;
        trackBtn.setBackgroundResource(R.drawable.location_trick_off_btn_bg);
        latLngs.clear();
        trickHandler.removeMessages(Config.trickLocation);
        trickHandler.removeMessages(Config.locationTime);
        count = 0;
        sumSecond = 300;
        locationTimeTv.setText("05:00");
    }

    /**
     * 划线
     * @param bean
     */
    private void doTrickLine(LocationResultBean bean) {
        latLngs.add(new LatLng(bean.getContent().getLat(),bean.getContent().getLng()));
        drawLiner.drawTrickLine(aMap,latLngs);
    }

    private void showTime() {
        minute = sumSecond%3600/60;
        second = sumSecond%60;

        sumSecond--;
        if(second == 0 && minute == 0){
            trickHandler.removeMessages(Config.locationTime);

        }else{
            trickHandler.sendEmptyMessageDelayed(Config.locationTime,1000);
        }


        if(second < 10){
            locationTimeTv.setText("0"+minute+":0"+second);

        }else{

            locationTimeTv.setText("0"+minute+":"+second);
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

        if(sumSecond == 0){
            locationTimeTv.setVisibility(View.GONE);
        }

    }

    public void ext(View v){
        onClickFinsh(activity);
    }


}
