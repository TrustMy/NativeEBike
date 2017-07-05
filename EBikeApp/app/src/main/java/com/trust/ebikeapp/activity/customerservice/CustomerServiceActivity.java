package com.trust.ebikeapp.activity.customerservice;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CustomerServiceActivity extends BaseActivity {

    @InjectView(R.id.activity_customer_service_back)
    ImageButton activityCustomerServiceBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service);
        ButterKnife.inject(this);

        initView();
    }

    private void initView() {
        onClick(activityCustomerServiceBack);
    }

    @Override
    public void clickResult(View v) {
        if(v.getId() == R.id.activity_customer_service_back){
            finish();
        }
    }
}
