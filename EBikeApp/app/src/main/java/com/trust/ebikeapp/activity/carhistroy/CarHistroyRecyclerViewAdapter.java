package com.trust.ebikeapp.activity.carhistroy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.trust.ebikeapp.R;
import com.trust.ebikeapp.tool.bean.CarStrokeBean;

import java.util.List;

/**
 * Created by Trust on 2017/5/18.
 */

public class CarHistroyRecyclerViewAdapter extends RecyclerView.Adapter<CarHistroyRecyclerViewAdapter.ViewHodler> {
    private List<CarStrokeBean.ContentBean.TripsBean> ml;
    private Context context;
    public CarHistroyRecyclerViewAdapter(Context context) {
       this.context = context;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.car_histroy_item, null);
        ViewHodler holder = new ViewHodler(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return ml != null ? ml.size() : 10;
    }

    class ViewHodler extends RecyclerView.ViewHolder{

        public ViewHodler(View itemView) {
            super(itemView);
        }
    }
}
