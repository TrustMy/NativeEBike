package com.phonegap.ebike.tool.push;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;

import com.baidu.android.pushservice.PushMessageReceiver;
import com.phonegap.ebike.Config;
import com.phonegap.ebike.R;
import com.phonegap.ebike.tool.L;
import com.phonegap.ebike.tool.trustinterface.PushCallBack;


import java.util.List;

/**
 * Created by Trust on 2017/3/13.
 */
public class PushTool extends PushMessageReceiver {

    public static Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public static PushCallBack pushCallBack;
    private MediaPlayer mp;
    private boolean isStart = false;//是否处于播放中
    @Override
    public void onBind(Context context, int i, String s, String s1, String s2, String s3) {

        L.i("onBin i:"+i+"| s:"+s+"| s1 :"+s1+"|s2 PushId:"+s2+"|s3:"+s3);
        if(i == 0 && s2 != null)
        {
            PushId.ID = s2;
        }


    }



    @Override
    public void onUnbind(Context context, int i, String s) {
        L.i("onUnbind");
    }

    @Override
    public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {
        L.i("onSetTags");
    }

    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {
        L.i("onDelTags");
    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {
        L.i("onListTags");
    }

    @Override
    public void onMessage(Context context, String s, String s1) {
        L.i("onMessage"+s+"\n:ss"+s+"\ns1:"+s1);
    }

    @Override
    public void onNotificationClicked(Context context, String s, String s1, String s2) {
        L.i("onNotificationClicked");
//        Toast.makeText(context, "onNotificationClicked", Toast.LENGTH_SHORT).show();
//        Message message = new Message();
//        message.what = EBikeConstant.DIALOG;
//
//        DialogBean dialogBean =   new DialogBean();
//        dialogBean.setTitle(s);
//        dialogBean.setMsg(s1);
//        message.obj =dialogBean;
//        handler.sendMessage(message);

        pushCallBack.CallBack(s,s1);
    }

    @Override
    public void onNotificationArrived(Context context, String s, String s1, String s2) {
        L.i("onNotificationArrived"+s+"|\ns1 \n"+s1+"|\ns2"+s2);
//        Toast.makeText(context, "onNotificationArrived", Toast.LENGTH_SHORT).show();
        if (mp == null) {
            mp = MediaPlayer.create(context, R.raw.alarm);
        }
        if (!isStart && s.equals("报警")) {
            isStart = true;
            mp.start();
        }
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                isStart = false;
            }
        });
    }
}
