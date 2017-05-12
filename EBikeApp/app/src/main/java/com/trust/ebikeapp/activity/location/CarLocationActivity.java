package com.trust.ebikeapp.activity.location;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.BaseActivity;

public class CarLocationActivity extends BaseActivity {
    private MapView mapView;
    private AMap aMap;
    private Activity activity = CarLocationActivity.this;
    private Context context = CarLocationActivity.this;
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
    }


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
