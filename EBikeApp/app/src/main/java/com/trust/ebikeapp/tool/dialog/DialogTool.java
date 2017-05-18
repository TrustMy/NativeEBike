package com.trust.ebikeapp.tool.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.BaseActivity;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


/**
 * Created by Trust on 2017/4/9.
 */

public class DialogTool {

    public static Dialog dialog;

    public DialogTool() {

    }
    public static  click onClick;
      public interface click {
        void onClick();
    }


    /**
     * dialog
     * @param activity

     */
    public static void waitDialog(Activity activity){
           context  = activity;
            View view = LayoutInflater.from(activity).inflate(R.layout.wait_login_dialog,null);
            ImageView img = (ImageView) view.findViewById(R.id.wait_log_img);

            dialog = new Dialog(activity, R.style.customDialog);
            Glide.with(activity).load(R.drawable.wait_log).
                placeholder(R.drawable.wait_logo_first)
                .error(R.drawable.wait_logo_first).
                diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade(1000).bitmapTransform(
                new RoundedCornersTransformation(activity,30,0,
                        RoundedCornersTransformation.
                                CornerType.ALL)).into(img);
            dialog.setContentView(view);
//          dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失

            if(!activity.isFinishing()){
            dialog.show();
            }

        handler.sendEmptyMessageDelayed(1,1000*15);


    }




    public interface PhoneOnClick {
        void onClick(View v);
    }

    public static PhoneOnClick phoneOnClick;

    public static  Activity context;
    public static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                        dialog.dismiss();
                    break;
            }
        }
    };
}
