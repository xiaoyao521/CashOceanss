package com.jike.cashocean.model;

/**
 * Created by Yey on 2018/1/9.
 */

public class ClickAppListEntity {

    /**
     * data : {"code":100,"datas":{"click_url":"http://app.appsflyer.com/这是一个app?pid=cashmall_int&android_id=254&advertising_id=24568asdfs"},"msg":"获取成功"}
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
         * datas : {"click_url":"http://app.appsflyer.com/这是一个app?pid=cashmall_int&android_id=254&advertising_id=24568asdfs"}
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
             * click_url : http://app.appsflyer.com/这是一个app?pid=cashmall_int&android_id=254&advertising_id=24568asdfs
             */

            private String click_url;
            private String package_name;
            private int jump_type;

            public String getPackage_name() {
                return package_name;
            }

            public void setPackage_name(String package_name) {
                this.package_name = package_name;
            }

            public String getClick_url() {
                return click_url;
            }

            public void setClick_url(String click_url) {
                this.click_url = click_url;
            }

            public int getJump_type() {
                return jump_type;
            }

            public void setJump_type(int jump_type) {
                this.jump_type = jump_type;
            }
        }
    }
}
