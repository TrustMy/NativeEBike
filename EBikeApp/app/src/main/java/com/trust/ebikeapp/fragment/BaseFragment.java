package com.trust.ebikeapp.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.view.RxView;
import com.trust.ebikeapp.tool.internet.Post;

import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Trust on 2017/5/12.
 */
public class BaseFragment extends Fragment {
    public Post post;
    public Handler fragmentHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Result(msg.what,msg.obj);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        post = new Post(fragmentHandler);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void Result (int type , Object msg){

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
}
