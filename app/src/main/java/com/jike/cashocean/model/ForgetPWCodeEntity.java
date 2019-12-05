package com.jike.cashocean.model;

/**
 * Created by Yey on 2018/5/5.
 */

public class ForgetPWCodeEntity {

    /**
     * data : {"code":100,"datas":{"code":545136,"end_time":1565171276},"msg":"发送成功"}
     * msg :
     * ret : 200
     */

    private DataBean data;
    private String msg;
    private int ret;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public static class DataBean {
        /**
         * code : 100
         * datas : {"code":545136,"end_time":1565171276}
         * msg : 发送成功
         */

        private int code;
        private DatasBean datas;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public DatasBean getDatas() {
            return datas;
        }

        public void setDatas(DatasBean datas) {
            this.datas = datas;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public static class DatasBean {
            /**
             * code : 545136
             * end_time : 1565171276
             */

            private int code;
            private int end_time;

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }

            public int getEnd_time() {
                return end_time;
            }

            public void setEnd_time(int end_time) {
                this.end_time = end_time;
            }
        }
    }
}
