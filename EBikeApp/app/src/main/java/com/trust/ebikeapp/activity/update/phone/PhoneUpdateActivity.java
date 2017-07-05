package com.trust.ebikeapp.activity.update.phone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.BaseActivity;

import java.util.Map;
import java.util.WeakHashMap;

public class PhoneUpdateActivity extends BaseActivity {
    private ImageButton backBtn;
    private EditText pwdEd,newPwdEd,chackNumEd;
    private TextView getCheckNumBtn;
    private Button cancelBtn,determine;
    private String pwd,newPwd,checkNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_update);
        initView();
    }

    private void initView() {
        backBtn = (ImageButton) findViewById(R.id.activity_phone_back);
        getCheckNumBtn = (TextView) findViewById(R.id.activity_phone_phone_get_check_num);
        cancelBtn = (Button) findViewById(R.id.activity_phone_cancel);
        determine = (Button) findViewById(R.id.activity_phone_determine);

        onClick(backBtn);
        onClick(getCheckNumBtn);
        onClick(cancelBtn);
        onClick(determine);


        pwdEd = (EditText) findViewById(R.id.activity_phone_pwd_ed);
        newPwdEd = (EditText) findViewById(R.id.activity_phone_new_pwd_ed);
        chackNumEd = (EditText) findViewById(R.id.activity_phone_check_num_ed);
    }

    @Override
    public void clickResult(View v) {
         pwd = pwdEd.getText().toString().trim();
         newPwd = newPwdEd.getText().toString().trim();
         checkNum = chackNumEd.getText().toString().trim();
        switch (v.getId()){
            case R.id.activity_phone_back:
                finsh(this);
                break;
            case R.id.activity_phone_phone_get_check_num:
                requestCheckNum(Config.phone);
                break;
            case R.id.activity_phone_cancel:
                finish();
                break;
            case R.id.activity_phone_determine:
                request();
                break;
        }
    }



    private void request() {

        if(pwd.equals("")){

        }
    }

    @Override
    public void successCallBeack(Object obj, int type) {
        switch (type){
            case Config.getCheckNum:
                chackNumEd.setText(888888+"");
                break;

        }
    }
}
