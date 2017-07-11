package com.trust.ebikeapp.tool.dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.trust.ebikeapp.R;
import com.trust.ebikeapp.tool.L;

import java.util.Calendar;

/**
 * Created by Trust on 2017/7/10.
 */

public class DialogTimeTool {
    public String time;
    @RequiresApi(api = Build.VERSION_CODES.N)
    public  void showTimeDialog(Activity activity){
        final Calendar showDate=Calendar.getInstance();//calendarViewShown
        /*
        new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                showDate.set(Calendar.YEAR,year);
                showDate.set(Calendar.MONTH,monthOfYear);
                showDate.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                L.d("选择的时间"+DateFormat.format("yyyy-MM-dd",showDate));
            }
        }, showDate.get(Calendar.YEAR), showDate.get(Calendar.MONTH), showDate.get(Calendar.DAY_OF_MONTH)).setView().show();

        */


        int year,month,day;
        year =   showDate.get(Calendar.YEAR);
        month =   showDate.get(Calendar.MONTH);
        day =   showDate.get(Calendar.DAY_OF_MONTH);


        View view = LayoutInflater.from(activity).inflate(R.layout.time_dialog,null);

        final TextView title = (TextView) view.findViewById(R.id.time_dialog_title_time);
        title.setText(year+"年"+(month+1)+"月"+day+"日");
        DatePicker datePicker = (DatePicker) view.findViewById(R.id.time_dialog_datepicker);
        datePicker.init(year, month,day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                title.setText(year+"年"+(month+1)+"月"+day+"日");
                StringBuffer Time = new StringBuffer();
                if((month + 1)<=9){
                    if((day )<=9){

                        Time.append(year+"-").append(0+""+(month + 1)+"-")
                                .append("0"+day);
                    }else{
                        Time.append(year+"-").append(0+""+(month + 1)+"-")
                                .append(day+"");
                    }
                }else{
                    if((day )<=9){

                        Time.append(year+"-").append((month + 1)+"-")
                                .append("0"+day);
                    }else{
                        Time.append(year+"-").append((month + 1)+"-")
                                .append(day+"");
                    }
                }








                time = Time.toString();
//                show.setText("您选择的日期和时间为：" + year + "年"
//                        + (month + 1) + "月" + day + "日  "
//                        + hour + "时" + minute + "分");
            }
        });
        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                showDate.set(Calendar.YEAR,year);
                showDate.set(Calendar.MONTH,monthOfYear);
                showDate.set(Calendar.DAY_OF_MONTH,dayOfMonth);
//                L.d("选择的时间"+DateFormat.format("yyyy-MM-dd",showDate));
//                time = year+"-"+(monthOfYear+1)+"-"+dayOfMonth;
                times.getTime(time);
            }
        }, showDate.get(Calendar.YEAR), showDate.get(Calendar.MONTH), showDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setView(view);
        datePickerDialog.show();

    }

    public  String  getTime(){
        return time;
    }


    public interface Time{void getTime(String time);}
    public Time times;
}
