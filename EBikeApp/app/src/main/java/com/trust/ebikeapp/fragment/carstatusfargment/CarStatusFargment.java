package com.trust.ebikeapp.fragment.carstatusfargment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.fragment.BaseFragment;
import com.trust.ebikeapp.tool.TimeTool;
import com.trust.ebikeapp.tool.bean.SelfTestBean;

import java.util.Map;
import java.util.WeakHashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Trust on 2017/5/12.
 */
public class CarStatusFargment extends BaseFragment {
    @InjectView(R.id.carstatus_fragment_hall)
    TextView carstatusFragmentHall;
    @InjectView(R.id.carstatus_fragment_phase_line)
    TextView carstatusFragmentPhaseLine;
    @InjectView(R.id.carstatus_fragment_turn_the_handle)
    TextView carstatusFragmentTurnTheHandle;
    @InjectView(R.id.carstatus_fragment_controller)
    TextView carstatusFragmentController;
    @InjectView(R.id.carstatus_fragment_hall_status_img)
    ImageView carstatusFragmentHallStatusImg;
    @InjectView(R.id.carstatus_fragment_hall_status)
    TextView carstatusFragmentHallStatus;
    @InjectView(R.id.carstatus_fragment_phase_line_status)
    TextView carstatusFragmentPhaseLineStatus;
    @InjectView(R.id.carstatus_fragment_phase_line_status_img)
    ImageView carstatusFragmentPhaseLineStatusImg;
    @InjectView(R.id.carstatus_fragment_turn_the_handle_status_img)
    ImageView carstatusFragmentTurnTheHandleStatusImg;
    @InjectView(R.id.carstatus_fragment_turn_the_handle_status)
    TextView carstatusFragmentTurnTheHandleStatus;
    @InjectView(R.id.carstatus_fragment_controller_status)
    TextView carstatusFragmentControllerStatus;
    @InjectView(R.id.carstatus_fragment_controller_status_img)
    ImageView carstatusFragmentControllerStatusImg;
    @InjectView(R.id.carstatus_fragment_ic)
    ImageView carstatusFragmentIc;
    private View v;
    private Context context;
    private Activity activity;
    private Button checkCarStatusBtn;

    @Override
    public void onAttach(Activity activity) {
        this.activity = activity;
        super.onAttach(activity);
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = LayoutInflater.from(context).inflate(R.layout.fragment_carstatus, null);
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.carstatus_toolbar);
        ((AppCompatActivity) context).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        initView();
        ButterKnife.inject(this, v);
        requestDate();
        return v;
    }



    private void initView() {
        checkCarStatusBtn = (Button) v.findViewById(R.id.carstatus_checkCarStatus);
        onClick(checkCarStatusBtn);
    }


    @Override
    public void clickResult(View v) {
        switch (v.getId()) {
            case R.id.carstatus_checkCarStatus:
                requestDate();
                break;
        }
    }

    private void requestDate() {
        Map<String, Object> map = new WeakHashMap<>();
        map.put("termId", Config.termId);
        map.put("userCellPhone", Config.phone);
        map.put("appSN", TimeTool.getSystemTimeDate() / 1000);
        requestCallBeack(Config.self_test, map, Config.selfTest, Config.needAdd);
    }


    @Override
    public void successCallBeack(Object obj, int type) {
        switch (type) {
            case Config.selfTest:
                showWaitToast(context, "自检成功", 3);
                checkStatus(obj);
                break;
        }
    }

    private void checkStatus(Object obj) {
        SelfTestBean bean = (SelfTestBean) obj;
        if (bean.getContent().getFault() != null) {

//            setImg(bean.getContent().getFault().getFault1(),
//                    carstatusFragmentControllerStatusImg
//            ,carstatusFragmentControllerStatus,R.drawable.control,R.drawable.control_bad)
//            ;//控制器

            setImg(bean.getContent().getFault().getFault2(),
                    carstatusFragmentPhaseLineStatusImg
                    , carstatusFragmentPhaseLineStatus, R.drawable.electric_machinery,
                    R.drawable.electric_machinery_problem, carstatusFragmentPhaseLine,
                    context.getResources().getString(R.string.checkPhaseLine),
                    context.getResources().getString(R.string.checkPhaseLineError));//电机相线

            setImg(bean.getContent().getFault().getFault3(),
                    carstatusFragmentControllerStatusImg
                    , carstatusFragmentControllerStatus, R.drawable.control, R.
                            drawable.control_bad, carstatusFragmentController,
                    context.getResources().getString(R.string.checkController),
                    context.getResources().getString(R.string.checkControllerError));//控制器故障

            setImg(bean.getContent().getFault().getFault4(),
                    carstatusFragmentHallStatusImg
                    , carstatusFragmentHallStatus, R.drawable.electric_machinery, R.
                            drawable.electric_machinery_problem, carstatusFragmentHall,
                    context.getResources().getString(R.string.checkHall),
                    context.getResources().getString(R.string.checkHallError));//电机霍尔故障


            setImg(bean.getContent().getFault().getFault4(),
                    carstatusFragmentTurnTheHandleStatusImg
                    , carstatusFragmentTurnTheHandleStatus, R.drawable.turn, R.
                            drawable.turn_bad, carstatusFragmentTurnTheHandle,
                    context.getResources().getString(R.string.checkTurnTheHandle),
                    context.getResources().getString(R.string.checkTurnTheHandleError));//转把故障

        }else{

        }
    }


    private void setImg(int status, ImageView img, TextView statusTv, int ic, int icError, TextView textTv, String msg, String msgError) {
        if (status == 0) {//正常
            carstatusFragmentIc.setVisibility(View.INVISIBLE);
            statusTv.setText("正常");
            textTv.setText(msg);
            Glide.with(activity).load(ic).into(img);
        } else {//异常
            carstatusFragmentIc.setVisibility(View.VISIBLE);
            statusTv.setText("异常");
            textTv.setText(msgError);
            Glide.with(activity).load(icError).into(img);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
