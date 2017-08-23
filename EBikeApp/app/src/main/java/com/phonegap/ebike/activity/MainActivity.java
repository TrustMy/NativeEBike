package com.phonegap.ebike.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.phonegap.ebike.Config;
import com.phonegap.ebike.R;
import com.phonegap.ebike.fragment.carstatusfargment.CarStatusFargment;
import com.phonegap.ebike.fragment.homefragment.HomeFragment;
import com.phonegap.ebike.fragment.myfragment.MyFragment;
import com.phonegap.ebike.tool.dialog.DialogTool;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{
    private FragmentTransaction transaction;
    private Fragment homeFragment ,carStatusFragment,myFragment;
    private RadioGroup radioGroup;
    private RadioButton homeBtn;
    private Context context = MainActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFargment();

        Config.loginStatus = true;
        mainActivity = this;
    }

    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.main_radiogroup);
        radioGroup.setOnCheckedChangeListener(this);
        homeBtn = (RadioButton) findViewById(R.id.radio_home);
    }

    private void initFargment() {
        homeFragment = new HomeFragment();
        carStatusFragment = new CarStatusFargment();
        myFragment = new MyFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.homefragment_fragment,homeFragment);
        transaction.commit();

        homeBtn.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        transaction = getSupportFragmentManager().beginTransaction();
        switch (i){
            case R.id.radio_home:
                transaction.replace(R.id.homefragment_fragment,homeFragment);
                break;
            case R.id.radio_carstatus:
                transaction.replace(R.id.homefragment_fragment,carStatusFragment);
                break;
            case R.id.radio_my:
                transaction.replace(R.id.homefragment_fragment,myFragment);
                break;
        }
        transaction.commit();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            DialogTool.showBackDialog(this);
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }
}
