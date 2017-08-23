package com.phonegap.ebike.activity.loginorregister;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.phonegap.ebike.Config;
import com.phonegap.ebike.activity.BaseActivity;
import com.phonegap.ebike.R;
import com.phonegap.ebike.tool.CheckNumTool;
import com.phonegap.ebike.tool.TextUtlis;
import com.phonegap.ebike.tool.utils.MD5Utils;

import java.util.Map;
import java.util.WeakHashMap;

public class RegisterActivity extends BaseActivity {

    private Context context = RegisterActivity.this;
    private Button submit,checkNumBtn;
    private ImageView finshBtn;
    private EditText userEd,pwdEd,emailEd,secretOrder,checkNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        submit = (Button) findViewById(R.id.register_sumbit);
        onClick(submit);
        finshBtn = (ImageView) findViewById(R.id.register_finsh);
        onClickFinsh(finshBtn,this);

        userEd = (EditText) findViewById(R.id.register_user);
        pwdEd = (EditText) findViewById(R.id.register_pwd);
        emailEd = (EditText) findViewById(R.id.register_email);
        checkNum = (EditText) findViewById(R.id.register_checknum);
        secretOrder = (EditText) findViewById(R.id.register_secret_order);
        checkNumBtn = (Button) findViewById(R.id.register_checknum_btn);

        onClick(checkNumBtn);
    }

    @Override
    public void clickResult(View v) {
        Map<String,Object> map = new WeakHashMap<String,Object>();
        String users = userEd.getText().toString().trim();
        String check = checkNum.getText().toString().trim();
        long user = 0 ,checkNums = 0;
        if(users != null && !users.equals("")){

            user = Long.parseLong(users);
        }
        if(!check.equals("")){
            checkNums = Long.parseLong(check);
        }
        switch (v.getId()){
            case R.id.register_sumbit:

                String pwd = MD5Utils.encrypt(pwdEd.getText().toString().trim());
                String email = emailEd.getText().toString().trim();
//                String secret = secretOrder.getText().toString().trim();

                int type = 0;

                if(user == 0 || pwd.equals("")){
                    showErrorToast(context,"账号或密码有误!",1);
                    return;
                }
                if(check.equals("") ){
                    showErrorToast(context,"验证码有误!",1);
                    return;
                }else{

                    if(checkNums == 0){
                        showErrorToast(context,"验证码有误!",1);
                    }else{
                        map.put("cp",user);
                        map.put("pw",pwd);
                        map.put("code",checkNums);
                        map.put("phoneType",3);
                        requestCallBeack(Config.Register,map,Config.register,Config.noAdd);
                    }



                }


                break;


            case R.id.register_checknum_btn:
                if(user == 0){
                    showErrorToast(context, TextUtlis.getMsg(R.string.errorPhone),3);
                }else {
                    map.put("cp", user);
                    requestCallBeack(Config.get_check_num, map, Config.getCheckNum, Config.noAdd);
                }
                break;
        }
    }



    @Override
    public void successCallBeack(Object obj, int type) {
        if(type == Config.register){
                showErrorToast(context,"注册成功!",1);
                killAllActivtiy(context);
        }else if(type == Config.getCheckNum){
                doTime(checkNumBtn);//倒计时
//                checkNum.setText(obj.toString());
        }
    }
}
