package com.trust.ebikeapp.tool.bean;

/**
 * Created by Trust on 2017/5/16.
 */

public class CarLoationMessage {
    private double  lat,lon;
    private String  Type;
    private long gpsTime;
    private int GpsType;

    public CarLoationMessage(double lat, double lon, long time,String type,int GpsType) {
        this.lat = lat;
        this.lon = lon;
        Type = type;
        gpsTime = time;
        this.GpsType = GpsType;
    }

    public int getGpsType() {
        return GpsType;
    }

    public void setGpsType(int gpsType) {
        GpsType = gpsType;
    }

    public long getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(long gpsTime) {
        this.gpsTime = gpsTime;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(long lon) {
        this.lon = lon;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
