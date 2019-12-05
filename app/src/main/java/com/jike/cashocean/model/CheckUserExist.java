package com.jike.cashocean.model;

/**
 * Created by Yey on 2018/1/9.
 */

public class CheckUserExist {

    /**
     * data : {"code":100,"datas":{"is_register":1},"msg":""}
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
         * datas : {"is_register":1}
         * msg :
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
             * is_register : 1
             */

            private String is_register;

            public String getIs_register() {
                return is_register;
            }

            public void setIs_register(String is_register) {
                this.is_register = is_register;
            }
        }
    }
}
