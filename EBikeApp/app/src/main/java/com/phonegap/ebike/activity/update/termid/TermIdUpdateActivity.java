package com.phonegap.ebike.activity.update.termid;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.phonegap.ebike.Config;
import com.phonegap.ebike.R;
import com.phonegap.ebike.activity.BaseActivity;
import com.phonegap.ebike.tool.TextUtlis;

import java.util.Map;
import java.util.WeakHashMap;

public class TermIdUpdateActivity extends BaseActivity{
    private EditText phoneEd,checkNumEd;
    private Button cancelBtn,determine;
    private TextView getCheckNumBtn;
    private ImageButton backBtn;
    private Context context = TermIdUpdateActivity.this;
    private int checkNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_id_update);
        initView();
    }

    private void initView() {
        phoneEd = (EditText) findViewById(R.id.activity_termid_phone);
        checkNumEd = (EditText) findViewById(R.id.activity_termid_check_num);

        cancelBtn = (Button) findViewById(R.id.activity_termid_cancel);
        determine = (Button) findViewById(R.id.activity_termid_determine);
        getCheckNumBtn = (TextView) findViewById(R.id.activity_termid_get_check_num);
        backBtn = (ImageButton) findViewById(R.id.activity_termid_back);

        onClick(cancelBtn);
        onClick(determine);
        onClick(getCheckNumBtn);
        onClick(backBtn);


    }


    @Override
    public void clickResult(View v) {
        switch (v.getId()){
            case R.id.activity_termid_cancel:
                finish();
                break;
            case R.id.activity_termid_determine:
                String phone = checkMessage(phoneEd, TextUtlis.getMsg(R.string.errorPhone));
                if(phone!= null){
                    String checkNum = checkMessage(checkNumEd,TextUtlis.getMsg(R.string.errorCheckNum));
                    if (checkNum != null) {
                        unBindCar(Long.parseLong(phone),Integer.parseInt(checkNum));
                    }
                }
                break;
            case R.id.activity_termid_get_check_num:
                String phones = checkMessage(phoneEd,TextUtlis.getMsg(R.string.errorPhone));
                if(phones != null){
                    requestCheckNum(Long.parseLong(phones));
                }
                break;
            case R.id.activity_termid_back:
                finsh(this);
                break;
        }
    }

    private void unBindCar(long phone , long checkNum) {
        Map<String,Object> map = new WeakHashMap<>();
        map.put("cp",phone);
        map.put("termId",Config.termId);
        map.put("code",checkNum);
        requestCallBeack(Config.un_bind_car, map, Config.unBindCar, Config.needAdd);
    }


    @Override
    public void successCallBeack(Object obj, int type) {
        switch (type){
            case Config.getCheckNum:
                doTime(getCheckNumBtn);
                break;
            case Config.unBindCar:
                showWaitToast(context,"解绑成功",3);
                killAllActivtiy(context);
                break;
        }
    }


}
