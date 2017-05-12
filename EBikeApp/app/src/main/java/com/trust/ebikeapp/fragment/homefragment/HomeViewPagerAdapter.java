package com.trust.ebikeapp.fragment.homefragment;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.trust.ebikeapp.R;
import com.trust.ebikeapp.tool.bean.HomeViewPagerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trust on 2017/5/11.
 */
public class HomeViewPagerAdapter extends PagerAdapter {
    private Activity context;
    private List<HomeViewPagerBean> articles;
    private List<ImageView> ml = new ArrayList<>();

    public HomeViewPagerAdapter(Activity context) {
        this.context = context;

        for (int i = 0; i < 3; i++) {
            ImageView s= new ImageView(context);
            s.setImageResource(R.drawable.wind);
            ml.add(s);

        }

    }

    @Override
    public int getCount() {
        return ml.size()!=0 ?ml.size():0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(ml.get(position));
        return ml.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(ml.get(position));
    }
}
