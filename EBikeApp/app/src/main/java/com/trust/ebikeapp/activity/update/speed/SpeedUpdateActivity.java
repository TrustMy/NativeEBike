package com.trust.ebikeapp.activity.update.speed;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.BaseActivity;
import com.trust.ebikeapp.tool.TimeTool;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by Trust on 2017/6/27.
 */

public class SpeedUpdateActivity extends BaseActivity  implements RadioGroup.OnCheckedChangeListener{
    private Button cancelBtn,determine;
    private RadioGroup radioGroup;
    private ImageButton backBtn;
    private int speed;
    private Context context = SpeedUpdateActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_update);
        initView();
    }

    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.activity_speed_update_radio_group);
        radioGroup.setOnCheckedChangeListener(this);
        cancelBtn = (Button) findViewById(R.id.activty_speed_update_cancel);
        determine = (Button) findViewById(R.id.activty_speed_update_determine);
        backBtn = (ImageButton) findViewById(R.id.activity_speed_update_back);
        onClick(cancelBtn);
        onClick(determine);
        onClick(backBtn);

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i){
            case R.id.activty_speed_update_radio1:
                speed = 30;
                break;

            case R.id.activty_speed_update_radio2:
                speed = 35;
                break;

            case R.id.activty_speed_update_radio3:
                speed = 40;
                break;
        }
    }


    @Override
    public void clickResult(View v) {
        switch (v.getId()){
            case R.id.activty_speed_update_determine:
                request();
                break;
            case R.id.activty_speed_update_cancel:
            case R.id.activity_speed_update_back:
                finish();
                break;
        }
    }

    private void request() {
        Map<String,Object> map = new WeakHashMap<>();
        map.put("termId", Config.termId);
        map.put("userCellPhone", Config.phone);
        map.put("appSN", TimeTool.getSystemTimeDate()/1000);
        map.put("speed", speed);

        requestCallBeack(Config.speed_limit,map,Config.speedLimit,Config.needAdd);
    }

    @Override
    public void successCallBeack(Object obj, int type) {
        switch (type){
            case Config.speedLimit:
                showWaitToast(context,"设置成功",1);
                break;
        }
    }
}
