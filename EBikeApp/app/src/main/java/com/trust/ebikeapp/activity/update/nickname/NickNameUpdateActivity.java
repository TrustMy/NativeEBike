package com.trust.ebikeapp.activity.update.nickname;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.BaseActivity;

import java.util.Map;
import java.util.WeakHashMap;

public class NickNameUpdateActivity extends BaseActivity {
    private Context context = NickNameUpdateActivity.this;
    private Button cancelBtn,determine;
    private EditText nickNameEd;
    private ImageButton backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nick_name_update);
        iniView();
    }

    private void iniView() {
        nickNameEd = (EditText) findViewById(R.id.activity_nick_name_ed);
        backBtn = (ImageButton) findViewById(R.id.activity_nick_name_back);
        cancelBtn = (Button) findViewById(R.id.activity_nick_name_back_cancel);
        determine = (Button) findViewById(R.id.activity_nick_name_back_determine);

        onClick(backBtn);
        onClick(cancelBtn);
        onClick(determine);
    }

    @Override
    public void clickResult(View v) {
        switch (v.getId()){
            case R.id.activity_nick_name_back:
            case R.id.activity_nick_name_back_cancel:
                finish();
                break;
            case R.id.activity_nick_name_back_determine:
                request();
                break;
        }
    }

    private void request() {
        String name = nickNameEd.getText().toString().trim();
        if(!name .equals("")){
            Map<String,Object> map = new WeakHashMap<>();
            map.put("cp", Config.phone);
            map.put("nickName", name);
            requestCallBeack(Config.nick_name_update,map,Config.nickNameUpdate,Config.needAdd);
        }


    }

    @Override
    public void successCallBeack(Object obj, int type) {
        switch (type){
            case Config.nickNameUpdate:
                showWaitToast(context,"设置成功",3);
                break;
        }
    }
}
