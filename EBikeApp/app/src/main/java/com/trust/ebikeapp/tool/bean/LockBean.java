package com.trust.ebikeapp.tool.bean;

/**
 * Created by Trust on 2017/6/19.
 */

public class LockBean {


    /**
     * content : {"result":0,"description":"解防成功","cmdType":35}
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
         * result : 0
         * description : 解防成功
         * cmdType : 35
         */

        private int result;
        private String description;
        private int cmdType;

        public void setResult(int result) {
            this.result = result;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setCmdType(int cmdType) {
            this.cmdType = cmdType;
        }

        public int getResult() {
            return result;
        }

        public String getDescription() {
            return description;
        }

        public int getCmdType() {
            return cmdType;
        }
    }
}
