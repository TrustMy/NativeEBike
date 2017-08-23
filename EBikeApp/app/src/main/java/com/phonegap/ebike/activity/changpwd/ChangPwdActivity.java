package com.phonegap.ebike.activity.changpwd;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.phonegap.ebike.Config;
import com.phonegap.ebike.R;
import com.phonegap.ebike.activity.BaseActivity;
import com.phonegap.ebike.tool.utils.MD5Utils;

import java.util.Map;
import java.util.WeakHashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ChangPwdActivity extends BaseActivity {
    private Context context = ChangPwdActivity.this;
    ImageButton activityChangPwdBack;
    EditText activityChangPwdOldPwd;
    EditText activityChangPwdNewPwd;
    EditText activityChangPwdNewTwoPwd;
    Button activityResetChangCancel;
    Button activityChangPwdDetermine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chang_pwd);
        initView();
    }

    private void initView() {
        activityChangPwdBack = (ImageButton) findViewById(R.id.activity_chang_pwd_back);
        activityChangPwdOldPwd = (EditText) findViewById(R.id.activity_chang_pwd_old_pwd);
        activityChangPwdNewPwd = (EditText) findViewById(R.id.activity_chang_pwd_new_pwd);
        activityChangPwdNewTwoPwd = (EditText) findViewById(R.id.activity_chang_pwd_new_two_pwd);
        activityResetChangCancel = (Button) findViewById(R.id.activity_reset_chang_cancel);
        activityChangPwdDetermine = (Button) findViewById(R.id.activity_chang_pwd_determine);

        onClick(activityChangPwdBack);
        onClick(activityResetChangCancel);
        onClick(activityChangPwdDetermine);
    }

    @Override
    public void clickResult(View v) {
        switch (v.getId()){
            case R.id.activity_chang_pwd_back:
            case R.id.activity_reset_chang_cancel:
                finish();
                break;
            case R.id.activity_chang_pwd_determine:
                doDate();
                break;
        }
    }

    private void doDate() {
        String oldPwd = checkMessage(activityChangPwdOldPwd,"旧密码不能为空!");
        String newPwd = checkMessage(activityChangPwdNewPwd,"新密码不能空!");
        String newTwoPwd = checkMessage(activityChangPwdNewTwoPwd,"确认新密码不能空!");
        if(oldPwd != null){
            if(oldPwd.equals(Config.pwd)){
                if(newPwd!=null){
                    if(newPwd.equals(newTwoPwd)){
                        requestChangPwd(MD5Utils.encrypt(oldPwd),MD5Utils.encrypt(newTwoPwd));
                    }else{
                        showErrorToast(context,"两次密码输入不一致!",1);
                    }
                }
            }else{
                showErrorToast(context,"旧密码输入错误!",1);
            }
        }
    }

    private void requestChangPwd(String oldPwd, String newTwoPwd) {
        Map<String,Object> map = new WeakHashMap<>();
        map.put("cp",Config.phone);
        map.put("oldPwd",oldPwd);
        map.put("newPwd",newTwoPwd);

        requestCallBeack(Config.chang_pwd,map,Config.changPwd,Config.needAdd);
    }


    @Override
    public void successCallBeack(Object obj, int type) {
        switch (type){
            case Config.changPwd:
                showErrorToast(context,"修改成功!",1);
                killAllActivtiy(context);
                break;
        }
    }
}
