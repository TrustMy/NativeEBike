package com.phonegap.ebike.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.view.RxView;
import com.phonegap.ebike.Config;
import com.phonegap.ebike.R;
import com.phonegap.ebike.activity.BaseActivity;
import com.phonegap.ebike.tool.PersionAuthority;
import com.phonegap.ebike.tool.T;
import com.phonegap.ebike.tool.TextUtlis;
import com.phonegap.ebike.tool.TrustException;
import com.phonegap.ebike.tool.bean.ErrorResultBean;
import com.phonegap.ebike.tool.dialog.DialogTool;
import com.phonegap.ebike.tool.internet.Post;
import com.phonegap.ebike.tool.trustinterface.ResultCallBack;

import java.util.Map;
import java.util.concurrent.TimeUnit;


import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Trust on 2017/5/12.
 */
public class BaseFragment extends Fragment {
    private Activity context;

    @Override
    public void onAttach(Activity activity) {
        context = activity;
        super.onAttach(activity);
    }

    public Post post;
    public ResultCallBack resultCallBack = new ResultCallBack() {
        @Override
        public void CallBeck(Object obj, int type, int status) {
            resultCallBeack(obj,type,status);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        post = new Post(resultCallBack);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
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

    public void extFragmet(){
        getFragmentManager().popBackStack();
    }

    public void requestCallBeack(String url, Map<String,Object> map, int type, boolean isNeed){
        if(TrustException.isNetConnected()){
            if(PersionAuthority.checkAuthority(type ,map) == 0){
                DialogTool.showError((BaseActivity) context, TextUtlis.getMsg(R.string.persionAuthority));
            }else{
                showDialog();
                post.Request(url,map,type,isNeed);
            }
        }else{
            DialogTool.showError((BaseActivity)context, TextUtlis.getMsg(R.string.internetError));
        }

    }


    //网络请求回调

    public void resultCallBeack(Object obj,int type,int status){
        dissDialog();
        if(status == Config.SUCCESS){
            successCallBeack(obj,type);
        }else{
            errorCallBeack((ErrorResultBean)obj,type);
        }
    }

    public void successCallBeack(Object obj,int type){
    }

    public void errorCallBeack(ErrorResultBean obj, int type){
        if(type == Config.trickLocation){
            showErrorToast(Config.context,obj.toString(),3);
        }else{
            DialogTool.showError((BaseActivity)context,obj);
        }
            doError(type);
    }

    protected void doError(int type){

    }



    public void showDialog(){
        DialogTool.waitDialog(context);
    }

    public void dissDialog(){
        DialogTool.dismissWartDialog();
    }

    public void showWaitToast(Context context, String msg, int time){
        T.waitToast(context,msg,time*1000);
    }
    public void showErrorToast(Context context,String msg,int time){
        T.errorToast(context,msg,time*1000);
    }


}
