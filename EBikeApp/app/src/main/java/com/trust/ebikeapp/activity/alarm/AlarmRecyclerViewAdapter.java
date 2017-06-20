package com.trust.ebikeapp.activity.alarm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trust.ebikeapp.R;
import com.trust.ebikeapp.tool.TimeTool;
import com.trust.ebikeapp.tool.bean.AlarmBean;


import java.util.List;

/**
 * Created by Trust on 2017/6/20.
 */

public class AlarmRecyclerViewAdapter extends RecyclerView.Adapter<AlarmRecyclerViewAdapter.ViewHodler> {
    private List<AlarmBean.ContentBean.AlarmsBean> ml ;
    private Context context;

    public void setMl(List<AlarmBean.ContentBean.AlarmsBean> ml) {
        this.ml = ml;
    }

    public AlarmRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.alarm_item, null);
        final ViewHodler holder = new ViewHodler(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
        holder.time.setText(TimeTool.getTime(ml.get(position).getGpsTime()));
        holder.address.setText(ml.get(position).getLat()+"");
        holder.type.setText(ml.get(position).getStatus()+"");
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
}
