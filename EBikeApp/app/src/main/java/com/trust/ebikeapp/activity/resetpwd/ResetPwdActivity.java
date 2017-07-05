package com.trust.ebikeapp.activity.resetpwd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.BaseActivity;
import com.trust.ebikeapp.activity.customerservice.CustomerServiceActivity;
import com.trust.ebikeapp.tool.CheckNumTool;
import com.trust.ebikeapp.tool.TextUtlis;
import com.trust.ebikeapp.tool.utils.MD5Utils;

import java.util.Map;
import java.util.WeakHashMap;

public class ResetPwdActivity extends BaseActivity {
    private ImageButton backBtn;
    private EditText phoneEd,checkNumEd,newPwdEd,newTwoEd;
    private TextView getCheckNumBtn;
    private Button neverCheckNumBtn,cancelBtn,determineBtn;
    private int checkNum;
    private Context context = ResetPwdActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);
        initView();
    }

    private void initView() {
        phoneEd = (EditText) findViewById(R.id.activity_reset_pwd_phone);
        checkNumEd = (EditText) findViewById(R.id.activity_reset_pwd_check_num);
        newPwdEd = (EditText) findViewById(R.id.activity_reset_pwd_new_pwd);
        newTwoEd = (EditText) findViewById(R.id.activity_reset_pwd_new_two_pwd);

        getCheckNumBtn = (TextView) findViewById(R.id.activity_reset_pwd_get_check_num);
        neverCheckNumBtn = (Button) findViewById(R.id.activity_never);
        cancelBtn = (Button) findViewById(R.id.activity_reset_pwd_cancel);
        determineBtn = (Button) findViewById(R.id.activity_reset_pwd_determine);
        backBtn = (ImageButton) findViewById(R.id.activity_reset_pwd_back);
        onClick(getCheckNumBtn);
        onClick(neverCheckNumBtn);
        onClick(cancelBtn);
        onClick(determineBtn);
        onClick(backBtn);
    }


    @Override
    public void clickResult(View v) {
        switch (v.getId()){
            case R.id.activity_reset_pwd_get_check_num:
                String phone = checkMessage(phoneEd, TextUtlis.getMsg(R.string.errorPhone));
                if(phone != null){
                    requestCheckNum(Long.parseLong(phone));
                }
                break;
            case R.id.activity_never:
                startActivity(new Intent(this, CustomerServiceActivity.class));
                break;
            case R.id.activity_reset_pwd_cancel:
                finish();
                break;
            case R.id.activity_reset_pwd_determine:
                doDate();
                break;
            case R.id.activity_reset_pwd_back:
                finish();
                break;
        }
    }



    @Override
    public void successCallBeack(Object obj, int type) {

        switch (type){
            case Config.getCheckNum:
                    checkNum = Integer.parseInt(obj.toString());
                    checkNumEd.setText(obj.toString());
                new CheckNumTool<>().startTime(getCheckNumBtn);
                break;
            case Config.resetPwd:
                showWaitToast(context,"重置密码成功!",1);
                finish();
                break;
        }
    }

    private void doDate() {
        String phone = checkMessage(phoneEd,TextUtlis.getMsg(R.string.errorPhone));
        String checkNumt = checkMessage(checkNumEd,"验证码不能为空!");
        String pwd = checkMessage(newPwdEd,"密码不能为空!");
        String twoPwd = checkMessage(newTwoEd,"确认密码不能为空!");
        if(phone != null){
            Config.phone = Long.parseLong(phone);
            if(checkNumt != null){
                checkNum =  Integer.parseInt(checkNumt);
                if(pwd != null){
                    if(twoPwd!=null){
                        if(pwd .equals(twoPwd)){
                            String newPwd = MD5Utils.encrypt(twoPwd);
                            requestChangPwd(newPwd);
                        }else{
                            showErrorToast(context,"两次密码不一致!",1);
                        }
                    }
                }
            }
        }


    }

    private void requestChangPwd(String newPwd) {
        Map<String,Object> map = new WeakHashMap<>();
        map.put("cp",Config.phone);
        map.put("code",checkNum);
        map.put("pwd",newPwd);

        requestCallBeack(Config.reset_pwd,map,Config.resetPwd,Config.noAdd);
    }


}
