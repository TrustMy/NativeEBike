package com.phonegap.ebike.activity.carstatus;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.phonegap.ebike.Config;
import com.phonegap.ebike.R;
import com.phonegap.ebike.activity.BaseActivity;
import com.phonegap.ebike.tool.TimeTool;
import com.phonegap.ebike.tool.bean.CarStatusBean;

import java.util.Map;
import java.util.WeakHashMap;

public class CarStatusActivity extends BaseActivity {
    private ImageView voltageImg , voltageStatusImg,getVoltageNumImg , carStatusImg;
    private ImageButton backBtn;
    private Context context = CarStatusActivity.this;
    private TextView electractNum ,voltageStatusTv,getVoltageNumTv,carStatusTv;
    private int [] Id = {R.drawable.watch0,R.drawable.watch1,R.drawable.watch2,R.drawable.watch3,
            R.drawable.watch4,R.drawable.watch5,R.drawable.watch6,R.drawable.watch7,R.drawable.watch8,
            R.drawable.watch9,R.drawable.watch10,R.drawable.electricity_off,R.drawable.electricity_on,
            R.drawable.warning_off,R.drawable.warning_on,R.drawable.electricity1,
            R.drawable.electricity2,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_status);
        initView();
        initDate();
    }

    private void initView() {
        voltageImg = (ImageView) findViewById(R.id.carstatus_voltage);
        voltageStatusImg = (ImageView) findViewById(R.id.carstatus_voltage_status);
        voltageStatusTv = (TextView) findViewById(R.id.carstatus_voltage_status_tv);
        getVoltageNumImg = (ImageView) findViewById(R.id.carstatus_voltage_num);
        getVoltageNumTv = (TextView) findViewById(R.id.carstatus_voltage_num_tv);
        carStatusImg = (ImageView) findViewById(R.id.carstatus_status);
        carStatusTv = (TextView) findViewById(R.id.carstatus_status_tv);
        electractNum = (TextView) findViewById(R.id.carstatus_electract_num);

        backBtn = (ImageButton) findViewById(R.id.carstatus_back);
        onClick(backBtn);
    }

    private void initDate() {
        long appSN = TimeTool.getSystemTimeDate();
        Map<String,Object>map = new WeakHashMap<>();
        map.put("termId",Config.termId);
        map.put("userCellPhone",Config.phone);
        map.put("appSN",appSN/1000);
        requestCallBeack(Config.car_status,map,Config.carStatus,Config.needAdd);
    }


    @Override
    public void successCallBeack(Object obj, int type) {
        CarStatusBean bean = (CarStatusBean) obj;
        voltage(bean);
        pullOut(bean);
        lowVoltage(bean);

    }

    private void lowVoltage(CarStatusBean bean) {
        int num = bean.getContent().getVoltagePercent();
        int type;
        String msg;
        if(num >30){
            type = 15;
            msg = "电量充足";
        }else{
            type = 16;
            msg = "电量不足";
        }
        getVoltageNumTv.setText(msg);
        showImg(type,getVoltageNumImg);
    }

    private void pullOut(CarStatusBean bean) {
        int type;
        String msg;
        //电池是否拔出
        if(bean.getContent().getPlugOutAlarm().equals("0")){//正常
            type = 11;
            msg = "电量插入";
        }else{
            type = 12;
            msg = "电量拔出";
        }
        voltageStatusTv.setText(msg);
        showImg(type,voltageStatusImg);
    }

    private void voltage(CarStatusBean bean) {
        int num = bean.getContent().getVoltagePercent();
        electractNum.setText(num+"");
        int type = 0;
        if(num == 100){
            type = 10;
        }else if(num >= 90){
            type = 9;
        }else if(num >= 80){
            type = 8;
        }else if(num >= 70){
            type = 7;
        }else if(num >= 60){
            type = 6;
        }else if(num >= 50){
            type = 5;
        }else if(num >= 40){
            type = 4;
        }else if(num >= 30){
            type = 3;
        }else if(num >= 20){
            type = 2;
        }else if(num >= 10){
            type = 1;
        }else{
            type = 0;
        }


        showImg(type,voltageImg);
    }

    public void showImg(int num,ImageView imageView){
        Glide.with(context).load(Id[num]).into(imageView);
    }

    public void reload(View v){
        initDate();
    }

    @Override
    public void clickResult(View v) {
        switch (v.getId()){
            case R.id.carstatus_back:
                finsh(this);
                break;
        }
    }
}
