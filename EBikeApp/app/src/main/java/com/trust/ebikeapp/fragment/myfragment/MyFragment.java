package com.trust.ebikeapp.fragment.myfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trust.ebikeapp.fragment.BaseFragment;
import com.trust.ebikeapp.R;

/**
 * Created by Trust on 2017/5/12.
 */
public class MyFragment extends BaseFragment {
    private View v;
    private Context context;

    @Override
    public void onAttach(Context context) {
        this.context =context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = LayoutInflater.from(context).inflate(R.layout.fragment_my,null);
        return v;
    }
}
