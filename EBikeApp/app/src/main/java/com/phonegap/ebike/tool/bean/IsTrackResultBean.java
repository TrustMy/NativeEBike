package com.phonegap.ebike.tool.bean;

/**
 * Created by Trust on 2017/5/16.
 */

public class IsTrackResultBean {
    /**
     * status : true
     * content : {"result":0,"cmdType":38}
     */

    private boolean status;
    private ContentBean content;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public boolean getStatus() {
        return status;
    }

    public ContentBean getContent() {
        return content;
    }

    public static class ContentBean {
        /**
         * result : 0
         * cmdType : 38
         */

        private int result;
        private int cmdType;

        public void setResult(int result) {
            this.result = result;
        }

        public void setCmdType(int cmdType) {
            this.cmdType = cmdType;
        }

        public int getResult() {
            return result;
        }

        public int getCmdType() {
            return cmdType;
        }
    }
}
