package com.phonegap.ebike.activity.loginorregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.phonegap.ebike.activity.BaseActivity;
import com.phonegap.ebike.Config;
import com.phonegap.ebike.activity.MainActivity;
import com.phonegap.ebike.R;
import com.phonegap.ebike.activity.bind.CarBindActivity;
import com.phonegap.ebike.activity.resetpwd.ResetPwdActivity;
import com.phonegap.ebike.tool.L;
import com.phonegap.ebike.tool.bean.LoginResultBean;
import com.phonegap.ebike.tool.push.PushId;
import com.phonegap.ebike.tool.updateapp.APPVersion;
import com.phonegap.ebike.tool.updateapp.CheckVersionTask;
import com.phonegap.ebike.tool.utils.MD5Utils;

import java.io.InputStream;
import java.util.Map;
import java.util.WeakHashMap;


public class LoginActivity extends BaseActivity {
    private Context context = LoginActivity.this;
    private Button login;
    private EditText userEd,pwdEd;
    private LinearLayout registerBtn;
    private CheckBox checkBox;
    private TextView changPwdTv;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lgin);

        initView();

        init();


    }
    public void init(){
        SharedPreferences editor = context.getSharedPreferences("UserMsg",
                Context.MODE_PRIVATE);
        long phone = editor.getLong("phone",0);
        if(phone != 0){
            checkBox.setChecked(true);
            Config.pwd   = editor.getString("pwd",null);
            userEd.setText(phone+"");
            pwdEd.setText(Config.pwd );
        }
        requestGetCallBeack(Config.update_app,Config.updateApp);

    }


    private void initView() {
        login = (Button) findViewById(R.id.login_login);
        userEd = (EditText) findViewById(R.id.login_user);
        pwdEd = (EditText) findViewById(R.id.login_pwd);
        registerBtn = (LinearLayout) findViewById(R.id.login_register);
        changPwdTv = (TextView) findViewById(R.id.login_chang_pwd);
        onClick(registerBtn);
        onClick(login);
        onClick(changPwdTv);
        checkBox = (CheckBox) findViewById(R.id.login_check_box);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Config.checkBox = b;
            }
        });
        logo = (ImageView) findViewById(R.id.logo);
    }

    @Override
    public void clickResult(View v) {
        switch (v.getId()){
            case R.id.login_login:
                String users = userEd.getText().toString().trim();
                if(users.equals("")){
                    showErrorToast(context, "用户名不能为空!",3);
                    return ;
                }
                long user = Long.parseLong(users);
                String pwd = MD5Utils.encrypt(pwdEd.getText().toString().trim());
                if (user == 0 || pwd.equals("")) {
                    showErrorToast(context, "密码或用户名有误!",3);
                } else {
                    showWaitToast(context,"正在登陆,请稍后...",2);
                    Config.phone = user;
                    Config.pwd = pwdEd.getText().toString().trim();
                    Map<String, Object> map = new WeakHashMap<>();
                    map.put("cp", user);
                    map.put("pw", pwd);
                    requestCallBeack(Config.Login,map,Config.login,Config.noAdd);
                }


                break;
            case R.id.login_register:
                startActivity(new Intent(context,RegisterActivity.class));
                break;

            case R.id.login_chang_pwd:
                startActivity(new Intent(context,ResetPwdActivity.class));

                break;
        }
    }


    @Override
    public void successCallBeack(Object obj, int type) {
        if(type == Config.login){
            LoginResultBean bean = (LoginResultBean) obj;
            long termId = bean.getContent().getTermId();
            if(termId == 0){
                startActivity(new Intent(context,CarBindActivity.class));
            }else{
                startActivity(new Intent(context,MainActivity.class));
            }
            if (!bean.getContent().getPushId().equals(PushId.ID)) {
                Map<String,Object> map = new WeakHashMap<>();
                map.put("cp",Config.phone);
                map.put("pushId",PushId.ID);
                map.put("phoneType",3);//标示android
                requestCallBeack(Config.push_url,map,Config.pushId,Config.needAdd);
                L.e("push id bind connection");
            }else{
                L.d("push id same");
            }
        }else if(type == Config.updateApp){

            InputStream updateMsg = (InputStream) obj;

            try {
                 Config.version = APPVersion.getVersion(context);
                Thread thread = new Thread(new CheckVersionTask(context,APPVersion.getVersion(context),
                        updateMsg ));
                thread.start();
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

        }
    }


}
