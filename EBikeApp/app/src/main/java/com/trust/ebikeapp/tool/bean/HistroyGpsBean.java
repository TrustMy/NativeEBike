package com.trust.ebikeapp.tool.bean;

import java.io.Serializable;

/**
 * Created by Trust on 2017/5/23.
 */

public class HistroyGpsBean implements Serializable {

    private long fireOnTime ,fireOffTime;
    private double fireOnLat,fireOnlon,fireOffLat,fireOffLon;
    private String onName,offName;


    public HistroyGpsBean(long fireOnTime, long fireOffTime, double fireOnLat, double fireOnlon, double fireOffLat, double fireOffLon,String onName,String offName) {
        this.fireOnTime = fireOnTime;
        this.fireOffTime = fireOffTime;
        this.fireOnLat = fireOnLat;
        this.fireOnlon = fireOnlon;
        this.fireOffLat = fireOffLat;
        this.fireOffLon = fireOffLon;
        this.onName = onName;
        this.offName = offName;
    }


    public String getOnName() {
        return onName;
    }

    public void setOnName(String onName) {
        this.onName = onName;
    }

    public String getOffName() {
        return offName;
    }

    public void setOffName(String offName) {
        this.offName = offName;
    }

    public long getFireOnTime() {
        return fireOnTime;
    }

    public void setFireOnTime(long fireOnTime) {
        this.fireOnTime = fireOnTime;
    }

    public long getFireOffTime() {
        return fireOffTime;
    }

    public void setFireOffTime(long fireOffTime) {
        this.fireOffTime = fireOffTime;
    }

    public double getFireOnLat() {
        return fireOnLat;
    }

    public void setFireOnLat(double fireOnLat) {
        this.fireOnLat = fireOnLat;
    }

    public double getFireOnlon() {
        return fireOnlon;
    }

    public void setFireOnlon(double fireOnlon) {
        this.fireOnlon = fireOnlon;
    }

    public double getFireOffLat() {
        return fireOffLat;
    }

    public void setFireOffLat(double fireOffLat) {
        this.fireOffLat = fireOffLat;
    }

    public double getFireOffLon() {
        return fireOffLon;
    }

    public void setFireOffLon(double fireOffLon) {
        this.fireOffLon = fireOffLon;
    }
}
