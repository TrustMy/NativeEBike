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
import com.trust.ebikeapp.tool.T;
import com.trust.ebikeapp.tool.Utils.MD5Utils;
import com.trust.ebikeapp.tool.dialog.DialogTool;

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
                String secret = secretOrder.getText().toString().trim();

                int type = 0;

                if(user == 0 || pwd.equals("")){
                    T.showToast(context,"账号或密码有误!");
                    return;
                }
                if(check.equals("") || secret.equals("")){
                    T.showToast(context,"密令或验证码有误!");
                    return;
                }else{

                    if(checkNums == 0){
                        T.showToast(this,"验证码有误!");
                    }else{
                        showDialog();

                        map.put("cp",user);
                        map.put("pw",pwd);
                        map.put("code",checkNums);
                        map.put("phoneType",3);
                        post.Request(Config.Register,map,Config.register,Config.noAdd);
                    }



                }


                break;


            case R.id.register_checknum_btn:
                if(user == 0){
                    T.showToast(this,"手机号不能为空!");
                }else {
                    showDialog();

                    map.put("cp", user);
                    post.Request(Config.get_check_num, map, Config.getCheckNum, Config.noAdd);
                }
                break;
        }
    }

    @Override
    public void resultCallBeack(Object obj, int type, int status) {
        dissDialog();
        if(type == Config.register){
            if(status == Config.SUCCESS){
                T.showToast(this,"注册成功!");
            }else{
                T.showToast(this,"注册失败!"+obj.toString());
            }
        }else if(type == Config.getCheckNum){
            if(status == Config.SUCCESS){
                T.showToast(this,"获取成功!");
                checkNum.setText(obj.toString());
            }else{
                T.showToast(this,"获取失败!"+obj.toString());
            }
        }
    }


}
