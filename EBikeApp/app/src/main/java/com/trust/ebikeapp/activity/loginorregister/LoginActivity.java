package com.trust.ebikeapp.activity.loginorregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trust.ebikeapp.activity.BaseActivity;
import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.activity.MainActivity;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.bind.CarBindActivity;
import com.trust.ebikeapp.activity.changpwd.ChangPwdActivity;
import com.trust.ebikeapp.tool.L;
import com.trust.ebikeapp.tool.T;
import com.trust.ebikeapp.tool.Utils.MD5Utils;
import com.trust.ebikeapp.tool.dialog.DialogTool;

import java.util.Map;
import java.util.WeakHashMap;


public class LoginActivity extends BaseActivity {
    private Context context = LoginActivity.this;
    private Button login;
    private EditText userEd,pwdEd;
    private LinearLayout registerBtn;
    private CheckBox checkBox;
    private TextView changPwdTv;

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
            String pwd = editor.getString("pwd",null);
            userEd.setText(phone+"");
            pwdEd.setText(pwd);
        }


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
    }

    @Override
    public void clickResult(View v) {
        switch (v.getId()){
            case R.id.login_login:
                String users = userEd.getText().toString().trim();
                if(users.equals("")){
                    T.showToast(context, "用户名不能为空!");
                    return ;
                }
                long user = Long.parseLong(users);
                String pwd = MD5Utils.encrypt(pwdEd.getText().toString().trim());
                if (user == 0 || pwd.equals("")) {
                    T.showToast(context, "密码或用户名有误!");
                } else {
                    showWaitToast(context,"正在登陆,请稍后...",2);
                    DialogTool.waitDialog(this);

                    Config.phone = user;
                    Config.pwd = pwdEd.getText().toString().trim();
                    Map<String, Object> map = new WeakHashMap<>();
                    map.put("cp", user);
                    map.put("pw", pwd);
                    post.Request(Config.Login,map,Config.login,Config.noAdd);
                }


                break;
            case R.id.login_register:
                startActivity(new Intent(context,RegisterActivity.class));
                break;

            case R.id.login_chang_pwd:
                startActivity(new Intent(context,ChangPwdActivity.class));

                break;
        }
    }


    @Override
    public void successCallBeack(Object obj, int type) {
        if(type == Config.login){
            long termId = (long) obj;
            if(termId == 0){
                startActivity(new Intent(context,CarBindActivity.class));
            }else{
                startActivity(new Intent(context,MainActivity.class));
            }
        }
    }


}
