package com.trust.ebikeapp.tool.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
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
     * 订单dialog
     * @param context

     */
    public static void waitDialog(Activity context){
            View view = LayoutInflater.from(context).inflate(R.layout.wait_login_dialog,null);
            ImageView img = (ImageView) view.findViewById(R.id.wait_log_img);

            dialog = new Dialog(context, R.style.customDialog);
            Glide.with(context).load(R.drawable.wait_log).
                placeholder(R.drawable.wait_logo_first)
                .error(R.drawable.wait_logo_first).
                diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade(1000).bitmapTransform(
                new RoundedCornersTransformation(context,30,0,
                        RoundedCornersTransformation.
                                CornerType.ALL)).into(img);
            dialog.setContentView(view);
//          dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
            if(!context.isFinishing()){
            dialog.show();
            }


    }




    public interface PhoneOnClick {
        void onClick(View v);
    }

    public static PhoneOnClick phoneOnClick;
}
