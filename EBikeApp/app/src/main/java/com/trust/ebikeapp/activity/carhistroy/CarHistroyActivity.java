package com.trust.ebikeapp.activity.carhistroy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.BaseActivity;
import com.trust.ebikeapp.activity.carhistroy.vehicletrajectory.VehicleTrajectoryActivity;
import com.trust.ebikeapp.tool.L;
import com.trust.ebikeapp.tool.TimeTool;
import com.trust.ebikeapp.tool.bean.CarStrokeAndAddress;
import com.trust.ebikeapp.tool.bean.CarStrokeBean;
import com.trust.ebikeapp.tool.bean.HistroyGpsBean;
import com.trust.ebikeapp.tool.bean.LocationAddressBean;
import com.trust.ebikeapp.tool.trustinterface.EndlessRecyclerOnScrollListener;

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
    private Context context = CarHistroyActivity.this;
    private int pageIndex = 0, pageSize = 10;
    private RecyclerView recyclerView;
    private CarHistroyRecyclerViewAdapter recyclerAdapter;
    private TextView messageTv, timeTv;

    private String message;

    private TextView loadDate;

    private CarStrokeAndAddress carStrokeAndAddress;
    private int requestCode = 1;

    private long whenTime = TimeTool.getSystemTimeDate();

    private ImageButton bachBtn;
    private long fireOnTimeDate, fireOffTimeDate;

    private List<CarStrokeBean.ContentBean.TripsBean> tripsBeanList = new ArrayList<>();
    private List<LocationAddressBean> locatoinBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_histroy);
        ButterKnife.inject(this);
        initView();
        init(whenTime, whenTime);
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


    }


    private void init(long onFire, long offFire) {
        Map<String, Object> map = new WeakHashMap<>();
        map.put("termId", Config.termId);
        map.put("startTime", onFire);
        map.put("endTime", offFire);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);

        requestCallBeack(Config.car_stroke, map, Config.carStroke, Config.needAdd);
    }


    @Override
    public void successCallBeack(Object obj, int type) {
        if (type == Config.carStroke) {
            CarStrokeAndAddress ml = (CarStrokeAndAddress) obj;

            if (ml != null) {
                if (ml.getTripsBeenList().size() == 0) {
                    if (tripsBeanList.size() != 0) {
                        activityCarHistroyLoadDateLayou.setVisibility(View.VISIBLE);
                        loadDate.setText("已经是最后一条数据了!");
                        messageTv.setVisibility(View.GONE);
                    } else {
                        activityCarHistroyLoadDateLayou.setVisibility(View.GONE);
                        messageTv.setVisibility(View.VISIBLE);
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

        }
    }

    public void chooseTime(View v) {
        Intent intent = new Intent(context, ChooseTimeActivity.class);
        intent.putExtra("title", "查询历史信息");

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
                        init(fireOnTimeDate, fireOffTimeDate);

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
                init(fireOnTimeDate, fireOffTimeDate);
                break;
        }
    }


    CarHistroyRecyclerViewAdapter.onClick onClick = new CarHistroyRecyclerViewAdapter.onClick() {
        @Override
        public void clickCallBack(View v, HistroyGpsBean bean) {
            L.d("bean getFireOnTime:" + bean.getFireOnTime() + "|bean getFireOnTime" + bean.
                    getFireOffTime() + "|onName:" + bean.getOnName() + "|offName:" + bean.getOffName());
            Intent intent = new Intent(context, VehicleTrajectoryActivity.class);
            intent.putExtra("gpsMessage", (Serializable) bean);
            startActivity(intent);
        }
    };
}
