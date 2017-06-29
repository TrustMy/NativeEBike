package com.trust.ebikeapp.activity.carhistroy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.tool.L;
import com.trust.ebikeapp.tool.TimeTool;
import com.trust.ebikeapp.tool.bean.CarStrokeAndAddress;
import com.trust.ebikeapp.tool.bean.CarStrokeBean;
import com.trust.ebikeapp.tool.bean.HistroyGpsBean;
import com.trust.ebikeapp.tool.bean.LocationAddressBean;
import com.trust.ebikeapp.tool.uitool.PercentLinearLayout;

import java.util.List;

/**
 * Created by Trust on 2017/5/18.
 */

public class CarHistroyRecyclerViewAdapter extends RecyclerView.Adapter<CarHistroyRecyclerViewAdapter.ViewHodler> {
    private Context context;
    public CarHistroyRecyclerViewAdapter(Context context) {
       this.context = context;
    }

    private List<CarStrokeBean.ContentBean.TripsBean> tripsBeanList;
    private List<LocationAddressBean> locationAddressBeanList;

    public void setMl(List<CarStrokeBean.ContentBean.TripsBean> tripsBeanList,List<LocationAddressBean> locationAddressBeanList) {
        this.tripsBeanList = null;
        this.locationAddressBeanList = null;
        this.tripsBeanList = tripsBeanList;
        this.locationAddressBeanList = locationAddressBeanList;
        date = null;


    }

    private String  date;//记录日期
    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.car_histroy_item, null);
        final ViewHodler holder = new ViewHodler(view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                HistroyGpsBean bean = new HistroyGpsBean(tripsBeanList.get(pos).
                        getFireOnTime(),tripsBeanList.get(pos).getFireOffTime(),
                        tripsBeanList.get(pos).getFireOnLat(),
                        tripsBeanList.get(pos).getFireOnLng(),
                        tripsBeanList.get(pos).getFireOffLat(),
                        tripsBeanList.get(pos).getFireOffLng(),
                        locationAddressBeanList.get(pos).getFireOnName(),
                        locationAddressBeanList.get(pos).getFireOffName()
                        );
                click.clickCallBack(view,bean);
            }
        });
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
        holder.titleTime.setText(TimeTool.getTime(tripsBeanList.get(position).getFireOnTime(), Config.timeTypeYears));
        holder.fireOnTime.setText(TimeTool.getTime(tripsBeanList.get(position).getFireOnTime(),Config.timeTypeMinutesAndSeconds));
        holder.fireOnName.setText(locationAddressBeanList.get(position).getFireOnName());
        holder.fireOffTime.setText(TimeTool.getTime(tripsBeanList.get(position).getFireOffTime(),Config.timeTypeMinutesAndSeconds));
        holder.fireOffName.setText(locationAddressBeanList.get(position).getFireOffName());



        if(date == null){
            date = TimeTool.getTime(tripsBeanList.get(position).getFireOnTime(), Config.timeTypeYears);
        }else{
            if(date .equals(TimeTool.getTime(tripsBeanList.get(position).getFireOnTime(), Config.timeTypeYears))){
                holder.linearLayout.setVisibility(View.GONE);
            }else{
                holder.linearLayout.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return tripsBeanList!= null ?tripsBeanList.size():0;
    }

    class ViewHodler extends RecyclerView.ViewHolder{
        TextView titleTime,fireOnTime,fireOnName,fireOffTime,fireOffName;
        PercentLinearLayout linearLayout;
        LinearLayout loadDate;
        View view;
        public ViewHodler(View itemView) {
            super(itemView);
            view = itemView;
            titleTime = (TextView) itemView.findViewById(R.id.activity_car_histroy_item_time_title);
            fireOnTime = (TextView) itemView.findViewById(R.id.activity_car_histroy_item_fireon_time);
            fireOnName = (TextView) itemView.findViewById(R.id.activity_car_histroy_item_fireon_name);
            fireOffTime = (TextView) itemView.findViewById(R.id.activity_car_histroy_item_fireoff_time);
            fireOffName = (TextView) itemView.findViewById(R.id.activity_car_histroy_item_fireoff_name);
            TextPaint tp = titleTime.getPaint();
            tp.setFakeBoldText(true);
            linearLayout = (PercentLinearLayout) itemView.findViewById(R.id.activity_car_histroy_item_time_title_lin);
        }
    }


    interface  onClick{
        void  clickCallBack(View v,HistroyGpsBean bean);
    }

    onClick click;
}
