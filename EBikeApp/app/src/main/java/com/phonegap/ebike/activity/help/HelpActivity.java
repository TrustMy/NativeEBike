package com.phonegap.ebike.activity.help;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.phonegap.ebike.R;
import com.phonegap.ebike.activity.BaseActivity;
import com.phonegap.ebike.activity.helpmanual.HelpManualActivity;

public class HelpActivity extends BaseActivity {
    private ImageButton backBtn;
    private TextView helpManualBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_help_toolbar);
        setSupportActionBar(toolbar);

        initView();
    }

    private void initView() {
        backBtn = (ImageButton) findViewById(R.id.activity_help_back);
        helpManualBtn = (TextView) findViewById(R.id.activity_help_help_manual);
        onClick(backBtn);
        onClick(helpManualBtn);
    }

    @Override
    public void clickResult(View v) {
        switch (v.getId()){
            case R.id.activity_help_back:
                finish();
                break;
            case R.id.activity_help_help_manual:
                startActivity(new Intent(HelpActivity.this, HelpManualActivity.class));
                break;
        }
    }
}
