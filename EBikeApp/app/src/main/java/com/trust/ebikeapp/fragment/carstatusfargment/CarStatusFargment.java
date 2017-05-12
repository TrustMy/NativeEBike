package com.trust.ebikeapp.fragment.carstatusfargment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trust.ebikeapp.fragment.BaseFragment;
import com.trust.ebikeapp.R;

/**
 * Created by Trust on 2017/5/12.
 */
public class CarStatusFargment extends BaseFragment {
    private View v;
    private Context context;
    private Activity activity;

    @Override
    public void onAttach(Activity activity) {
        this.activity = activity;
        super.onAttach(activity);
    }

    @Override
    public void onAttach(Context context) {
        this.context =context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = LayoutInflater.from(context).inflate(R.layout.fragment_carstatus,null);
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.carstatus_toolbar);
        ((AppCompatActivity)context).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        return v;
    }



}
