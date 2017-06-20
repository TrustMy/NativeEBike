package com.trust.ebikeapp.tool.bean;

import java.util.List;

/**
 * Created by Trust on 2017/6/5.
 */

public class VehicleTrajectoryBean {

    private ContentBean content;
    private boolean status;

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ContentBean getContent() {
        return content;
    }

    public boolean getStatus() {
        return status;
    }

    public static class ContentBean {


        private int totalElements;
        private List<GpsBean> gps;

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public void setGps(List<GpsBean> gps) {
            this.gps = gps;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public List<GpsBean> getGps() {
            return gps;
        }

        public static class GpsBean {


            private long gpsTime;
            private int engine;
            private int station;
            private int block;
            private double lng;
            private int type;
            private int acc;
            private long termId;
            private int course;
            private String address;
            private double voltage;
            private int fix;
            private double alt;
            private double gpsSpeed;
            private int rssi;
            private double lat;

            public void setGpsTime(long gpsTime) {
                this.gpsTime = gpsTime;
            }

            public void setEngine(int engine) {
                this.engine = engine;
            }

            public void setStation(int station) {
                this.station = station;
            }

            public void setBlock(int block) {
                this.block = block;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public void setType(int type) {
                this.type = type;
            }

            public void setAcc(int acc) {
                this.acc = acc;
            }

            public void setTermId(long termId) {
                this.termId = termId;
            }

            public void setCourse(int course) {
                this.course = course;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public void setVoltage(double voltage) {
                this.voltage = voltage;
            }

            public void setFix(int fix) {
                this.fix = fix;
            }

            public void setAlt(double alt) {
                this.alt = alt;
            }

            public void setGpsSpeed(double gpsSpeed) {
                this.gpsSpeed = gpsSpeed;
            }

            public void setRssi(int rssi) {
                this.rssi = rssi;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public long getGpsTime() {
                return gpsTime;
            }

            public int getEngine() {
                return engine;
            }

            public int getStation() {
                return station;
            }

            public int getBlock() {
                return block;
            }

            public double getLng() {
                return lng;
            }

            public int getType() {
                return type;
            }

            public int getAcc() {
                return acc;
            }

            public long getTermId() {
                return termId;
            }

            public int getCourse() {
                return course;
            }

            public String getAddress() {
                return address;
            }

            public double getVoltage() {
                return voltage;
            }

            public int getFix() {
                return fix;
            }

            public double getAlt() {
                return alt;
            }

            public double getGpsSpeed() {
                return gpsSpeed;
            }

            public int getRssi() {
                return rssi;
            }

            public double getLat() {
                return lat;
            }
        }
    }
}
