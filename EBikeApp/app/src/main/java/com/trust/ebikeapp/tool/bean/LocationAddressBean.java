package com.trust.ebikeapp.tool.bean;

/**
 * Created by Trust on 2017/5/19.
 */

public class LocationAddressBean {
    private String fireOnName,fireOffName;

    public LocationAddressBean(String fireOnName, String fireOffName) {
        this.fireOnName = fireOnName;
        this.fireOffName = fireOffName;
    }

    public String getFireOnName() {
        return fireOnName;
    }

    public void setFireOnName(String fireOnName) {
        this.fireOnName = fireOnName;
    }

    public String getFireOffName() {
        return fireOffName;
    }

    public void setFireOffName(String fireOffName) {
        this.fireOffName = fireOffName;
    }
}
