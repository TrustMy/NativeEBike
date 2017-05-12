package com.trust.ebikeapp.tool.bean;

/**
 * Created by Trust on 2017/5/11.
 */
public class LoginResultBean {

    /**
     * content : {"model":"","pushId":"b0d3766c1708c44090415f4e0bc5ce2e7f42d64346385561751f8bfd077ee728","speed":"","email":"","nickName":"","serialNumber":"","factoryNumber":"","function":"","termId":860337030432061}
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
         * model :
         * pushId : b0d3766c1708c44090415f4e0bc5ce2e7f42d64346385561751f8bfd077ee728
         * speed :
         * email :
         * nickName :
         * serialNumber :
         * factoryNumber :
         * function :
         * termId : 860337030432061
         */

        private String model;
        private String pushId;
        private String speed;
        private String email;
        private String nickName;
        private String serialNumber;
        private String factoryNumber;
        private String function;
        private long termId;

        public void setModel(String model) {
            this.model = model;
        }

        public void setPushId(String pushId) {
            this.pushId = pushId;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public void setSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
        }

        public void setFactoryNumber(String factoryNumber) {
            this.factoryNumber = factoryNumber;
        }

        public void setFunction(String function) {
            this.function = function;
        }

        public void setTermId(long termId) {
            this.termId = termId;
        }

        public String getModel() {
            return model;
        }

        public String getPushId() {
            return pushId;
        }

        public String getSpeed() {
            return speed;
        }

        public String getEmail() {
            return email;
        }

        public String getNickName() {
            return nickName;
        }

        public String getSerialNumber() {
            return serialNumber;
        }

        public String getFactoryNumber() {
            return factoryNumber;
        }

        public String getFunction() {
            return function;
        }

        public long getTermId() {
            return termId;
        }
    }
}
