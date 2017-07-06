package com.trust.ebikeapp.tool.dialog;

import android.animation.Animator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.BaseActivity;
import com.trust.ebikeapp.tool.bean.ErrorResultBean;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


/**
 * Created by Trust on 2017/4/9.
 */

public class DialogTool  {

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

    public static void dismissWartDialog(){
        handler.removeMessages(1);
        dialog.dismiss();
    }




    public interface PhoneOnClick {
        void onClick(View v);
    }

    public static PhoneOnClick phoneOnClick;


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



    public static void showBackDialog(final Activity activity){
        View view = LayoutInflater.from(activity).inflate(R.layout.back_dialog,null);

        final Dialog dialog = new Dialog(activity, R.style.customDialog);

        Button determine = (Button) view.findViewById(R.id.back_dialog_determine);
        Button cancel = (Button) view.findViewById(R.id.back_dialog_cancel);
        determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!activity.isFinishing()){
                    Config.loginStatus = false;
                    if(!activity.isFinishing()){
                        activity.finish();
                    }
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        if(!activity.isFinishing()){
            dialog.show();
        }
    }


    public static void showError(final BaseActivity activity, final Object bean){
        final Dialog dialog = new Dialog(activity, R.style.customDialog);
        View view = LayoutInflater.from(activity).inflate(R.layout.error_dialog,null);
        Button determine = (Button) view.findViewById(R.id.error_dialog_determine);
        determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    dialog.dismiss();
                if(bean instanceof  ErrorResultBean){
                    if (((ErrorResultBean)bean).getCode() == 1) {
                        activity.killAllActivtiy(activity);
                    }
                }
            }
        });
        TextView msgTv = (TextView) view.findViewById(R.id.error_dialog_msg);

        if(bean instanceof String){
            msgTv.setText(bean.toString());
        } else if (bean instanceof ErrorResultBean) {
            msgTv.setText(((ErrorResultBean)bean).getErr());
        }
        dialog.setCanceledOnTouchOutside(false);

        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.errorDialog);
        dialog.setContentView(view);
        if(!activity.isFinishing()){
            dialog.show();
        }
    }

    public static void showAlarmError(Activity activity,String type , String msg){
        final Dialog dialog = new Dialog(activity, R.style.customDialog);
        View view = LayoutInflater.from(activity).inflate(R.layout.error_alarm_dialog,null);
        TextView titleTv = (TextView) view.findViewById(R.id.error_alarm_dialog_type);
        TextView msgTv = (TextView) view.findViewById(R.id.error_alarm_dialog_msg);
        titleTv.setText(type);
        Button determine = (Button) view.findViewById(R.id.error_alarm_dialog_determine);
        determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                alarmErrorDialogCallBack.CallBack();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);
        if(!activity.isFinishing()){
            dialog.show();
        }
    }

     public interface  AlarmErrorDialogCallBack{
        void CallBack();
    }
    public static AlarmErrorDialogCallBack  alarmErrorDialogCallBack;




    public static void handleToolBar(final Context context, final View search, final EditText editText) {
        //隐藏
        if (search.getVisibility() == View.VISIBLE) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                final Animator animatorHide = ViewAnimationUtils.createCircularReveal(search,
                        search.getWidth() - 56,
                        23,
                        //确定元的半径（算长宽的斜边长，这样半径不会太短也不会很长效果比较舒服）
                        (float) Math.hypot(search.getWidth(), search.getHeight()),
                        0);
                animatorHide.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        search.setVisibility(View.GONE);
                        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(search.getWindowToken(), 0);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animatorHide.setDuration(300);
                animatorHide.start();
            } else {
//                关闭输入法
                ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(search.getWindowToken(), 0);
                search.setVisibility(View.GONE);
            }
            editText.setText("");
            search.setEnabled(false);
        }
        //显示
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                final Animator animator = ViewAnimationUtils.createCircularReveal(search,
                        search.getWidth() - 56,
                        23,
                        0,
                        (float) Math.hypot(search.getWidth(), search.getHeight()));
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                search.setVisibility(View.VISIBLE);
                if (search.getVisibility() == View.VISIBLE) {
                    animator.setDuration(300);
                    animator.start();
                    search.setEnabled(true);
                }
            } else {
                search.setVisibility(View.VISIBLE);
                search.setEnabled(true);
                //                关闭输入法
                ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        }
    }
}
