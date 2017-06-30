package com.trust.ebikeapp.tool;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Trust on 2017/6/30.
 */

public class CheckNumTool <T extends View>{

    public void startTime(final T value){
        final int count = 60;
        Observable.interval(0,1, TimeUnit.SECONDS).take(count+1).map(new Function<Long, Object>() {
            @Override
            public Object apply(@NonNull Long aLong) throws Exception {
                L.d("定时器: count:"+count+"|aLong:"+aLong);
                return count-aLong;
            }
        }).subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        value.setEnabled(false);//不可点击
                    }
                }).observeOn(AndroidSchedulers.mainThread())//操作UI主要在UI线程
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        checkT(value,o+"秒");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        value.setEnabled(true);//不可点击
                       checkT(value,"获取验证码");
                    }
                });
    }

    public void checkT(T v,String msg){
        if(v instanceof Button){
            ((Button)v).setText(msg);
        }else if(v instanceof TextView){
            ((TextView)v).setText(msg);
        }
    }

}
