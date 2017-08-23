package com.phonegap.ebike.tool.bean;

/**
 * Created by Trust on 2017/6/20.
 */

public class CarStatusBean {

    /**
     * content : {"voltage":0,"plugOutAlarm":"0","description":"车况查询完成","buzzerStatus":0,"cmdType":33,"fault":{},"lockStatus":0,"voltagePercent":0}
     * status : true
     * totleAlarm : 1
     */

    private ContentBean content;
    private boolean status;
    private int totleAlarm;

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setTotleAlarm(int totleAlarm) {
        this.totleAlarm = totleAlarm;
    }

    public ContentBean getContent() {
        return content;
    }

    public boolean getStatus() {
        return status;
    }

    public int getTotleAlarm() {
        return totleAlarm;
    }

    public static class ContentBean {
        /**
         * voltage : 0
         * plugOutAlarm : 0
         * description : 车况查询完成
         * buzzerStatus : 0
         * cmdType : 33
         * fault : {}
         * lockStatus : 0
         * voltagePercent : 0
         */

        private int voltage;
        private String plugOutAlarm;
        private String description;
        private int buzzerStatus;
        private int cmdType;
        private int lockStatus;
        private int voltagePercent;

        public void setVoltage(int voltage) {
            this.voltage = voltage;
        }

        public void setPlugOutAlarm(String plugOutAlarm) {
            this.plugOutAlarm = plugOutAlarm;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setBuzzerStatus(int buzzerStatus) {
            this.buzzerStatus = buzzerStatus;
        }

        public void setCmdType(int cmdType) {
            this.cmdType = cmdType;
        }

        public void setLockStatus(int lockStatus) {
            this.lockStatus = lockStatus;
        }

        public void setVoltagePercent(int voltagePercent) {
            this.voltagePercent = voltagePercent;
        }

        public int getVoltage() {
            return voltage;
        }

        public String getPlugOutAlarm() {
            return plugOutAlarm;
        }

        public String getDescription() {
            return description;
        }

        public int getBuzzerStatus() {
            return buzzerStatus;
        }

        public int getCmdType() {
            return cmdType;
        }

        public int getLockStatus() {
            return lockStatus;
        }

        public int getVoltagePercent() {
            return voltagePercent;
        }
    }

    public static class FaultBean {
        /**
         * fault1 : 1
         * fault2 : 1
         * fault3 : 1
         * fault4 : 1
         * fault5 : 1
         */

        private int fault1;
        private int fault2;
        private int fault3;
        private int fault4;
        private int fault5;

        public void setFault1(int fault1) {
            this.fault1 = fault1;
        }

        public void setFault2(int fault2) {
            this.fault2 = fault2;
        }

        public void setFault3(int fault3) {
            this.fault3 = fault3;
        }

        public void setFault4(int fault4) {
            this.fault4 = fault4;
        }

        public void setFault5(int fault5) {
            this.fault5 = fault5;
        }

        public int getFault1() {
            return fault1;
        }

        public int getFault2() {
            return fault2;
        }

        public int getFault3() {
            return fault3;
        }

        public int getFault4() {
            return fault4;
        }

        public int getFault5() {
            return fault5;
        }
    }


}
