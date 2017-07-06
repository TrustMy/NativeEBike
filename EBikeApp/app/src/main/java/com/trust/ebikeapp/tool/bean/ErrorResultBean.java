package com.trust.ebikeapp.tool.bean;

/**
 * Created by Trust on 2017/5/16.
 */

public class ErrorResultBean {
    /**
     * status : false
     * err : 请重新登录
     * code : 1
     */

    private boolean status;
    private String err;
    private int code;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean getStatus() {
        return status;
    }

    public String getErr() {
        return err;
    }

    public int getCode() {
        return code;
    }

//    /**
//     * status : false
//     * err : 设备不在线
//     */
//
//    private boolean status;
//    private String err;
//
//    public void setStatus(boolean status) {
//        this.status = status;
//    }
//
//    public void setErr(String err) {
//        this.err = err;
//    }
//
//    public boolean getStatus() {
//        return status;
//    }
//
//    public String getErr() {
//        return err;
//    }



}
