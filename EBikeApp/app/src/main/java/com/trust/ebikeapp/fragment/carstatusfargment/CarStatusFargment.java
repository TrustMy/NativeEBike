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

import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.fragment.BaseFragment;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.tool.L;
import com.trust.ebikeapp.tool.T;
import com.trust.ebikeapp.tool.TimeTool;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by Trust on 2017/5/12.
 */
public class CarStatusFargment extends BaseFragment {
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
        this.context =context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = LayoutInflater.from(context).inflate(R.layout.fragment_carstatus,null);
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.carstatus_toolbar);
        ((AppCompatActivity)context).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        initView();
        return v;
    }

    private void initView() {
        checkCarStatusBtn = (Button) v.findViewById(R.id.carstatus_checkCarStatus);
        onClick(checkCarStatusBtn);
    }



    @Override
    public void clickResult(View v) {
        switch (v.getId()){
            case R.id.carstatus_checkCarStatus:
                Map<String , Object> map = new WeakHashMap<>();
                map.put("termId",Config.termId);
                map.put("userCellPhone",Config.phone);
                map.put("appSN", TimeTool.getSystemTimeDate() / 1000);
                requestCallBeack(Config.self_test,map,Config.selfTest,Config.needAdd);
                break;
        }
    }


    @Override
    public void successCallBeack(Object obj, int type) {
        switch (type){
            case Config.selfTest:
                T.showToast(context,"自检成功!");
                break;
        }
    }
}
