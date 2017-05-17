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
import com.trust.ebikeapp.tool.dialog.DialogTool;
import com.trust.ebikeapp.tool.internet.Post;
import com.trust.ebikeapp.tool.trustinterface.ResultCallBack;

import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Trust on 2017/5/10.
 */
public class BaseActivity extends AppCompatActivity {
    protected Post post;
    public static Activity activity ;
    public ResultCallBack resultCallBack = new ResultCallBack() {
        @Override
        public void CallBeck(Object obj, int type, int status) {
            resultCallBeack(obj,type,status);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lgin);
        activity = this;
        init();

    }

    private void init() {
        post = new Post(resultCallBack);
    }

    //网络请求回调

    public void resultCallBeack(Object obj,int type,int status){

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

    public void  onClickFinsh( final Activity activity){
                activity.finish();
    }

    public void showDialog(){
        DialogTool.waitDialog(this);
    }

    public void dissDialog(){
        DialogTool.dialog.dismiss();
    }


}
