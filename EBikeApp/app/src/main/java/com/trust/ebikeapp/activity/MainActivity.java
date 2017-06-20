package com.trust.ebikeapp.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.trust.ebikeapp.R;
import com.trust.ebikeapp.fragment.carstatusfargment.CarStatusFargment;
import com.trust.ebikeapp.fragment.homefragment.HomeFragment;
import com.trust.ebikeapp.fragment.myfragment.MyFragment;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{
    private FragmentTransaction transaction;
    private Fragment homeFragment ,carStatusFragment,myFragment;
    private RadioGroup radioGroup;
    private RadioButton homeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFargment();
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
}
