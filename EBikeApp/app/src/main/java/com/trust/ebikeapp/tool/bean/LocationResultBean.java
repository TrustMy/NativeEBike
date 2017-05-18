package com.trust.ebikeapp.tool.bean;

/**
 * Created by Trust on 2017/5/16.
 */

public class LocationResultBean {


    /**
     * content : {"acc":1,"rssi":24,"address":"上海市闸北区大宁路街道江场路出口(南北高架路出口北向);南北高架路共和新路出口与南北高架路路口西26米","lng":121.445251,"alt":0,"gpsSpeed":0,"gpsTime":1488165995000,"type":1,"voltage":28.1,"termId":860337030343524,"fix":0,"engine":4,"station":6324,"course":0,"block":62449,"lat":31.29512}
     * status : true
     */

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
        /**
         * acc : 1
         * rssi : 24
         * address : 上海市闸北区大宁路街道江场路出口(南北高架路出口北向);南北高架路共和新路出口与南北高架路路口西26米
         * lng : 121.445251
         * alt : 0
         * gpsSpeed : 0
         * gpsTime : 1488165995000
         * type : 1
         * voltage : 28.1
         * termId : 860337030343524
         * fix : 0
         * engine : 4
         * station : 6324
         * course : 0
         * block : 62449
         * lat : 31.29512
         */

        private int acc;
        private int rssi;
        private String address;
        private double lng;
        private int alt;
        private int gpsSpeed;
        private long gpsTime;
        private int type;
        private double voltage;
        private long termId;
        private int fix;
        private int engine;
        private int station;
        private int course;
        private int block;
        private double lat;

        public void setAcc(int acc) {
            this.acc = acc;
        }

        public void setRssi(int rssi) {
            this.rssi = rssi;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public void setAlt(int alt) {
            this.alt = alt;
        }

        public void setGpsSpeed(int gpsSpeed) {
            this.gpsSpeed = gpsSpeed;
        }

        public void setGpsTime(long gpsTime) {
            this.gpsTime = gpsTime;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setVoltage(double voltage) {
            this.voltage = voltage;
        }

        public void setTermId(long termId) {
            this.termId = termId;
        }

        public void setFix(int fix) {
            this.fix = fix;
        }

        public void setEngine(int engine) {
            this.engine = engine;
        }

        public void setStation(int station) {
            this.station = station;
        }

        public void setCourse(int course) {
            this.course = course;
        }

        public void setBlock(int block) {
            this.block = block;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public int getAcc() {
            return acc;
        }

        public int getRssi() {
            return rssi;
        }

        public String getAddress() {
            return address;
        }

        public double getLng() {
            return lng;
        }

        public int getAlt() {
            return alt;
        }

        public int getGpsSpeed() {
            return gpsSpeed;
        }

        public long getGpsTime() {
            return gpsTime;
        }

        public int getType() {
            return type;
        }

        public double getVoltage() {
            return voltage;
        }

        public long getTermId() {
            return termId;
        }

        public int getFix() {
            return fix;
        }

        public int getEngine() {
            return engine;
        }

        public int getStation() {
            return station;
        }

        public int getCourse() {
            return course;
        }

        public int getBlock() {
            return block;
        }

        public double getLat() {
            return lat;
        }
    }
}
