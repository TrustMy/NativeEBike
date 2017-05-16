package com.trust.ebikeapp.activity.carhistroy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.trust.ebikeapp.R;
import com.trust.ebikeapp.activity.BaseActivity;
import com.trust.ebikeapp.tool.L;
import com.trust.ebikeapp.tool.TimeTool;
import com.trust.ebikeapp.tool.dialog.DoubleDatePickerDialog;

import java.util.Calendar;

public class ChooseTimeActivity extends BaseActivity {
    private Button determineBtn,cancelBtn,todayBtn,yesterdatBtn,lastWeekBtn;
    private TextView startTv,endTv;
    private long startTime,endTime;
    private long day = 86400000,lastWeek = 604800000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_time);


        intView();
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
    }

    @Override
    public void clickResult(View v) {

        switch (v.getId()){
            case R.id.choose_time_cancel:
                finish();
                break;
            case R.id.choose_time_determine:
                startTime = TimeTool.getTime(startTv.getText().toString());
                endTime = TimeTool.getTime(endTv.getText().toString());
                L.d("startTime:"+startTime+"|endTime"+endTime);
                break;

            case R.id.yesterday_btn:

                long time  = TimeTool.getSystemTimeDate();
                if(time != 0){
                     time = time - day;
                    String yesterday = TimeTool.getTime(time);
                    startTv.setText(yesterday);
                    endTv.setText(TimeTool.getSystemTime());
                }
                break;
            case R.id.today_btn:
                String today = TimeTool.getTime(System.currentTimeMillis());
                startTv.setText(today);
                endTv.setText(TimeTool.getSystemTime());
                break;
            case R.id.last_week_btn:
                long time1  = TimeTool.getSystemTimeDate();
                if(time1 != 0){
                    time1 = time1 - lastWeek;
                    String lastWeek = TimeTool.getTime(time1);
                    startTv.setText(lastWeek);
                    endTv.setText(TimeTool.getSystemTime());
                }
                break;

            case R.id.choose_time_starttv:
            case R.id.choose_time_endtv:
                showChooseTime();
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

                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE),
                true).show();
    }

}
