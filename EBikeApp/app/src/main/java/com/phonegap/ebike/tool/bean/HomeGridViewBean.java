package com.phonegap.ebike.tool.bean;

/**
 * Created by Trust on 2017/5/12.
 */
public class HomeGridViewBean {
    private int imgId;
    private String title;

    public HomeGridViewBean(int imgId, String title) {
        this.imgId = imgId;
        this.title = title;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
