package com.trust.ebikeapp.tool.bean;

/**
 * Created by Trust on 2017/6/26.
 */

public class SelfTestBean {


    /**
     * content : {"voltage":0,"description":"自检完成","buzzerStatus":0,"cmdType":39,"fault":{"fault1":1,"fault2":1,"fault3":1,"fault4":1,"fault5":1},"lockStatus":1,"voltagePercent":0}
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
         * voltage : 0
         * description : 自检完成
         * buzzerStatus : 0
         * cmdType : 39
         * fault : {"fault1":1,"fault2":1,"fault3":1,"fault4":1,"fault5":1}
         * lockStatus : 1
         * voltagePercent : 0
         */

        private int voltage;
        private String description;
        private int buzzerStatus;
        private int cmdType;
        private FaultBean fault;
        private int lockStatus;
        private int voltagePercent;

        public void setVoltage(int voltage) {
            this.voltage = voltage;
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

        public void setFault(FaultBean fault) {
            this.fault = fault;
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

        public String getDescription() {
            return description;
        }

        public int getBuzzerStatus() {
            return buzzerStatus;
        }

        public int getCmdType() {
            return cmdType;
        }

        public FaultBean getFault() {
            return fault;
        }

        public int getLockStatus() {
            return lockStatus;
        }

        public int getVoltagePercent() {
            return voltagePercent;
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
}
