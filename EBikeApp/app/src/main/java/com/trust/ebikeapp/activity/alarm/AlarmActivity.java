package com.trust.ebikeapp.activity.alarm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.BaseActivity;
import com.trust.ebikeapp.activity.carhistroy.ChooseTimeActivity;
import com.trust.ebikeapp.tool.L;
import com.trust.ebikeapp.tool.TimeTool;
import com.trust.ebikeapp.tool.bean.AlarmBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class AlarmActivity extends BaseActivity {
    private Context context = AlarmActivity.this;
    private int pageIndex = 0,pageSize = 10;
    private RecyclerView recyclerView;
    private AlarmRecyclerViewAdapter adapter;
    private TextView nothingTv,timeTv;
    private long ontime = TimeTool.getSystemTimeDate();
    private long startTime,endTime;
    private int requestCode = 1;
    private List<AlarmBean.ContentBean.AlarmsBean> beanList = new ArrayList<>();
    private Button readBtn,noReadBtn;
    private int status;
    private ImageButton backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_activity);
        initView();
        initDate(ontime,ontime);
    }

    private void initView() {
        timeTv = (TextView) findViewById(R.id.alarm_activity_time);
        nothingTv = (TextView) findViewById(R.id.alarm_activity_nothing);

        recyclerView = (RecyclerView) findViewById(R.id.alarm_activity_recycler_view);
        adapter = new AlarmRecyclerViewAdapter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        String time = TimeTool.getTime(ontime);
        timeTv.setText(time+" ~ "+time);

        readBtn = (Button) findViewById(R.id.alarm_activity_read_alarm_status);
        noReadBtn = (Button) findViewById(R.id.alarm_activity_no_read_alarm_status);
        onClick(readBtn);
        onClick(noReadBtn);

        backBtn = (ImageButton) findViewById(R.id.alarm_activity_back);
        onClick(backBtn);
    }

    private void initDate(long onFire,long offFire) {
//        List<String> ml = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            ml.add("this is test :"+i);
//        }
//        adapter.setMl(ml);
//        adapter.notifyDataSetChanged();


        Map<String ,Object> map = new WeakHashMap<>();
        map.put("termId", Config.termId);
        map.put("startTime", onFire);
        map.put("endTime", offFire);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        requestCallBeack(Config.car_alarm,map,Config.carAlarm,Config.needAdd);

    }


    public void chooseTime(View v){
        Intent intent = new Intent(context,ChooseTimeActivity.class);
        intent.putExtra("title","查询报警信息");

        startActivityForResult(intent,requestCode);
    }

    @Override
    public void successCallBeack(Object obj, int type) {
        if(type == Config.carAlarm){
            AlarmBean bean = (AlarmBean) obj;
            if(bean != null){
                if(bean.getContent().getAlarms().size() == 0){
                    if(nothingTv.getVisibility() == View.GONE && bean.getContent().getAlarms().size() != 0){
                        showWaitToast(context,"已经是最后一页数据了!",1);
                    }else{
                        nothingTv.setVisibility(View.VISIBLE);
                    }
                }else{
                    nothingTv.setVisibility(View.GONE);
                    pageIndex++;
                    showWaitToast(context,"加载成功",1);
                    beanList.addAll(bean.getContent().getAlarms());
                    adapter.setMl(beanList);
                    adapter.notifyDataSetChanged();
                }
            }
        }else if(type == Config.alarmStatus){

            if(pageIndex != 0){
                -- pageIndex ;
            }
            beanList.clear();
            if (startTime == 0) {
                initDate(ontime,ontime);
            }else{
                initDate(startTime,endTime);
            }
            showWaitToast(context,"选择成功",1);
        }
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
                    if(fireOffTimeDate != 0){
                        beanList.clear();
                        startTime = fireOnTimeDate;
                        endTime = fireOffTimeDate;
                        initDate(startTime,endTime);
                        timeTv.setText(fireOnTime+" ~ "+fireOffTime);
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
        }

    }

    private void requestDate() {
        Map<String,Object> map = new WeakHashMap<>();
        map.put("termId", Config.termId);
        map.put("status",status);
        requestCallBeack(Config.alarm_status,map,Config.alarmStatus,Config.needAdd);
    }
}
