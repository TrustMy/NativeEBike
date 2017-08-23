package com.phonegap.ebike.tool.bean;

/**
 * Created by Trust on 2017/5/17.
 */

public class GetCheckNumBean {

    /**
     * status : true
     * code : 888888
     */

    private boolean status;
    private int code;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }
}
