package com.trust.ebikeapp.activity.alarm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trust.ebikeapp.R;
import com.trust.ebikeapp.tool.TimeTool;
import com.trust.ebikeapp.tool.bean.AlarmBean;
import com.trust.ebikeapp.tool.bean.AlarmLocationAddressBean;
import com.trust.ebikeapp.tool.bean.HistroyGpsBean;


import java.util.List;

/**
 * Created by Trust on 2017/6/20.
 */

public class AlarmRecyclerViewAdapter extends RecyclerView.Adapter<AlarmRecyclerViewAdapter.ViewHodler> {
    private List<AlarmBean.ContentBean.AlarmsBean> ml ;
    private List<AlarmLocationAddressBean> addressList;
    private Context context;
    private String type;

    public void setMl(List<AlarmBean.ContentBean.AlarmsBean> ml,List<AlarmLocationAddressBean> addressList) {
        this.ml = null;
        this.ml = ml;
        this.addressList = addressList;
    }

    public AlarmRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.alarm_item, null);
        final ViewHodler holder = new ViewHodler(view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                setAlarmRecyclerAdapterClickListener.clickCallBack(view,pos);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
        holder.time.setText(TimeTool.getTimeAll(ml.get(position).getGpsTime()));
        holder.address.setText(addressList.get(position).getAddress());
        if(ml.get(position).getPlugout() == 1){
            type = context.getResources().getString(R.string.alarmTypePullOut);
        }else if (ml.get(position).getLowVoltage() == 1){
            type = context.getResources().getString(R.string.alarmTypeElectricity);
        }else if(ml.get(position).getVibration() == 1){
            type = context.getResources().getString(R.string.alarmTypeShock);
        }
        holder.type.setText(type);
    }

    @Override
    public int getItemCount() {
       return ml!= null ?ml.size():0;
    }

    class ViewHodler extends RecyclerView.ViewHolder{
        TextView time,address,type;
        View view;
        public ViewHodler(View itemView) {
            super(itemView);
            view = itemView;
            time = (TextView) itemView.findViewById(R.id.alarm_item_time);
            address = (TextView) itemView.findViewById(R.id.alarm_item_address);
            type = (TextView) itemView.findViewById(R.id.alarm_item_type);
        }
    }

    interface alarmRecyclerAdapterClickListener{
        void  clickCallBack(View v,Object bean);
    }

    public  alarmRecyclerAdapterClickListener setAlarmRecyclerAdapterClickListener;
}
