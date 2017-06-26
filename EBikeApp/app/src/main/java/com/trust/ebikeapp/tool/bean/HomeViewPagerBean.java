package com.trust.ebikeapp.tool.bean;

/**
 * Created by Trust on 2017/5/11.
 */
public class HomeViewPagerBean {
    private String imgUrl;//网络图片
    private int imgId;//本地图片
    private String url;//点击跳转的url

    public HomeViewPagerBean(String imgUrl, int imgId, String url) {
        this.imgUrl = imgUrl;
        this.imgId = imgId;
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
