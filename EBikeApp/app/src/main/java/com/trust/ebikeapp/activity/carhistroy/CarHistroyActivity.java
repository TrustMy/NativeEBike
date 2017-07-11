package com.trust.ebikeapp.activity.carhistroy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.BaseActivity;
import com.trust.ebikeapp.activity.carhistroy.vehicletrajectory.CarHistroyRecyclerViewNowAdapter;
import com.trust.ebikeapp.activity.carhistroy.vehicletrajectory.VehicleTrajectoryActivity;
import com.trust.ebikeapp.tool.L;
import com.trust.ebikeapp.tool.TextUtlis;
import com.trust.ebikeapp.tool.TimeTool;
import com.trust.ebikeapp.tool.bean.CarStrokeAndAddress;
import com.trust.ebikeapp.tool.bean.CarStrokeBean;
import com.trust.ebikeapp.tool.bean.HistroyGpsBean;
import com.trust.ebikeapp.tool.bean.LocationAddressBean;
import com.trust.ebikeapp.tool.trustinterface.EndlessRecyclerOnScrollListener;
import com.trust.ebikeapp.tool.uitool.PercentLinearLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CarHistroyActivity extends BaseActivity {
    @InjectView(R.id.activity_car_histroy_load_date_layou)
    LinearLayout activityCarHistroyLoadDateLayou;
    @InjectView(R.id.activity_car_histroy_now_time)
    TextView activityCarHistroyNowTime;
    @InjectView(R.id.activity_car_histroy_new_update)
    ImageView activityCarHistroyNewUpdate;
    @InjectView(R.id.activity_car_histroy_now_recyclerview)
    RecyclerView activityCarHistroyNowRecyclerview;
    @InjectView(R.id.activity_car_histroy_now_layout)
    PercentLinearLayout activityCarHistroyNowLayout;
    private Context context = CarHistroyActivity.this;
    private int pageIndex = 0, pageSize = 10;
    private RecyclerView recyclerView;
    private CarHistroyRecyclerViewAdapter recyclerAdapter;
    private TextView messageTv, timeTv;

    private String message;

    private TextView loadDate;

    private CarStrokeAndAddress carStrokeAndAddress;
    private int requestCode = 1;

    private long whenTime = TimeTool.getTime(TimeTool.getTime(TimeTool.getSystemTimeDate(),Config.timeTypeYears));
    private long offTime = 86399000;//offtime 结束时间+offtime就是当天23:59:59


    private ImageButton bachBtn;
    private long fireOnTimeDate, fireOffTimeDate;

    private List<CarStrokeBean.ContentBean.TripsBean> tripsBeanList = new ArrayList<>();
    private List<LocationAddressBean> locatoinBeanList = new ArrayList<>();

    private CarHistroyRecyclerViewNowAdapter nowAdapter;

    private PercentLinearLayout imgLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_histroy);
        ButterKnife.inject(this);
        initView();
        init(whenTime, whenTime+offTime, pageIndex,100, Config.carNowStroke);
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
        recyclerAdapter.click = onClick;
        recyclerView.setAdapter(recyclerAdapter);


        messageTv = (TextView) findViewById(R.id.car_hirstory_msg);
        timeTv = (TextView) findViewById(R.id.activity_car_histroy_time);
        String time = TimeTool.getTime(whenTime, Config.timeTypeYears);
        timeTv.setText(time + " ~ " + time);

        loadDate = (TextView) findViewById(R.id.activity_car_histroy_load_date);
        onClick(loadDate);

        bachBtn = (ImageButton) findViewById(R.id.car_hirstory_back);
        onClick(bachBtn);

        nowAdapter = new CarHistroyRecyclerViewNowAdapter(context);
        LinearLayoutManager nowLayoutManager = new LinearLayoutManager(this);
        nowLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        activityCarHistroyNowRecyclerview.setLayoutManager(nowLayoutManager);
        nowAdapter.click = new CarHistroyRecyclerViewNowAdapter.onClick() {
            @Override
            public void clickCallBack(View v, HistroyGpsBean bean) {
                intentBeanDate(bean);
            }
        };
        activityCarHistroyNowRecyclerview.setAdapter(nowAdapter);


        activityCarHistroyNowTime.setText(TimeTool.getTime(TimeTool.getSystemTimeDate(),
                Config.timeTypeYears));

        onClick(activityCarHistroyNewUpdate);

        imgLayout = (PercentLinearLayout) findViewById(R.id.activity_car_histroy_img_layout);
    }


    private void init(long onFire, long offFire, int pageIndex,int pageSize, int type) {
        Map<String, Object> map = new WeakHashMap<>();
        map.put("termId", Config.termId);
        map.put("startTime", onFire);
        map.put("endTime", offFire);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);

        requestCallBeack(Config.car_stroke, map, type, Config.needAdd);
    }


    @Override
    public void successCallBeack(Object obj, int type) {
        if (type == Config.carStroke) {
            CarStrokeAndAddress ml = (CarStrokeAndAddress) obj;

            if (ml != null) {
                if (ml.getAddressList().size() == 0) {
                    if (tripsBeanList.size() != 0) {
                        activityCarHistroyLoadDateLayou.setVisibility(View.VISIBLE);
                        loadDate.setText(TextUtlis.getMsg(R.string.alarmLastData));
                        messageTv.setVisibility(View.GONE);


                    } else {
                        L.d("行程 为 000000");
                        activityCarHistroyLoadDateLayou.setVisibility(View.GONE);
                        messageTv.setVisibility(View.VISIBLE);

                        recyclerAdapter.setMl(tripsBeanList, locatoinBeanList);
                        recyclerAdapter.notifyDataSetChanged();

                    }
                } else {
                    pageIndex++;
                    messageTv.setVisibility(View.GONE);
                    showWaitToast(context, "加载成功", 1);
                    for (int i = 0; i < ml.getTripsBeenList().size(); i++) {
                        tripsBeanList.add(ml.getTripsBeenList().get(i));
                        locatoinBeanList.add(ml.getAddressList().get(i));
                    }


                    recyclerAdapter.setMl(tripsBeanList, locatoinBeanList);
                    recyclerAdapter.notifyDataSetChanged();
                    activityCarHistroyLoadDateLayou.setVisibility(View.VISIBLE);
                    loadDate.setText("点击继续加载");


                }
            }

        } else if (type == Config.carNowStroke) {
            List<CarStrokeBean.ContentBean.TripsBean> tripsBeanList = new ArrayList<>();
            CarStrokeAndAddress ml = (CarStrokeAndAddress) obj;
            if (ml != null) {
                if (ml.getTripsBeenList().size() != 0) {
                    imgLayout.setVisibility(View.GONE);
                    activityCarHistroyNowLayout.setVisibility(View.VISIBLE);
                    //今天位置recy

                    for (int i = 0; i < ml.getTripsBeenList().size(); i++) {
                        tripsBeanList.add(ml.getTripsBeenList().get(i));
                    }

                    nowAdapter.setMl(tripsBeanList, ml.getAddressList());
                    nowAdapter.notifyDataSetChanged();
                } else {
                    activityCarHistroyNowLayout.setVisibility(View.GONE);
                    imgLayout.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void chooseTime(View v) {
        Intent intent = new Intent(context, ChooseTimeActivity.class);
        intent.putExtra("title", TextUtlis.getMsg(R.string.carHistroytitle));

        startActivityForResult(intent, requestCode);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == this.requestCode) {
            if (data != null) {
                String fireOnTime = data.getStringExtra("fireOnTime");
                String fireOffTime = data.getStringExtra("fireOffTime");
                fireOnTimeDate = data.getLongExtra("fireOnTimeDate", 0);
                fireOffTimeDate = data.getLongExtra("fireOffTimeDate", 0);
                if (fireOffTime != null && !fireOnTime.equals("")) {
                    timeTv.setText(fireOnTime + " ~ " + fireOffTime);
                    if (fireOffTimeDate != 0) {
                        carStrokeAndAddress = null;
                        pageIndex = 0;
                        tripsBeanList.clear();
                        locatoinBeanList.clear();
                        init(fireOnTimeDate, fireOffTimeDate, pageIndex,pageSize, Config.carStroke);

                    } else {
                        L.e("fireOffTimeDate: = 0");
                    }

                } else {
                    L.e("fireOffTime == null");
                }

            }

        }
    }

    @Override
    public void clickResult(View v) {
        switch (v.getId()) {
            case R.id.car_hirstory_back:
                finsh(this);
                break;
            case R.id.activity_car_histroy_load_date:
                init(fireOnTimeDate, fireOffTimeDate, pageIndex,pageSize, Config.carStroke);
                break;

            case R.id.activity_car_histroy_new_update:
                init(whenTime, whenTime, 0,pageSize ,Config.carNowStroke);
                break;
        }
    }


    CarHistroyRecyclerViewAdapter.onClick onClick = new CarHistroyRecyclerViewAdapter.onClick() {
        @Override
        public void clickCallBack(View v, HistroyGpsBean bean) {
            L.d("bean getFireOnTime:" + bean.getFireOnTime() + "|bean getFireOnTime" + bean.
                    getFireOffTime() + "|onName:" + bean.getOnName() + "|offName:" + bean.getOffName());
            intentBeanDate(bean);
        }
    };

    private void intentBeanDate(Serializable bean) {
        Intent intent = new Intent(context, VehicleTrajectoryActivity.class);
        intent.putExtra("gpsMessage", bean);
        startActivity(intent);
    }
}
