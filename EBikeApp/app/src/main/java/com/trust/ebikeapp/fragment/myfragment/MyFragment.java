package com.trust.ebikeapp.fragment.myfragment;

import android.app.Activity;
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
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.ChangPwdActivity;
import com.trust.ebikeapp.activity.resetpwd.ResetPwdActivity;
import com.trust.ebikeapp.activity.update.nickname.NickNameUpdateActivity;
import com.trust.ebikeapp.activity.update.phone.PhoneUpdateActivity;
import com.trust.ebikeapp.activity.update.speed.SpeedUpdateActivity;
import com.trust.ebikeapp.activity.update.termid.TermIdUpdateActivity;
import com.trust.ebikeapp.fragment.BaseFragment;
import com.trust.ebikeapp.tool.TimeTool;
import com.trust.ebikeapp.tool.dialog.DialogTool;

import java.util.Map;
import java.util.WeakHashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Trust on 2017/5/12.
 */
public class MyFragment extends BaseFragment {
    @InjectView(R.id.myfragment_ext)
    ImageButton myfragmentExt;
    private View v;
    private Context context;
    private Activity activity;
    private TextView phoneTv, termIdTv, speedTv, nickNameTv, emailTv, userNameTv;
    private ImageButton lightBtn, oilAndElectricityBtn, changPwdBtn;
    private RelativeLayout phoneUpdateBtn, termIdUpdateBtn, speedUpdateBtn, nickNameUpdateBtn, emailUpdateBtn;
    private int oilAndElectricitStatus = 0, lightStatus = 0;//0开  1关

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        this.activity = activity;
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = LayoutInflater.from(context).inflate(R.layout.fragment_my, null);
        ButterKnife.inject(this, v);
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
        changPwdBtn = (ImageButton) v.findViewById(R.id.myfragment_chang_pwd);
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
        onClick(changPwdBtn);
        onClick(myfragmentExt);
    }

    private void update() {
        phoneTv.setText(Config.phone + "");
        termIdTv.setText(Config.termId + "");
        speedTv.setText(Config.speed);
        nickNameTv.setText(Config.nickname);
        userNameTv.setText(Config.nickname);
        emailTv.setText(Config.emaill);
    }


    @Override
    public void clickResult(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
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
                intent.setClass(context, ResetPwdActivity.class);
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
                if (lightStatus == 0) {
                    lightStatus = 1;
                } else {
                    lightStatus = 0;
                }
                requestCarLightAndOilOrElectricity(Config.car_light,
                        Config.carLight, lightStatus);
                break;

            case R.id.myfragment_off_the_oil_or_electricity:
                if (oilAndElectricitStatus == 0) {
                    oilAndElectricitStatus = 1;
                } else {
                    oilAndElectricitStatus = 0;
                }
                requestCarLightAndOilOrElectricity(Config.off_the_oil_or_electricity,
                        Config.offTheOilOrElectricity
                        , oilAndElectricitStatus);
                break;

            case R.id.myfragment_chang_pwd:
                intent.setClass(context, ChangPwdActivity.class);
                toIntent(intent);
                break;

            case R.id.myfragment_ext:
                DialogTool.showBackDialog(activity);
                break;


        }

    }

    private void requestCarLightAndOilOrElectricity(String url, int type, int status) {
        Map<String, Object> map = new WeakHashMap<>();
        map.put("termId", Config.termId);
        map.put("userCellPhone", Config.phone);
        map.put("appSN", TimeTool.getSystemTimeDate() / 1000);
        map.put("mod", status);
        requestCallBeack(url, map, type, Config.needAdd);
    }


    private void toIntent(Intent intent) {
        context.startActivity(intent);
    }

    @Override
    public void successCallBeack(Object obj, int type) {
        switch (type) {
            case Config.offTheOilOrElectricity:

                if (oilAndElectricitStatus == 0) {
                    //关闭成功
                    oilAndElectricityBtn.setImageResource(R.drawable.off);
                } else {
                    //打开成功
                    oilAndElectricityBtn.setImageResource(R.drawable.on);
                }
                break;
            case Config.carLight:
                if (lightStatus == 0) {
                    //关闭成功
                    lightBtn.setImageResource(R.drawable.off);
                } else {
                    //打开成功
                    lightBtn.setImageResource(R.drawable.on);
                }
                break;
        }
    }

    @Override
    protected void doError(int type) {
        switch (type) {
            case Config.carLight:
                if (lightStatus == 0) {//还原请求前的状态
                    lightStatus = 1;
                } else {
                    lightStatus = 0;
                }
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
