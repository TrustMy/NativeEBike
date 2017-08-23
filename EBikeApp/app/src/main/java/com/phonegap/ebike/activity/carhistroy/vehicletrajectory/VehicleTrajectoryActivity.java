package com.phonegap.ebike.activity.carhistroy.vehicletrajectory;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.phonegap.ebike.Config;
import com.phonegap.ebike.R;
import com.phonegap.ebike.activity.BaseActivity;
import com.phonegap.ebike.tool.L;
import com.phonegap.ebike.tool.bean.HistroyGpsBean;
import com.phonegap.ebike.tool.bean.VehicleTrajectoryBean;
import com.phonegap.ebike.tool.gps.CoordinateTransformation;
import com.phonegap.ebike.tool.gps.DrawLiner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VehicleTrajectoryActivity extends BaseActivity {
    private MapView mapView;
    private AMap aMap;
    private Context context = VehicleTrajectoryActivity.this;
    private HistroyGpsBean gpsMessage;

    private CoordinateTransformation coordinateTransFormation;
    private     List<LatLng> ml = new ArrayList<>();
    private String startName,endName;
    private ImageButton backBtn,updateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_trajectory);
        mapView = (MapView) findViewById(R.id.vehicle_trajectory_activity_map_view);
        mapView.onCreate(savedInstanceState);

        if (aMap == null) {
            aMap = mapView.getMap();
        }

        initView();
        initDate();

    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.vehicle_activity_toolbar);
        setSupportActionBar(toolbar);
        backBtn = (ImageButton) findViewById(R.id.vehicle_activity_back);
        onClick(backBtn);
        updateBtn = (ImageButton) findViewById(R.id.vehicle_activity_update);
        onClick(updateBtn);
    }

    private void initDate() {
        coordinateTransFormation = new CoordinateTransformation(context);
        gpsMessage = (HistroyGpsBean) getIntent().getSerializableExtra("gpsMessage");
        requestDate();
    }

    private void requestDate() {


        Map<String, Object> map = new WeakHashMap<String, Object>();
        map.put("termId", Config.termId);
        map.put("startTime", gpsMessage.getFireOnTime());
        map.put("endTime", gpsMessage.getFireOffTime());
        requestCallBeack(Config.car_history_location_url,map,Config.carHistoryLocation,Config.needAdd);
        startName = gpsMessage.getOnName();
        endName = gpsMessage.getOffName();
    }


    @Override
    public void successCallBeack(Object obj, int type) {
        if(type == Config.carHistoryLocation){
            VehicleTrajectoryBean bean = (VehicleTrajectoryBean) obj;
            if(bean.getContent().getGps().size() == 0){
                showErrorToast(context,"本次行程坐标无效!",3);
            }else{
                aMap.clear();
                ml.clear();
                doGpsDate(bean.getContent().getGps());
            }
        }
    }

    /**
     * 过滤 0点以及坐标转换
     * @param bean
     */
    private void doGpsDate(final List<VehicleTrajectoryBean.ContentBean.GpsBean> bean) {

         Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(@NonNull ObservableEmitter e) throws Exception {
                for (int i = 0; i < bean.size(); i++) {
                    if(bean.get(i).getLat() == 0.0){
                        continue;
                    }else{
                        LatLng latLng = coordinateTransFormation.transformation(new LatLng(bean.
                                get(i).getLat(), bean.get(i).getLng()));
                        ml.add(latLng);
                    }
                }

                List<LatLng> mlist = new ArrayList<>();
                for (int i = ml.size()-1; i >= 0 ; i--) {
                    mlist.add(ml.get(i));
                }



                e.onNext(mlist);

            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        drawTrack.drawTrackCallBack((List<LatLng>) o);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        L.e("e.toString:"+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    interface DoDrawTrack {
        void drawTrackCallBack(List<LatLng> ml);
    }

    /**
     * 画过滤 转换过的点
     */
    private DoDrawTrack drawTrack = new DoDrawTrack() {
        @Override
        public void drawTrackCallBack(List<LatLng> ml) {

            
            DrawLiner drawLiner = new DrawLiner(context);
            drawLiner.draw(aMap,ml,startName,endName);
        }
    };

    @Override
    public void clickResult(View v) {
        switch (v.getId()){
            case R.id.vehicle_activity_back:
                finsh(this);
                break;

            case R.id.vehicle_activity_update:
                requestDate();
                break;

        }
    }
}
