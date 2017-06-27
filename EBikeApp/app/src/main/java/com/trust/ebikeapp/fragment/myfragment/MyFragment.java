package com.trust.ebikeapp.fragment.myfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.activity.changpwd.ChangPwdActivity;
import com.trust.ebikeapp.activity.update.nickname.NickNameUpdateActivity;
import com.trust.ebikeapp.activity.update.phone.PhoneUpdateActivity;
import com.trust.ebikeapp.activity.update.speed.SpeedUpdateActivity;
import com.trust.ebikeapp.activity.update.termid.TermIdUpdateActivity;
import com.trust.ebikeapp.fragment.BaseFragment;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.tool.TimeTool;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by Trust on 2017/5/12.
 */
public class MyFragment extends BaseFragment {
    private View v;
    private Context context;
    private TextView  phoneTv,termIdTv,speedTv,nickNameTv,emailTv,userNameTv;
    private ImageButton lightBtn,oilAndElectricityBtn;
    private RelativeLayout phoneUpdateBtn,termIdUpdateBtn,speedUpdateBtn,nickNameUpdateBtn,emailUpdateBtn;
    @Override
    public void onAttach(Context context) {
        this.context =context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = LayoutInflater.from(context).inflate(R.layout.fragment_my,null);
        initView();
        update();
        return v;
    }

    private void initView() {
        phoneTv = (TextView) v.findViewById(R.id.myfragment_phone);
        termIdTv = (TextView) v.findViewById(R.id.myfragment_termid);
        speedTv = (TextView) v.findViewById(R.id.myfragment_speed);
        nickNameTv = (TextView) v.findViewById(R.id.myfragment_nickname);
        emailTv = (TextView) v.findViewById(R.id.myfragment_email);
        userNameTv = (TextView) v.findViewById(R.id.myfragment_user_name);
        lightBtn = (ImageButton) v.findViewById(R.id.myfragment_car_light);
        oilAndElectricityBtn = (ImageButton) v.findViewById(R.id.myfragment_off_the_oil_or_electricity);

        onClick(lightBtn);
        onClick(oilAndElectricityBtn);
        phoneUpdateBtn = (RelativeLayout) v.findViewById(R.id.myfragment_phone_update);
        termIdUpdateBtn = (RelativeLayout) v.findViewById(R.id.myfragment_termid_update);
        speedUpdateBtn = (RelativeLayout) v.findViewById(R.id.myfragment_speed_update);
        nickNameUpdateBtn = (RelativeLayout) v.findViewById(R.id.myfragment_nickname_update);
        emailUpdateBtn = (RelativeLayout) v.findViewById(R.id.myfragment_email_update);

        onClick(phoneUpdateBtn);
        onClick(termIdUpdateBtn);
        onClick(speedUpdateBtn);
        onClick(nickNameUpdateBtn);
        onClick(emailUpdateBtn);
    }

    private void update() {
        phoneTv.setText(Config.phone+"");
        termIdTv.setText(Config.termId+"");
        speedTv.setText(Config.speed);
        nickNameTv.setText(Config.nickname);
        userNameTv.setText(Config.nickname);
        emailTv.setText(Config.emaill);
    }


    @Override
    public void clickResult(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.myfragment_phone_update:
                intent.setClass(context, PhoneUpdateActivity.class);
                toIntent(intent);
                break;
            case R.id.myfragment_termid_update:
                intent.setClass(context, TermIdUpdateActivity.class);
                toIntent(intent);
                break;
            case R.id.myfragment_speed_update:
                intent.setClass(context, SpeedUpdateActivity.class);
                toIntent(intent);
                break;
            case R.id.myfragment_pwd_update:
                intent.setClass(context, ChangPwdActivity.class);
                toIntent(intent);
                break;
            case R.id.myfragment_nickname_update:
                intent.setClass(context, NickNameUpdateActivity.class);
                toIntent(intent);
                break;
            /*
            case R.id.myfragment_email_update:
//                intent.setClass(context,EmailUpdateActivity.classs);
                break;
                */
            case R.id.myfragment_car_light:
                requestCarLightAndOilOrElectricity(Config.car_light,
                        Config.carLight,0);
                break;

            case R.id.myfragment_off_the_oil_or_electricity:
                requestCarLightAndOilOrElectricity(Config.off_the_oil_or_electricity,
                        Config.offTheOilOrElectricity
                        ,0);
                break;


        }

    }

    private void requestCarLightAndOilOrElectricity(String url,int type,int status) {
        Map<String ,Object> map = new WeakHashMap<>();
        map.put("termId",Config.termId);
        map.put("userCellPhone",Config.phone);
        map.put("appSN", TimeTool.getSystemTimeDate()/1000);
        map.put("mod",status);
        requestCallBeack(url,map,type,Config.needAdd);
    }


    private void toIntent(Intent intent) {
        context.startActivity(intent);
    }

    @Override
    public void successCallBeack(Object obj, int type) {
        switch (type){
            case Config.offTheOilOrElectricity:
                showWaitToast(context,"断XX成功",1);
                break;
            case Config.carLight:
                showWaitToast(context,"开关灯",1);
                break;
        }
    }
}
