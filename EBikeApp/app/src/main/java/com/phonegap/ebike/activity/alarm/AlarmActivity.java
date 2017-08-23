package com.phonegap.ebike.activity.alarm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phonegap.ebike.Config;
import com.phonegap.ebike.R;
import com.phonegap.ebike.activity.BaseActivity;
import com.phonegap.ebike.activity.carhistroy.ChooseTimeActivity;
import com.phonegap.ebike.tool.L;
import com.phonegap.ebike.tool.TextUtlis;
import com.phonegap.ebike.tool.TimeTool;
import com.phonegap.ebike.tool.bean.AlarmAddressAndroidBean;
import com.phonegap.ebike.tool.bean.AlarmBean;
import com.phonegap.ebike.tool.bean.AlarmLocationAddressBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AlarmActivity extends BaseActivity {
    TextView loadDateBtn;
    LinearLayout activityAlarmHistroyLoadDateLayout;

    private Context context = AlarmActivity.this;
    private int pageIndex = 0, pageSize = 10;
    private RecyclerView recyclerView;
    private AlarmRecyclerViewAdapter adapter;
    private TextView nothingTv, timeTv;
    private long ontime = TimeTool.getTime(TimeTool.getTime(TimeTool.getSystemTimeDate(),Config.timeTypeYears));
    private long offTime = 86399000;//offtime 结束时间+offtime就是当天23:59:59
    private long startTime, endTime;
    private int requestCode = 1;
    private List<AlarmBean.ContentBean.AlarmsBean> beanList = new ArrayList<>();
    private List<AlarmLocationAddressBean> addressList = new ArrayList();
    private Button readBtn, noReadBtn;
    private int status;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_activity);
        initView();
        initDate(ontime, ontime+offTime);
    }

    private void initView() {
        activityAlarmHistroyLoadDateLayout = (LinearLayout) findViewById(R.id.activity_alarm_histroy_load_date_layout);
        loadDateBtn = (TextView) findViewById(R.id.activity_alarm_histroy_load_date);

        timeTv = (TextView) findViewById(R.id.alarm_activity_time);
        nothingTv = (TextView) findViewById(R.id.alarm_activity_nothing);

        recyclerView = (RecyclerView) findViewById(R.id.alarm_activity_recycler_view);
        adapter = new AlarmRecyclerViewAdapter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setAlarmRecyclerAdapterClickListener = alarmRecyclerAdapterClickListener;
        recyclerView.setAdapter(adapter);


        String time = TimeTool.getTime(ontime, Config.timeTypeYears);
        timeTv.setText(time + " ~ " + time);

        readBtn = (Button) findViewById(R.id.alarm_activity_read_alarm_status);
        noReadBtn = (Button) findViewById(R.id.alarm_activity_no_read_alarm_status);
        onClick(readBtn);
        onClick(noReadBtn);

        backBtn = (ImageButton) findViewById(R.id.alarm_activity_back);
        onClick(backBtn);
        onClick(loadDateBtn);



        startTime = ontime;
        endTime = ontime+offTime;
    }

    private void initDate(long onFire, long offFire) {
//        List<String> ml = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            ml.add("this is test :"+i);
//        }
//        adapter.setMl(ml);
//        adapter.notifyDataSetChanged();


        Map<String, Object> map = new WeakHashMap<>();
        map.put("termId", Config.termId);
        map.put("startTime", onFire);
        map.put("endTime", offFire);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        requestCallBeack(Config.car_alarm, map, Config.carAlarm, Config.needAdd);

    }


    public void chooseTime(View v) {
        Intent intent = new Intent(context, ChooseTimeActivity.class);
        intent.putExtra("title", TextUtlis.getMsg(R.string.alarmTitle));

        startActivityForResult(intent, requestCode);
    }

    @Override
    public void successCallBeack(Object obj, int type) {
        if (type == Config.carAlarm) {
            AlarmAddressAndroidBean bean = (AlarmAddressAndroidBean) obj;
            if (bean != null) {
                if (bean.getAlarmsBeanList().size() == 0) {
                    if (beanList.size() != 0) {
                        loadDateBtn.setText(TextUtlis.getMsg(R.string.alarmLastData));
                        activityAlarmHistroyLoadDateLayout.setVisibility(View.VISIBLE);
                    } else {
                        nothingTv.setVisibility(View.VISIBLE);
                        activityAlarmHistroyLoadDateLayout.setVisibility(View.GONE);
                        beanList.clear();
                        addressList.clear();
                        adapter.setMl(beanList, addressList);
                        adapter.notifyDataSetChanged();

                        pageIndex = 0;
                    }
                } else {
                    nothingTv.setVisibility(View.GONE);
                    pageIndex++;
                    showWaitToast(context, "加载成功", 1);
                    beanList.addAll(bean.getAlarmsBeanList());
                    addressList.addAll(bean.getAlarmLocationAddressBeen());
                    adapter.setMl(beanList, addressList);
                    adapter.notifyDataSetChanged();
                    loadDateBtn.setText("点击加载");
                    activityAlarmHistroyLoadDateLayout.setVisibility(View.VISIBLE);
                }
            }
        } else if (type == Config.alarmStatus) {

            if (pageIndex != 0) {
                pageIndex = 0;
            }
            beanList.clear();
            if (startTime == 0) {
                initDate(ontime, ontime);
            } else {
                initDate(startTime, endTime);
            }
            showWaitToast(context, "选择成功", 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == this.requestCode) {
            if (data != null) {
                String fireOnTime = data.getStringExtra("fireOnTime");
                String fireOffTime = data.getStringExtra("fireOffTime");
                long fireOnTimeDate = data.getLongExtra("fireOnTimeDate", 0);
                long fireOffTimeDate = data.getLongExtra("fireOffTimeDate", 0);

                if (fireOffTime != null && !fireOnTime.equals("")) {
                    if (fireOffTimeDate != 0) {
                        beanList.clear();
                        startTime = fireOnTimeDate;
                        endTime = fireOffTimeDate;
                        pageIndex = 0;
                        initDate(startTime, endTime);
                        timeTv.setText(fireOnTime + " ~ " + fireOffTime);
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
            case R.id.alarm_activity_read_alarm_status:
                status = 1;
                requestDate();
                break;
            case R.id.alarm_activity_no_read_alarm_status:
                status = 0;
                requestDate();
                break;
            case R.id.alarm_activity_back:
                finsh(this);
                break;
            case R.id.activity_alarm_histroy_load_date:
                initDate(startTime, endTime);
                break;
        }

    }

    private void requestDate() {
        pageIndex = 0 ;
        Map<String, Object> map = new WeakHashMap<>();
        map.put("termId", Config.termId);
        map.put("status", status);
        requestCallBeack(Config.alarm_status, map, Config.alarmStatus, Config.needAdd);
    }


    AlarmRecyclerViewAdapter.alarmRecyclerAdapterClickListener alarmRecyclerAdapterClickListener = new AlarmRecyclerViewAdapter.alarmRecyclerAdapterClickListener() {
        @Override
        public void clickCallBack(View v, Object bean) {
            L.d("点击了 :" + bean);
        }
    };
}
