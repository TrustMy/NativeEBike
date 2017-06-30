package com.trust.ebikeapp.activity.carhistroy.vehicletrajectory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.R;
import com.trust.ebikeapp.tool.TimeTool;
import com.trust.ebikeapp.tool.bean.CarStrokeBean;
import com.trust.ebikeapp.tool.bean.HistroyGpsBean;
import com.trust.ebikeapp.tool.bean.LocationAddressBean;
import com.trust.ebikeapp.tool.uitool.PercentLinearLayout;

import java.util.List;

/**
 * Created by Trust on 2017/5/18.
 */

public class CarHistroyRecyclerViewNowAdapter extends RecyclerView.Adapter<CarHistroyRecyclerViewNowAdapter.ViewHodler> {
    private Context context;
    public CarHistroyRecyclerViewNowAdapter(Context context) {
       this.context = context;
    }

    private List<CarStrokeBean.ContentBean.TripsBean> tripsBeanList;
    private List<LocationAddressBean> locationAddressBeanList;

    public void setMl(List<CarStrokeBean.ContentBean.TripsBean> tripsBeanList,List<LocationAddressBean> locationAddressBeanList) {
        this.tripsBeanList = null;
        this.locationAddressBeanList = null;
        this.tripsBeanList = tripsBeanList;
        this.locationAddressBeanList = locationAddressBeanList;



    }

    private String  date;//记录日期
    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.car_histroy_now_item, null);
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
        holder.time.setText(TimeTool.getTime(tripsBeanList.get(position).getFireOnTime(),Config.timeTypeMinutes)
        +" - "+TimeTool.getTime(tripsBeanList.get(position).getFireOffTime(),Config.timeTypeMinutes));

        holder.fireOnName.setText(locationAddressBeanList.get(position).getFireOnName());
        holder.fireOffName.setText(locationAddressBeanList.get(position).getFireOffName());
    }

    @Override
    public int getItemCount() {
        return tripsBeanList!= null ?tripsBeanList.size():0;
    }

    class ViewHodler extends RecyclerView.ViewHolder{
        TextView time,fireOnName,fireOffName;
        PercentLinearLayout linearLayout;
        View view;
        public ViewHodler(View itemView) {
            super(itemView);
            view = itemView;
            time = (TextView) itemView.findViewById(R.id.activity_car_now_histroy_item_time);
            fireOnName = (TextView) itemView.findViewById(R.id.activity_car_histroy_now_item_fireon_name);
            fireOffName = (TextView) itemView.findViewById(R.id.activity_car_histroy_now_item_fireoff_name);


        }
    }


    public interface  onClick{
        void  clickCallBack(View v, HistroyGpsBean bean);
    }

    public onClick click;
}
