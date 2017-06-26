package com.trust.ebikeapp.fragment.homefragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.activity.alarm.AlarmActivity;
import com.trust.ebikeapp.activity.carhistroy.CarHistroyActivity;
import com.trust.ebikeapp.activity.carstatus.CarStatusActivity;
import com.trust.ebikeapp.activity.help.HelpActivity;
import com.trust.ebikeapp.activity.location.CarLocationActivity;
import com.trust.ebikeapp.fragment.BaseFragment;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.tool.L;
import com.trust.ebikeapp.tool.TimeTool;
import com.trust.ebikeapp.tool.bean.CarStatusBean;
import com.trust.ebikeapp.tool.bean.HomeViewPagerBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by Trust on 2017/5/11.
 */
public class HomeFragment extends BaseFragment implements ViewPager.OnPageChangeListener{
    private View v;
    private Context context;
    private Activity activity;

    private LinearLayout paint;
    private ImageView [] imgPaint;

    private ViewPager viewPager;
    private HomeViewPagerAdapter viewPagerAdapter;

    private RelativeLayout alarmNumLayout;
    private TextView alarmNum;

    private ImageButton fortificationBtn,carHistroyBtn,locationBtn,carStatusBtn,alarmBtn,
    helpBtn;

    private TextView fortificationTv;

    private boolean clickFortificationBtn = false;//防止点击设防按钮跳转页面
    private boolean fortificationStatus = false;//设防按钮状态

    private List<HomeViewPagerBean> ml = new ArrayList<>();

    @Override
    public void onAttach(Activity activity) {
        this.activity = activity;
        super.onAttach(activity);
    }

    //设置当前 第几个图片 被选中
    private int autoCurrIndex = 0;
    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    //定时轮播图片，需要在主线程里面修改 UI


    public Handler  advertisingHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:

                    if (msg.arg1 != 0) {
                        viewPager.setCurrentItem(msg.arg1);
                    } else {
                        //false 当从末页调到首页是，不显示翻页动画效果，
                        viewPager.setCurrentItem(msg.arg1, false);
                    }


                    startAdvertisingHandler();
                    break;
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = LayoutInflater.from(context).inflate(R.layout.fragment_home,null);
        initView();
        initData();
        startAdvertisingHandler();


        return v;
    }

    private void startAdvertisingHandler() {
        // 设置自动轮播图片，5s后执行，周期是5s

        Message message = Message.obtain();
        message.what = 1;
        if (autoCurrIndex == 3 - 1) {
            autoCurrIndex = -1;
        }
        message.arg1 = autoCurrIndex + 1;
        advertisingHandler.sendMessageDelayed(message,1000 * 3);
    }

    private void initData() {
        imgPaint = new ImageView[3];
        for (int i = 0; i < imgPaint.length; i++) {
            ImageView imageView = new ImageView(context);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30, 30);
            params.setMargins(5, 0, 5, 0);
            imageView.setLayoutParams(params);

            if (i == 0) {
                imageView.setBackgroundResource(R.drawable.paint_off);
            } else {
                imageView.setBackgroundResource(R.drawable.paint_on);
            }

            imgPaint[i] = imageView;
            //把指示作用的原点图片加入底部的视图中
            paint.addView(imgPaint[i]);
        }
        long appSN = TimeTool.getSystemTimeDate();
        Map<String,Object>map = new WeakHashMap<>();
        map.put("termId",Config.termId);
        map.put("userCellPhone",Config.phone);
        map.put("appSN",appSN/1000);
        requestCallBeack(Config.car_status,map,Config.carStatus,Config.needAdd);

        ml.add(new HomeViewPagerBean("www.wind.png",R.drawable.wind,"www.baidu1.com"));
        ml.add(new HomeViewPagerBean("www.two.png",R.drawable.advertising_two,"www.baidu2.com"));
        ml.add(new HomeViewPagerBean("www.there.png",R.drawable.advertising_there,"www.baidu3.com"));
        viewPagerAdapter.setArticles(ml);
        viewPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    private void initView() {
        viewPagerAdapter = new HomeViewPagerAdapter(activity);
        paint = (LinearLayout) v.findViewById(R.id.homefragment_viewpager_paint);
        viewPager = (ViewPager) v.findViewById(R.id.homefragment_viewpager);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(this);
        viewPagerAdapter.viewPagerAdapterOnClickListener = viewPagerAdapterOnClickListener;
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        L.d("ACTION_DOWN");
                        advertisingHandler.removeMessages(1);
                        break;
                    case MotionEvent.ACTION_UP:
                        L.d("ACTION_UP");
                        startAdvertisingHandler();
                        break;
                }
                return false;
            }
        });

        fortificationBtn = (ImageButton) v.findViewById(R.id.home_fortification);
        onClick(fortificationBtn);
        carHistroyBtn = (ImageButton) v.findViewById(R.id.home_car_history);
        onClick(carHistroyBtn);
        locationBtn = (ImageButton) v.findViewById(R.id.home_location);
        onClick(locationBtn);
        carStatusBtn = (ImageButton) v.findViewById(R.id.home_car_status);
        onClick(carStatusBtn);
        alarmBtn = (ImageButton) v.findViewById(R.id.home_alarm);
        onClick(alarmBtn);
        helpBtn = (ImageButton) v.findViewById(R.id.home_help);
        onClick(helpBtn);


        alarmNumLayout = (RelativeLayout) v.findViewById(R.id.homefragment_alarm_num_layout);
        alarmNum = (TextView) v.findViewById(R.id.homefragment_alarm_num);

        fortificationTv = (TextView) v.findViewById(R.id.home_fortification_tv);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
// 一定几个图片，几个圆点，但注意是从0开始的
        int total = imgPaint.length;
        for (int j = 0; j < total; j++) {
            if (j == position) {
                imgPaint[j].setBackgroundResource(R.drawable.paint_on);
            } else {
                imgPaint[j].setBackgroundResource(R.drawable.paint_off);
            }
        }

        //设置全局变量，currentIndex为选中图标的 index
        autoCurrIndex = position;
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void clickResult(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.home_fortification:
                clickFortificationBtn = true;
                doLock();
                break;
            case R.id.home_car_history:
                clickFortificationBtn = false;
                intent.setClass(context, CarHistroyActivity.class);
                break;
            case R.id.home_location:
                clickFortificationBtn = false;
                intent.setClass(context, CarLocationActivity.class);
                break;
            case R.id.home_car_status:
                clickFortificationBtn = false;
                intent.setClass(context, CarStatusActivity.class);
                break;
            case R.id.home_alarm:
                clickFortificationBtn = false;
                intent.setClass(context, AlarmActivity.class);
                break;
            case R.id.home_help:
                clickFortificationBtn = false;
                intent.setClass(context, HelpActivity.class);
                break;
        }
        if(!clickFortificationBtn){
            context.startActivity(intent);
        }
    }

    private void doLock() {
        Map<String,Object> map = new WeakHashMap<>();
        map.put("termId", Config.termId);
        map.put("userCellPhone",Config.phone);
        map.put("appSN", (TimeTool.getSystemTimeDate()/1000));
        map.put("lock",fortificationStatus);
        requestCallBeack(Config.car_lock_url,map,Config.lock,Config.needAdd);
    }

    @Override
    public void successCallBeack(Object obj, int type) {
        switch (type){
            case Config.carStatus:
                carStatus(obj);
                break;
            case Config.lock:
                if(fortificationStatus){
                    fortificationStatus = false;
                    showWaitToast(context,"设防成功!",1);

                    fortificationBtn.setBackgroundResource(R.drawable.home_fortification_on_btn_bg);
                    fortificationTv.setText("设防");
                }else{
                    fortificationBtn.setBackgroundResource(R.drawable.home_fortification_btn_bg);
                    fortificationTv.setText("解防");
                    fortificationStatus = true;
                    showWaitToast(context,"解防成功!",1);
                }
                break;
        }
    }

    private void carStatus(Object obj) {
        CarStatusBean bean = (CarStatusBean) obj;

        if(bean.getTotleAlarm() == 0){
            alarmNumLayout.setVisibility(View.GONE);
        }else{
            alarmNumLayout.setVisibility(View.VISIBLE);
            alarmNum.setText(bean.getTotleAlarm()+"");
        }

        //0解防 1设防
        if(bean.getContent().getLockStatus() == 0){
            fortificationStatus = false;
            fortificationBtn.setBackgroundResource(R.drawable.home_fortification_btn_bg);
            fortificationTv.setText("解防");
        }else{
            fortificationStatus = true;
            fortificationBtn.setBackgroundResource(R.drawable.home_fortification_on_btn_bg);
            fortificationTv.setText("设防");
        }

    }

    HomeViewPagerAdapter.ViewPagerAdapterOnClickListener  viewPagerAdapterOnClickListener = new HomeViewPagerAdapter.ViewPagerAdapterOnClickListener() {
        @Override
        public void adapterOnClick(String url) {
            showWaitToast(context,"你点击了广告url:"+url,3);
        }
    };


    @Override
    public void onDestroy() {
        advertisingHandler.removeMessages(1);
        super.onDestroy();
    }
}
