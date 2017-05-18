package com.trust.ebikeapp.tool;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.trust.ebikeapp.R;

/**
 * Created by Trust on 2017/4/12.
 */
public class T {
    private static Toast toast;

    public static void showToast(Context context,
                                 String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }



    public static void waitToast (Context context, String msg , int time )
    {

        View view= LayoutInflater.from(context).inflate(R.layout.wait_toast, null);
        TextView txt=(TextView) view.findViewById(R.id.text);
        txt.setText(msg);

         toast = new Toast(context);
//        toast.setGravity(Gravity.CENTER, 0, 160);
        toast.setDuration(time);
        toast.setView(view);
        toast.show();
    }


    public static  void errorToast(Context context,String msg,int time)
    {
        View view=LayoutInflater.from(context).inflate(R.layout.error_toast, null);
        TextView txt=(TextView) view.findViewById(R.id.error_toast_text);
        txt.setText(msg);
        toast = new Toast(context);
//        toast.setGravity(Gravity.CENTER, 0, 160);
        toast.setDuration(time);
        toast.setView(view);
        toast.show();
    }
}
