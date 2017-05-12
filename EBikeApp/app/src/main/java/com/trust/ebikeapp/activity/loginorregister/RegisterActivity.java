package com.trust.ebikeapp.activity.loginorregister;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.trust.ebikeapp.activity.BaseActivity;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.tool.T;
import com.trust.ebikeapp.tool.Utils.MD5Utils;

public class RegisterActivity extends BaseActivity {

    private Context context = RegisterActivity.this;
    private Button submit;
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
    }

    @Override
    public void clickResult(View v) {
        switch (v.getId()){
            case R.id.register_sumbit:
                long user = Long.parseLong(userEd.getText().toString().trim());
                String pwd = MD5Utils.encrypt(pwdEd.getText().toString().trim());
                String email = emailEd.getText().toString().trim();
                String secret = secretOrder.getText().toString().trim();
                String check = checkNum.getText().toString().trim();
                int type = 0;

                if(user == 0 || pwd.equals("")){
                    T.showToast(context,"账号或密码有误!");
                }
                if(check.equals("") || secret.equals("")){
                    T.showToast(context,"密令或验证码有误!");
                }else{

                }
                break;
        }
    }



}
