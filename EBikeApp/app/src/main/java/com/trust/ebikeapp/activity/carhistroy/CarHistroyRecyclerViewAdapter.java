package com.trust.ebikeapp.activity.carhistroy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trust.ebikeapp.R;
import com.trust.ebikeapp.tool.TimeTool;
import com.trust.ebikeapp.tool.bean.CarStrokeAndAddress;
import com.trust.ebikeapp.tool.bean.CarStrokeBean;
import com.trust.ebikeapp.tool.bean.HistroyGpsBean;

import java.util.List;

/**
 * Created by Trust on 2017/5/18.
 */

public class CarHistroyRecyclerViewAdapter extends RecyclerView.Adapter<CarHistroyRecyclerViewAdapter.ViewHodler> {
    private CarStrokeAndAddress ml;
    private Context context;
    public CarHistroyRecyclerViewAdapter(Context context) {
       this.context = context;
    }

    public void setMl(CarStrokeAndAddress ml) {
        this.ml = ml;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.car_histroy_item, null);
        final ViewHodler holder = new ViewHodler(view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                HistroyGpsBean bean = new HistroyGpsBean(ml.getTripsBeenList().get(pos).
                        getFireOnTime(),ml.getTripsBeenList().get(pos).getFireOffTime(),
                        ml.getTripsBeenList().get(pos).getFireOnLat(),
                        ml.getTripsBeenList().get(pos).getFireOnLng(),
                        ml.getTripsBeenList().get(pos).getFireOffLat(),
                        ml.getTripsBeenList().get(pos).getFireOffLng(),
                        ml.getAddressList().get(pos).getFireOnName(),
                        ml.getAddressList().get(pos).getFireOffName()
                        );
                click.clickCallBack(view,bean);
            }
        });
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
        holder.titleTime.setText(TimeTool.getTime(ml.getTripsBeenList().get(position).getFireOnTime()));
        holder.fireOnTime.setText(TimeTool.getTime(ml.getTripsBeenList().get(position).getFireOnTime()));
        holder.fireOnName.setText(ml.getAddressList().get(position).getFireOnName());
        holder.fireOffTime.setText(TimeTool.getTime(ml.getTripsBeenList().get(position).getFireOffTime()));
        holder.fireOffName.setText(ml.getAddressList().get(position).getFireOffName());
    }

    @Override
    public int getItemCount() {
        return ml!= null ?ml.getTripsBeenList().size():0;
    }

    class ViewHodler extends RecyclerView.ViewHolder{
        TextView titleTime,fireOnTime,fireOnName,fireOffTime,fireOffName;
        View view;
        public ViewHodler(View itemView) {
            super(itemView);
            view = itemView;
            titleTime = (TextView) itemView.findViewById(R.id.activity_car_histroy_item_time_title);
            fireOnTime = (TextView) itemView.findViewById(R.id.activity_car_histroy_item_fireon_time);
            fireOnName = (TextView) itemView.findViewById(R.id.activity_car_histroy_item_fireon_name);
            fireOffTime = (TextView) itemView.findViewById(R.id.activity_car_histroy_item_fireoff_time);
            fireOffName = (TextView) itemView.findViewById(R.id.activity_car_histroy_item_fireoff_name);

        }
    }


    interface  onClick{
        void  clickCallBack(View v,HistroyGpsBean bean);
    }

    onClick click;
}
