package com.jike.cashocean.model;

/**
 * Created by Yey on 2018/1/9.
 */

public class HomeBannerEntity {

    @Override
    public String toString() {
        return "CheckUserExist{" +
                "flag='" + flag + '\'' +
                ", msg='" + msg + '\'' +
                ", cnmsg='" + cnmsg + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * flag : Success
     * msg : register first
     * cnmsg : 去注册
     * code : 9001
     */



    private String flag;
    private String msg;
    private String cnmsg;
    private String code;
    private PhoneNum data;

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

    public PhoneNum getData() {
        return data;
    }

    public void setData(PhoneNum data) {
        this.data = data;
    }

    public static class PhoneNum {
        /**
         * mobile : 8613001222372
         */

        private String mobile;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
