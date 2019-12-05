package com.jike.cashocean.model;

/**
 * Created by Yey on 2018/1/9.
 */

public class OssBackEntity {

    /**
     * data : {"code":100,"datas":{"ali_token":"OSS LTAIfZqITEb6kMXM:eARU3n4uJ+E9bceOu1IQ6MEF3Fo="},"msg":"获取成功"}
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
         * datas : {"ali_token":"OSS LTAIfZqITEb6kMXM:eARU3n4uJ+E9bceOu1IQ6MEF3Fo="}
         * msg : 获取成功
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
             * ali_token : OSS LTAIfZqITEb6kMXM:eARU3n4uJ+E9bceOu1IQ6MEF3Fo=
             */

            private String ali_token;

            public String getAli_token() {
                return ali_token;
            }

            public void setAli_token(String ali_token) {
                this.ali_token = ali_token;
            }
        }
    }
}
