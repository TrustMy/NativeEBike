package com.trust.ebikeapp.activity.carhistroy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.BaseActivity;
import com.trust.ebikeapp.tool.L;
import com.trust.ebikeapp.tool.TimeTool;
import com.trust.ebikeapp.tool.bean.CarStrokeBean;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class CarHistroyActivity extends BaseActivity {
    private Context context = CarHistroyActivity.this;
    private int pageIndex = 0,pageSize = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_histroy);
        init();
    }

    private void init() {
        Map<String ,Object> map = new WeakHashMap<>();
        map.put("termId",Config.termId);
        map.put("startTime", 1487174400000L);
        map.put("endTime", 1487347200000L);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);

        requestCallBeack(Config.car_stroke,map,Config.carStroke,Config.needAdd);
    }


    @Override
    public void successCallBeack(Object obj, int type) {
        pageIndex++;
        List<CarStrokeBean.ContentBean.TripsBean> ml = (List<CarStrokeBean.ContentBean.TripsBean>) obj;
        if(ml.size() == 0){
            showWaitToast(context,"已经是最后一页数据了!",1);
        }else{
            showWaitToast(context,"加载成功",1);
        }
    }

    public void chooseTime(View v){
        startActivity(new Intent(context,ChooseTimeActivity.class));
    }


}
