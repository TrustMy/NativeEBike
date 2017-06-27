package com.trust.ebikeapp.activity.update.termid;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.BaseActivity;
import com.trust.ebikeapp.activity.loginorregister.LoginActivity;
import com.trust.ebikeapp.activity.loginorregister.RegisterActivity;

import java.util.Map;
import java.util.WeakHashMap;

public class TermIdUpdateActivity extends BaseActivity{
    private EditText phoneEd,checkNumEd;
    private Button cancelBtn,determine;
    private TextView getCheckNumBtn;
    private ImageButton backBtn;
    private Context context = TermIdUpdateActivity.this;
    private int checkNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_id_update);
        initView();
    }

    private void initView() {
        phoneEd = (EditText) findViewById(R.id.activity_termid_phone);
        checkNumEd = (EditText) findViewById(R.id.activity_termid_check_num);

        cancelBtn = (Button) findViewById(R.id.activity_termid_cancel);
        determine = (Button) findViewById(R.id.activity_termid_determine);
        getCheckNumBtn = (TextView) findViewById(R.id.activity_termid_get_check_num);
        backBtn = (ImageButton) findViewById(R.id.activity_termid_back);

        onClick(cancelBtn);
        onClick(determine);
        onClick(getCheckNumBtn);
        onClick(backBtn);


    }


    @Override
    public void clickResult(View v) {
        switch (v.getId()){
            case R.id.activity_termid_cancel:
                finish();
                break;
            case R.id.activity_termid_determine:
                unBindCar();
                break;
            case R.id.activity_termid_get_check_num:
                requestCheckNum();
                break;
            case R.id.activity_termid_back:
                finsh(this);
                break;
        }
    }

    private void unBindCar() {
        Map<String,Object> map = new WeakHashMap<>();
        map.put("cp",Config.phone);
        map.put("termId",Config.termId);
        map.put("code",checkNum);
        requestCallBeack(Config.un_bind_car, map, Config.unBindCar, Config.needAdd);
    }


    private void requestCheckNum() {
        Map<String,Object> map =  new WeakHashMap<>();
        map.put("cp", Config.phone);
        requestCallBeack(Config.get_check_num, map, Config.getCheckNum, Config.noAdd);
    }


    @Override
    public void successCallBeack(Object obj, int type) {
        switch (type){
            case Config.getCheckNum:
                checkNum = 888888;
                checkNumEd.setText(checkNum+"");
                break;
            case Config.unBindCar:
                showWaitToast(context,"解绑成功",3);
                startActivity(new Intent(context, LoginActivity.class));
                finish();
                break;
        }
    }


}
