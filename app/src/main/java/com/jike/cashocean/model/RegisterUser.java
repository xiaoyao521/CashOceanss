package com.jike.cashocean.model;

/**
 * Created by Yey on 2018/1/9.
 */

public class RegisterUser {
    /**
     * cnmsg : 注册成功
     * code : 200
     * data : {"client_tel":"9953671240"}
     * flag : Success
     * msg : Register success
     */

    private String cnmsg;
    private String code;
    private DataBean data;
    private String flag;
    private String msg;

    public String getCnmsg() {
        return cnmsg;
    }

    public void setCnmsg(String cnmsg) {
        this.cnmsg = cnmsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * client_tel : 9953671240
         */

        private String client_tel;

        public String getClient_tel() {
            return client_tel;
        }

        public void setClient_tel(String client_tel) {
            this.client_tel = client_tel;
        }
    }


//    /**
//     * cnmsg : 注册成功
//     * code : 1000
//     * data :
//     * flag : Success
//     * msg : Terdaftar dengan sukses
//     */
//
//    private String cnmsg;
//    private String code;
//    private String data;
//    private String flag;
//    private String msg;
//
//    public String getCnmsg() {
//        return cnmsg;
//    }
//
//    public void setCnmsg(String cnmsg) {
//        this.cnmsg = cnmsg;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getData() {
//        return data;
//    }
//
//    public void setData(String data) {
//        this.data = data;
//    }
//
//    public String getFlag() {
//        return flag;
//    }
//
//    public void setFlag(String flag) {
//        this.flag = flag;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
}
