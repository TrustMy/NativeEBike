package com.trust.ebikeapp.activity.helpmanual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;

import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.BaseActivity;

public class HelpManualActivity extends BaseActivity {
    private ImageButton backBtn;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_manual);

        initView();
    }

    private void initView() {
        backBtn = (ImageButton) findViewById(R.id.activity_help_manual_back);
        onClick(backBtn);

        webView = (WebView) findViewById(R.id.activity_help_manual_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/ebhelp/userGuide.html");
    }

    @Override
    public void clickResult(View v) {
        finish();
    }
}
