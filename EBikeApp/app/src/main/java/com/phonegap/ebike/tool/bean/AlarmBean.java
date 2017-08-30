package com.phonegap.ebike.tool.bean;

import java.util.List;

/**
 * Created by Trust on 2017/6/20.
 */

public class AlarmBean {


    /**
     * content : {"totalElements":18,"currentPage":0,"alarms":[{"gpsTime":1503987951000,"status":1,"engine":0,"station":29652,"block":30383,"lng":112.557976,"acc":0,"termId":860337030798859,"course":0,"plugout":0,"lowVoltage":0,"voltage":871.4,"fix":1,"alt":216.1,"gpsSpeed":0.1,"vibration":1,"rssi":22,"lat":26.89336},{"gpsTime":1503987951000,"status":1,"engine":0,"station":29652,"block":30383,"lng":112.557976,"acc":0,"termId":860337030798859,"course":0,"plugout":0,"lowVoltage":0,"voltage":886.4,"fix":1,"alt":216.1,"gpsSpeed":0.1,"vibration":1,"rssi":20,"lat":26.89336},{"gpsTime":1503987951000,"status":1,"engine":0,"station":29652,"block":30383,"lng":112.557976,"acc":0,"termId":860337030798859,"course":0,"plugout":0,"lowVoltage":0,"voltage":886.4,"fix":1,"alt":216.1,"gpsSpeed":0.1,"vibration":1,"rssi":20,"lat":26.89336},{"gpsTime":1503987951000,"status":1,"engine":0,"station":29652,"block":30383,"lng":112.557976,"acc":0,"termId":860337030798859,"course":0,"plugout":0,"lowVoltage":0,"voltage":886.4,"fix":1,"alt":216.1,"gpsSpeed":0.1,"vibration":1,"rssi":22,"lat":26.89336},{"gpsTime":1503987951000,"status":1,"engine":0,"station":29652,"block":30383,"lng":112.557976,"acc":0,"termId":860337030798859,"course":0,"plugout":0,"lowVoltage":0,"voltage":886.4,"fix":1,"alt":216.1,"gpsSpeed":0.1,"vibration":1,"rssi":21,"lat":26.89336},{"gpsTime":1503987951000,"status":1,"engine":0,"station":29652,"block":30383,"lng":112.557976,"acc":0,"termId":860337030798859,"course":0,"plugout":0,"lowVoltage":0,"voltage":901.4,"fix":1,"alt":216.1,"gpsSpeed":0.1,"vibration":1,"rssi":17,"lat":26.89336},{"gpsTime":1503987951000,"status":1,"engine":0,"station":29652,"block":30383,"lng":112.557976,"acc":0,"termId":860337030798859,"course":0,"plugout":0,"lowVoltage":0,"voltage":886.4,"fix":1,"alt":216.1,"gpsSpeed":0.1,"vibration":1,"rssi":23,"lat":26.89336},{"gpsTime":1503987951000,"status":1,"engine":0,"station":29652,"block":30383,"lng":112.557976,"acc":0,"termId":860337030798859,"course":0,"plugout":0,"lowVoltage":0,"voltage":886.4,"fix":1,"alt":216.1,"gpsSpeed":0.1,"vibration":1,"rssi":21,"lat":26.89336},{"gpsTime":1503987951000,"status":1,"engine":0,"station":29652,"block":30383,"lng":112.557976,"acc":0,"termId":860337030798859,"course":0,"plugout":0,"lowVoltage":0,"voltage":886.4,"fix":1,"alt":216.1,"gpsSpeed":0.1,"vibration":1,"rssi":21,"lat":26.89336},{"gpsTime":1503987228000,"status":1,"engine":0,"station":29652,"block":30383,"lng":0,"acc":0,"termId":860337030798859,"course":0,"plugout":0,"lowVoltage":0,"voltage":886.4,"fix":0,"alt":0,"gpsSpeed":0,"vibration":1,"rssi":18,"lat":0}],"currentSize":10,"totalPages":2}
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
         * totalElements : 18
         * currentPage : 0
         * alarms : [{"gpsTime":1503987951000,"status":1,"engine":0,"station":29652,"block":30383,"lng":112.557976,"acc":0,"termId":860337030798859,"course":0,"plugout":0,"lowVoltage":0,"voltage":871.4,"fix":1,"alt":216.1,"gpsSpeed":0.1,"vibration":1,"rssi":22,"lat":26.89336},{"gpsTime":1503987951000,"status":1,"engine":0,"station":29652,"block":30383,"lng":112.557976,"acc":0,"termId":860337030798859,"course":0,"plugout":0,"lowVoltage":0,"voltage":886.4,"fix":1,"alt":216.1,"gpsSpeed":0.1,"vibration":1,"rssi":20,"lat":26.89336},{"gpsTime":1503987951000,"status":1,"engine":0,"station":29652,"block":30383,"lng":112.557976,"acc":0,"termId":860337030798859,"course":0,"plugout":0,"lowVoltage":0,"voltage":886.4,"fix":1,"alt":216.1,"gpsSpeed":0.1,"vibration":1,"rssi":20,"lat":26.89336},{"gpsTime":1503987951000,"status":1,"engine":0,"station":29652,"block":30383,"lng":112.557976,"acc":0,"termId":860337030798859,"course":0,"plugout":0,"lowVoltage":0,"voltage":886.4,"fix":1,"alt":216.1,"gpsSpeed":0.1,"vibration":1,"rssi":22,"lat":26.89336},{"gpsTime":1503987951000,"status":1,"engine":0,"station":29652,"block":30383,"lng":112.557976,"acc":0,"termId":860337030798859,"course":0,"plugout":0,"lowVoltage":0,"voltage":886.4,"fix":1,"alt":216.1,"gpsSpeed":0.1,"vibration":1,"rssi":21,"lat":26.89336},{"gpsTime":1503987951000,"status":1,"engine":0,"station":29652,"block":30383,"lng":112.557976,"acc":0,"termId":860337030798859,"course":0,"plugout":0,"lowVoltage":0,"voltage":901.4,"fix":1,"alt":216.1,"gpsSpeed":0.1,"vibration":1,"rssi":17,"lat":26.89336},{"gpsTime":1503987951000,"status":1,"engine":0,"station":29652,"block":30383,"lng":112.557976,"acc":0,"termId":860337030798859,"course":0,"plugout":0,"lowVoltage":0,"voltage":886.4,"fix":1,"alt":216.1,"gpsSpeed":0.1,"vibration":1,"rssi":23,"lat":26.89336},{"gpsTime":1503987951000,"status":1,"engine":0,"station":29652,"block":30383,"lng":112.557976,"acc":0,"termId":860337030798859,"course":0,"plugout":0,"lowVoltage":0,"voltage":886.4,"fix":1,"alt":216.1,"gpsSpeed":0.1,"vibration":1,"rssi":21,"lat":26.89336},{"gpsTime":1503987951000,"status":1,"engine":0,"station":29652,"block":30383,"lng":112.557976,"acc":0,"termId":860337030798859,"course":0,"plugout":0,"lowVoltage":0,"voltage":886.4,"fix":1,"alt":216.1,"gpsSpeed":0.1,"vibration":1,"rssi":21,"lat":26.89336},{"gpsTime":1503987228000,"status":1,"engine":0,"station":29652,"block":30383,"lng":0,"acc":0,"termId":860337030798859,"course":0,"plugout":0,"lowVoltage":0,"voltage":886.4,"fix":0,"alt":0,"gpsSpeed":0,"vibration":1,"rssi":18,"lat":0}]
         * currentSize : 10
         * totalPages : 2
         */

        private int totalElements;
        private int currentPage;
        private int currentSize;
        private int totalPages;
        private List<AlarmsBean> alarms;

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public void setCurrentSize(int currentSize) {
            this.currentSize = currentSize;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public void setAlarms(List<AlarmsBean> alarms) {
            this.alarms = alarms;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public int getCurrentSize() {
            return currentSize;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public List<AlarmsBean> getAlarms() {
            return alarms;
        }

        public static class AlarmsBean {
            /**
             * gpsTime : 1503987951000
             * status : 1
             * engine : 0
             * station : 29652
             * block : 30383
             * lng : 112.557976
             * acc : 0
             * termId : 860337030798859
             * course : 0
             * plugout : 0
             * lowVoltage : 0
             * voltage : 871.4
             * fix : 1
             * alt : 216.1
             * gpsSpeed : 0.1
             * vibration : 1
             * rssi : 22
             * lat : 26.89336
             */

            private long gpsTime;
            private int status;
            private int engine;
            private int station;
            private int block;
            private double lng;
            private int acc;
            private long termId;
            private int course;
            private int plugout;
            private int lowVoltage;
            private double voltage;
            private int fix;
            private double alt;
            private double gpsSpeed;
            private int vibration;
            private int rssi;
            private double lat;

            public void setGpsTime(long gpsTime) {
                this.gpsTime = gpsTime;
            }

            public void setStatus(int status) {
                this.status = status;
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

            public void setAcc(int acc) {
                this.acc = acc;
            }

            public void setTermId(long termId) {
                this.termId = termId;
            }

            public void setCourse(int course) {
                this.course = course;
            }

            public void setPlugout(int plugout) {
                this.plugout = plugout;
            }

            public void setLowVoltage(int lowVoltage) {
                this.lowVoltage = lowVoltage;
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

            public void setVibration(int vibration) {
                this.vibration = vibration;
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

            public int getStatus() {
                return status;
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

            public int getAcc() {
                return acc;
            }

            public long getTermId() {
                return termId;
            }

            public int getCourse() {
                return course;
            }

            public int getPlugout() {
                return plugout;
            }

            public int getLowVoltage() {
                return lowVoltage;
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

            public int getVibration() {
                return vibration;
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
