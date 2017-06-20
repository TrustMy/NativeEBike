package com.trust.ebikeapp.tool.bean;

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
}
