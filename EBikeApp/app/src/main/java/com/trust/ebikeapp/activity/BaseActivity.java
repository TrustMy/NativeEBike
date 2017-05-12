package com.trust.ebikeapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;

import com.jakewharton.rxbinding2.view.RxView;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.tool.internet.Post;

import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Trust on 2017/5/10.
 */
public class BaseActivity extends AppCompatActivity {
    protected Post post;
    public Handler baseHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Result(msg.obj,msg.what);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lgin);

        init();

    }

    private void init() {
        post = new Post(baseHandler);
    }

    //网络请求回调
    public void Result(Object obj , int type) {
    }


    public void  onClick(final View v){
        RxView.clicks(v).throttleFirst(5, TimeUnit.SECONDS).
                subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        clickResult(v);
                    }
                });
    }

    public void clickResult(View v){

    }
    public void  onClickFinsh(final View v, final Activity activity){
        RxView.clicks(v).throttleFirst(5, TimeUnit.SECONDS).
                subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        activity.finish();;
                    }
                });
    }



}
