package com.trust.ebikeapp.activity.loginorregister;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.trust.ebikeapp.activity.BaseActivity;
import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.activity.MainActivity;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.tool.T;
import com.trust.ebikeapp.tool.Utils.MD5Utils;

import java.util.Map;
import java.util.WeakHashMap;


public class LoginActivity extends BaseActivity {
    private Context context = LoginActivity.this;
    private Button login;
    private EditText userEd,pwdEd;
    private LinearLayout registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lgin);

        initView();



    }


    private void initView() {
        login = (Button) findViewById(R.id.login_login);
        userEd = (EditText) findViewById(R.id.login_user);
        pwdEd = (EditText) findViewById(R.id.login_pwd);
        registerBtn = (LinearLayout) findViewById(R.id.login_register);
        onClick(registerBtn);
        onClick(login);
    }

    @Override
    public void clickResult(View v) {
        switch (v.getId()){
            case R.id.login_login:
                long user = Long.parseLong(userEd.getText().toString().trim());
                String pwd = MD5Utils.encrypt(pwdEd.getText().toString().trim());
                if (user == 0 || pwd.equals("")) {
                    T.showToast(context, "密码或用户名有误!");
                } else {
                    Map<String, Object> map = new WeakHashMap<>();
                    map.put("cp", user);
                    map.put("pw", pwd);
                    post.Request(Config.Login,map,Config.login,false);
                }
                break;
            case R.id.login_register:
                startActivity(new Intent(context,RegisterActivity.class));
                break;
        }
    }

    @Override
    public void Result(Object obj, int type) {
        if(type == Config.login){
            startActivity(new Intent(context,MainActivity.class));
        }
    }
}
