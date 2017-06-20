package com.trust.ebikeapp.activity.carstatus;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.BaseActivity;
import com.trust.ebikeapp.tool.TimeTool;
import com.trust.ebikeapp.tool.bean.CarStatusBean;

import java.util.Map;
import java.util.WeakHashMap;

public class CarStatusActivity extends BaseActivity {
    private ImageView voltageImg , voltageStatusImg;
    private Context context = CarStatusActivity.this;
    private TextView electractNum;
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
        electractNum = (TextView) findViewById(R.id.carstatus_electract_num);
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
        //电池是否拔出
        if(bean.getContent().getPlugOutAlarm().equals("0")){//正常
            showImg(11);
        }else{
            showImg(12);
        }


    }

    private void voltage(CarStatusBean bean) {
        int num = bean.getContent().getVoltagePercent();
        electractNum.setText(num+"");
        if(num == 100){
            showImg(10);
        }else if(num >= 90){
            showImg(9);
        }else if(num >= 80){
            showImg(8);
        }else if(num >= 70){
            showImg(7);
        }else if(num >= 60){
            showImg(6);
        }else if(num >= 50){
            showImg(5);
        }else if(num >= 40){
            showImg(4);
        }else if(num >= 30){
            showImg(3);
        }else if(num >= 20){
            showImg(2);
        }else if(num >= 10){
            showImg(1);
        }else{
            showImg(0);
        }
    }

    public void showImg(int num){
        Glide.with(context).load(Id[num]);
    }

    public void reload(View v){
        initDate();
    }
}
