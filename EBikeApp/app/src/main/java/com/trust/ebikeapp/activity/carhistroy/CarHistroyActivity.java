package com.trust.ebikeapp.activity.carhistroy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.BaseActivity;
import com.trust.ebikeapp.activity.carhistroy.vehicletrajectory.VehicleTrajectoryActivity;
import com.trust.ebikeapp.tool.L;
import com.trust.ebikeapp.tool.TimeTool;
import com.trust.ebikeapp.tool.bean.CarStrokeAndAddress;
import com.trust.ebikeapp.tool.bean.HistroyGpsBean;
import com.trust.ebikeapp.tool.trustinterface.EndlessRecyclerOnScrollListener;
import com.trust.ebikeapp.tool.bean.CarStrokeBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class CarHistroyActivity extends BaseActivity {
    private Context context = CarHistroyActivity.this;
    private int pageIndex = 0,pageSize = 10;
    private RecyclerView recyclerView;
    private CarHistroyRecyclerViewAdapter recyclerAdapter;
    private TextView  messageTv ,timeTv;

    private String message;

    private CarStrokeAndAddress carStrokeAndAddress;
    private int requestCode = 1;

    private long whenTime = TimeTool.getSystemTimeDate();

    private ImageButton bachBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_histroy);
        initView();
        init(whenTime,whenTime);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.activity_car_histroy_recyclerview);
        recyclerAdapter = new CarHistroyRecyclerViewAdapter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {


            }
        });
        recyclerView.setAdapter(recyclerAdapter);

        recyclerAdapter.click  = new CarHistroyRecyclerViewAdapter.onClick() {
            @Override
            public void clickCallBack(View v, HistroyGpsBean bean) {
                L.d("bean getFireOnTime:"+bean.getFireOnTime()+"|bean getFireOnTime"+bean.
                        getFireOffTime()+"|onName:"+bean.getOnName()+"|offName:"+bean.getOffName());
                Intent intent = new Intent(context, VehicleTrajectoryActivity.class);
                intent.putExtra("gpsMessage", (Serializable) bean);
                startActivity(intent);
            }
        };


        messageTv = (TextView) findViewById(R.id.car_hirstory_msg);
        timeTv = (TextView) findViewById(R.id.activity_car_histroy_time);
        String time = TimeTool.getTime(whenTime);
        timeTv.setText(time+" ~ "+time);

        bachBtn = (ImageButton) findViewById(R.id.car_hirstory_back);
        onClick(bachBtn);
    }




    private void init(long onFire,long offFire) {
        Map<String ,Object> map = new WeakHashMap<>();
        map.put("termId",Config.termId);
        map.put("startTime", onFire);
        map.put("endTime", offFire);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);

        requestCallBeack(Config.car_stroke,map,Config.carStroke,Config.needAdd);
    }


    @Override
    public void successCallBeack(Object obj, int type) {

        CarStrokeAndAddress ml = (CarStrokeAndAddress) obj;

        if(ml.getTripsBeenList().size() == 0){
            if(messageTv.getVisibility() == View.GONE && carStrokeAndAddress.getAddressList().size() != 0){
                showWaitToast(context,"已经是最后一页数据了!",1);
            }else{
                messageTv.setVisibility(View.VISIBLE);
            }
        }else{
            pageIndex++;
            messageTv.setVisibility(View.GONE);
            showWaitToast(context,"加载成功",1);
            if(carStrokeAndAddress != null){
                carStrokeAndAddress.getTripsBeenList().addAll(ml.getTripsBeenList());
                carStrokeAndAddress.getAddressList().addAll(ml.getAddressList());
            }else{
                carStrokeAndAddress = ml;
            }

            recyclerAdapter.setMl(carStrokeAndAddress);
            recyclerAdapter.notifyDataSetChanged();
        }
    }

    public void chooseTime(View v){
        Intent intent = new Intent(context,ChooseTimeActivity.class);
        intent.putExtra("title","查询历史信息");

        startActivityForResult(intent,requestCode);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == this.requestCode){
            if(data != null){
            String fireOnTime = data.getStringExtra("fireOnTime");
            String fireOffTime = data.getStringExtra("fireOffTime");
            long fireOnTimeDate = data.getLongExtra("fireOnTimeDate",0);
            long fireOffTimeDate = data.getLongExtra("fireOffTimeDate",0);
            if(fireOffTime != null && !fireOnTime.equals("")){
                timeTv.setText(fireOnTime+" ~ "+fireOffTime);
                if(fireOffTimeDate != 0){
                    init(fireOnTimeDate,fireOffTimeDate);

                }else{
                    L.e("fireOffTimeDate: = 0");
                }

            }else{
                L.e("fireOffTime == null");
            }

            }

        }
    }

    @Override
    public void clickResult(View v) {
        switch (v.getId()){
            case R.id.car_hirstory_back:
                finsh(this);
                break;
        }
    }
}
