package com.trust.ebikeapp.activity.bind;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.BaseActivity;
import com.trust.ebikeapp.activity.MainActivity;
import com.trust.ebikeapp.tool.L;
import com.trust.ebikeapp.tool.T;
import com.zxing.activity.CaptureActivity;

import java.util.Map;
import java.util.WeakHashMap;

public class CarBindActivity extends BaseActivity {
    private EditText phoneEd,deviceEd,checkNumEd;
    private ImageView qrBtn;
    private Button cancelBtn,determineBtn;
    private TextView getCheckNumBtn;
    private Context context = CarBindActivity.this;
    private final int Scann = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_bind);

        iniView();

        if(Config.phone != 0){
            phoneEd.setText(Config.phone+"");
        }
    }

    private void iniView() {
        phoneEd = (EditText) findViewById(R.id.activity_car_bind_phone);
        deviceEd = (EditText) findViewById(R.id.activity_car_bind_device_num);
        checkNumEd = (EditText) findViewById(R.id.activity_car_bind_check_num);


        qrBtn = (ImageView) findViewById(R.id.activity_car_bind_qr_code);
        getCheckNumBtn = (TextView) findViewById(R.id.activity_car_bind_get_check_num);
        cancelBtn = (Button) findViewById(R.id.activity_car_bind_cancel);
        determineBtn = (Button) findViewById(R.id.activity_car_bind_determine);

        onClick(qrBtn);
        onClick(getCheckNumBtn);
        onClick(cancelBtn);
        onClick(determineBtn);

    }



    @Override
    public void clickResult(View v) {
        String user = phoneEd.getText().toString().trim();
        String device = deviceEd.getText().toString().trim();
        String check = checkNumEd.getText().toString().trim();
        checkNumEd = (EditText) findViewById(R.id.activity_car_bind_check_num);
        long phone = 0,termId = 0 , checkNum = 0;
        if(!user.equals("")){
            phone  = Long.parseLong(user);
        }
        if(!device.equals("")){
            termId = Long.parseLong(device);
        }
        if(!check.equals("")){
            checkNum = Long.parseLong(check);
        }
        Map<String,Object> map = new WeakHashMap<String,Object>();
        switch (v.getId()){
            case R.id.activity_car_bind_qr_code:
                startActivityForResult(new Intent(context, CaptureActivity.class),Scann);
                break;
            case R.id.activity_car_bind_get_check_num:
                if (phone == 0) {
                    showErrorToast(context,"手机号码有误!",3);
                    return;
                }else{
                    map.put("cp", phone);
                    requestCallBeack(Config.get_check_num, map, Config.getCheckNum, Config.noAdd);
                }
                break;
            case R.id.activity_car_bind_cancel:
                finish();
                break;
            case R.id.activity_car_bind_determine:
                if(phone == 0 ||termId == 0 || checkNum == 0){
                    showErrorToast(context,"输入信息有误!",3);
                    return;
                }else{
                    map.put("cp",phone);
                    map.put("termId",termId);
                    map.put("code",checkNum);
                    requestCallBeack(Config.bind_car,map,Config.bindCar,Config.needAdd);
                }

                break;

        }
    }


    @Override
    public void resultCallBeack(Object obj, int type, int status) {
        if(status == Config.SUCCESS){
            if(type == Config.getCheckNum){

                checkNumEd.setText(obj.toString());
            }else if(type == Config.bindCar){
                startActivity(new Intent(this, MainActivity.class));
            }
        }else{
            L.e("error："+obj.toString());
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case Scann:
                    String resultq = data.getExtras().getString("result");
                    deviceEd.setText(resultq);
                    break;
            }
        }
    }


}
