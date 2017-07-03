package com.trust.ebikeapp.activity.loginorregister;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.activity.BaseActivity;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.tool.CheckNumTool;
import com.trust.ebikeapp.tool.L;
import com.trust.ebikeapp.tool.T;
import com.trust.ebikeapp.tool.utils.MD5Utils;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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
                String secret = secretOrder.getText().toString().trim();

                int type = 0;

                if(user == 0 || pwd.equals("")){
                    showErrorToast(context,"账号或密码有误!",1);
                    return;
                }
                if(check.equals("") || secret.equals("")){
                    showErrorToast(context,"密令或验证码有误!",1);
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
                    T.showToast(this,"手机号不能为空!");
                }else {
                    map.put("cp", user);
                    requestCallBeack(Config.get_check_num, map, Config.getCheckNum, Config.noAdd);
                }
                break;
        }
    }

    private void doTime( final Button v) {

        new CheckNumTool<>().startTime(v);
    }

    @Override
    public void successCallBeack(Object obj, int type) {
        if(type == Config.register){
                showErrorToast(context,"注册成功!",1);
        }else if(type == Config.getCheckNum){
                doTime(checkNumBtn);//倒计时
                checkNum.setText(obj.toString());
        }
    }
}
