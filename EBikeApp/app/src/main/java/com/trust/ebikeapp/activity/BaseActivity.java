package com.trust.ebikeapp.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.jakewharton.rxbinding2.view.RxView;
import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.alarm.AlarmActivity;
import com.trust.ebikeapp.tool.L;
import com.trust.ebikeapp.tool.PersionAuthority;
import com.trust.ebikeapp.tool.T;
import com.trust.ebikeapp.tool.dialog.DialogTool;
import com.trust.ebikeapp.tool.internet.Post;
import com.trust.ebikeapp.tool.internet.ssl.Get;
import com.trust.ebikeapp.tool.push.PushTool;
import com.trust.ebikeapp.tool.push.Utils;
import com.trust.ebikeapp.tool.trustinterface.PushCallBack;
import com.trust.ebikeapp.tool.trustinterface.ResultCallBack;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Trust on 2017/5/10.
 */
public class BaseActivity extends AppCompatActivity {
    protected Post post;
    protected Get get;
    public static Activity activity ;
    private Context context = BaseActivity.this;
    public ResultCallBack resultCallBack = new ResultCallBack() {
        @Override
        public void CallBeck(Object obj, int type, int status) {
            resultCallBeack(obj,type,status);
        }
    };

    private PushCallBack pushCallBack = new PushCallBack() {
        @Override
        public void CallBack(String title, String msg) {
            L.d("title :"+title+"|"+msg);
            DialogTool.showAlarmError(activity,title,msg);
            DialogTool.alarmErrorDialogCallBack = new DialogTool.AlarmErrorDialogCallBack() {
                @Override
                public void CallBack() {
                    L.d("Config.loginStatus:"+Config.loginStatus);
                    if(Config.loginStatus){
                       startActivity(new Intent(activity, AlarmActivity.class));
                    }else{
                        showErrorToast(context,"请先登录,在查看详细信息!",3);
                    }
                }
            };
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lgin);
        activity = this;
        init();
        initPush();
    }

    private void initPush() {
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY,
                Utils.getMetaValue(BaseActivity.this, "api_key"));

    }

    private void init() {
        post = new Post(resultCallBack);
        get = new Get(resultCallBack);
        PushTool.pushCallBack = pushCallBack;
    }


    public void requestCallBeack(String url, Map<String,Object> map,int type,boolean isNeed){
        if(PersionAuthority.checkAuthority(type ,map) == 0){

        }else{
            showDialog();
            post.Request(url,map,type,isNeed);
        }

    }



    public void requestGetCallBeack(String url,int type){
        get.Request(url,type);
    }


    //网络请求回调

    public void resultCallBeack(Object obj,int type,int status){
        if(type != Config.updateApp){
            dissDialog();
        }
        if(status == Config.SUCCESS){
            successCallBeack(obj,type);
        }else{
            errorCallBeack(obj,type);
        }


    }
    public void successCallBeack(Object obj,int type){
        if(type == Config.updateApp){
            L.d("update app Success");
        }
    }

    public void errorCallBeack(Object obj,int type){
        if(type == Config.trickLocation){
            showErrorToast(Config.context,obj.toString(),3);
        }else{
            DialogTool.showError(this,obj.toString());
        }
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

    public void showWaitToast(Context context,String msg,int time){
        T.waitToast(context,msg,time*1000);
    }
    public void showErrorToast(Context context,String msg,int time){
        T.errorToast(context,msg,time*1000);
    }

    public void finsh(Activity activity){
        if (activity != null) {
            activity.finish();
        }
    }

    /**
     * 申请验证码
     */
    protected void requestCheckNum() {
        Map<String,Object> map =  new WeakHashMap<>();
        map.put("cp", Config.phone);
        requestCallBeack(Config.get_check_num, map, Config.getCheckNum, Config.noAdd);
    }

    /**
     * 检测 输入是否为空
     * @param editText
     * @param errorMsg
     * @return
     */
    protected  String checkMessage(EditText editText,String errorMsg){
        String msg = editText.getText().toString().trim();
        if(!msg.equals("")){
            return msg;
        }else{
            showErrorToast(context,errorMsg,1);
            return null;
        }
    }

}
