package com.trust.ebikeapp.tool.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.trust.ebikeapp.R;


/**
 * Created by Trust on 2017/4/9.
 */

public class DialogTool {
    static Dialog dialog;

    static View view;


    public DialogTool() {

    }
    public static  click onClick;
      public interface click {
        void onClick();
    }


    /**
     * 订单dialog
     * @param context
     * @param layout
     * @param bitmap
     * @param time
     */
    public static void showDialog(Activity context, int layout, Bitmap bitmap, String time){

        if(dialog  == null){
            view = LayoutInflater.from(context).inflate(layout,null);

            dialog = new Dialog(context, R.style.customDialog);

        }

        dialog.setContentView(view);
//        dialog.setCancelable(true);
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
