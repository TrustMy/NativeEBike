package com.trust.ebikeapp.tool.bean;

/**
 * Created by Trust on 2017/5/11.
 */
public class HomeViewPagerBean {
    // 新闻的 id
    private int index;
    // 新闻里的图片 url
    private String imageUrl;

    private int imgId;

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public HomeViewPagerBean(int index, String imageUrl , int imgId) {
        this.index = index;
        this.imageUrl = imageUrl;
        this.imgId = imgId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
