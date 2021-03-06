package com.phonegap.ebike.fragment.homefragment;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.phonegap.ebike.tool.bean.HomeViewPagerBean;

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


    }



    public void setArticles(List<HomeViewPagerBean> articles) {
        this.articles = articles;
        for (int i = 0; i < articles.size(); i++) {
            ImageView img= new ImageView(context);
//            img.setBackgroundResource(articles.get(i).getImgId());
            img.setScaleType(ImageView.ScaleType.FIT_XY);//铺满屏幕
            Glide.with(context).load(articles.get(i).getImgId()).placeholder(articles.get(i).getImgId()).crossFade().into(img);
            ml.add(img);

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
    public Object instantiateItem(ViewGroup container, final int position) {
        View v = ml.get(position);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPagerAdapterOnClickListener.adapterOnClick(articles.get(position).getUrl());
            }
        });
        container.addView(ml.get(position));
        return ml.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(ml.get(position));
    }


    interface ViewPagerAdapterOnClickListener{
        void adapterOnClick(String url);
    }
    public ViewPagerAdapterOnClickListener viewPagerAdapterOnClickListener;
}
