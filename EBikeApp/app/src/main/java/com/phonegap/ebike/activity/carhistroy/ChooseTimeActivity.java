package com.phonegap.ebike.activity.carhistroy;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.phonegap.ebike.Config;
import com.phonegap.ebike.R;
import com.phonegap.ebike.activity.BaseActivity;
import com.phonegap.ebike.tool.L;
import com.phonegap.ebike.tool.TextUtlis;
import com.phonegap.ebike.tool.TimeTool;
import com.phonegap.ebike.tool.dialog.DialogTimeTool;
import com.phonegap.ebike.tool.dialog.DoubleDatePickerDialog;

import java.util.Calendar;

public class ChooseTimeActivity extends BaseActivity {
    private Button determineBtn,cancelBtn,todayBtn,yesterdatBtn,lastWeekBtn;
    private TextView startTv,endTv;
    private long startTime,endTime;
    private long day = 86400000,lastWeek = 518400000,offTime = 86399000;//offtime 结束时间+offtime就是当天23:59:59
    private String timeOn ,timeOff;
    private Context context = ChooseTimeActivity.this;
    private TextView title;
    private long mTime = TimeTool.getSystemTimeDate();
    private ImageButton backBtn;
    private DialogTimeTool dialogTimeTool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_time);


        intView();
        initDate();
    }

    private void initDate() {
        String titleMsg = getIntent().getStringExtra("title");
        if (titleMsg != null) {
            title.setText(titleMsg);
        }

    }

    private void intView() {
        determineBtn = (Button) findViewById(R.id.choose_time_determine);
        onClick(determineBtn);
        cancelBtn = (Button) findViewById(R.id.choose_time_cancel);
        onClick(cancelBtn);

        yesterdatBtn = (Button) findViewById(R.id.yesterday_btn);
        onClick(yesterdatBtn);
        todayBtn = (Button) findViewById(R.id.today_btn);
        onClick(todayBtn);
        lastWeekBtn = (Button) findViewById(R.id.last_week_btn);
        onClick(lastWeekBtn);


        startTv = (TextView) findViewById(R.id.choose_time_starttv);
        endTv = (TextView) findViewById(R.id.choose_time_endtv);
        onClick(startTv);
        onClick(endTv);

        String time = TimeTool.getTime(mTime,Config.timeTypeYears);
        startTv.setText(time);
        endTv.setText(time);
        timeOff = time;
        timeOn = time;

        title = (TextView) findViewById(R.id.choose_time_title);

        backBtn = (ImageButton) findViewById(R.id.choose_time_back);
        onClick(backBtn);



        dialogTimeTool = new DialogTimeTool();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void clickResult(View v) {

        switch (v.getId()){
            case R.id.choose_time_cancel:
                finish();
                break;
            case R.id.choose_time_determine:
                L.d("Math.abs(TimeTool.getTime(timeOff) - TimeTool.getTime(timeOn)):"+Math.abs(TimeTool.getTime(timeOff) - TimeTool.getTime(timeOn))+
                "|offtime:"+TimeTool.getTime(timeOff)+"|onTime:"+TimeTool.getTime(timeOn));
                if(Math.abs(TimeTool.getTime(timeOff) - TimeTool.getTime(timeOn)) > lastWeek){
                    showErrorToast(context, TextUtlis.getMsg(R.string.chooseTimeOneWeekError),1);
                }else{
                    startTime = TimeTool.getTime(startTv.getText().toString());
                    endTime = TimeTool.getTime(endTv.getText().toString());
                    endTime = endTime+ offTime;
                    if(startTime <= endTime){
                        L.d("startTime:"+startTime+"|endTime"+endTime);
                        Intent intent = new Intent(context,CarHistroyActivity.class);
                        intent.putExtra("fireOnTimeDate",startTime);
                        intent.putExtra("fireOffTimeDate",endTime);
                        intent.putExtra("fireOnTime",startTv.getText().toString());
                        intent.putExtra("fireOffTime",endTv.getText().toString());
                        setResult(1,intent);
                        finish();
                    }else{
                        showErrorToast(context, TextUtlis.getMsg(R.string.chooseTimeError),1);
                    }

                }

                break;

            case R.id.yesterday_btn:

                long time  = TimeTool.getSystemTimeDate();
                if(time != 0){
                     time = time - day;
                    String yesterday = TimeTool.getTime(time,Config.timeTypeYears);
                    startTv.setText(yesterday);
                    endTv.setText(TimeTool.getSystemTime());
                }
                break;
            case R.id.today_btn:
                String today = TimeTool.getTime(System.currentTimeMillis(),Config.timeTypeYears);
                startTv.setText(today);
                endTv.setText(TimeTool.getSystemTime());
                break;
            case R.id.last_week_btn:
                long time1  = TimeTool.getSystemTimeDate();
                if(time1 != 0){
                    time1 = time1 - lastWeek;
                    String lastWeek = TimeTool.getTime(time1,Config.timeTypeYears);
                    startTv.setText(lastWeek);
                    endTv.setText(TimeTool.getSystemTime());
                }
                break;

            case R.id.choose_time_starttv:
                dialogTimeTool.showTimeDialog(this);
                dialogTimeTool.times = new DialogTimeTool.Time() {
                    @Override
                    public void getTime(String time) {
                        if(time != null){
                            timeOn = time;
                            startTv.setText(timeOn);
                        }else{
                            timeOn = TimeTool.getTimeAll(TimeTool.getSystemTimeDate());
                        }

                    }
                };

                break;
            case R.id.choose_time_endtv:
                dialogTimeTool.showTimeDialog(this);
                dialogTimeTool.times = new DialogTimeTool.Time() {
                    @Override
                    public void getTime(String time) {
                        if(time != null){
                            timeOff = time;
                            endTv.setText(timeOff);
                        }else{
                            timeOff = TimeTool.getTimeAll(TimeTool.getSystemTimeDate());
                        }

                    }
                };
//                showChooseTime();
                break;

            case R.id.choose_time_back:
                finsh(this);
                break;
        }

    }

    private void showChooseTime() {
        Calendar c = Calendar.getInstance();
        // 最后一个false表示不显示日期，如果要显示日期，最后参数可以是true或者不用输入
        new DoubleDatePickerDialog(ChooseTimeActivity.this, 0,
                new DoubleDatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker startDatePicker,
                                          int startYear, int startMonthOfYear,
                                          int startDayOfMonth, DatePicker endDatePicker,
                                          int endYear, int endMonthOfYear,
                                          int endDayOfMonth) {


                        StringBuffer startTime = new StringBuffer();
                        StringBuffer endTime = new StringBuffer();

                        if((startMonthOfYear + 1)<=9){

                            startTime.append(startYear+"-").append(0+""+(startMonthOfYear + 1)+"-")
                                    .append(startDayOfMonth+"");
                        }else{
                            startTime.append(startYear+"-").append((startMonthOfYear + 1)+"-")
                                    .append(startDayOfMonth+"");
                        }
                        if((endMonthOfYear + 1) <=9){
                            endTime.append(endYear+"-").append(0+""+(endMonthOfYear + 1)+"-")
                                    .append(endDayOfMonth+"");
                        }else{
                            endTime.append(endYear+"-").append((endMonthOfYear + 1)+"-")
                                    .append(endDayOfMonth+"");
                        }


                        startTv.setText(startTime);
                        endTv.setText(endTime);

                        timeOn = startTime.toString();
                        timeOff = endTime.toString();

                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE),
                true).show();
    }

}
