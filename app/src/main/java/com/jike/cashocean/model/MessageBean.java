package com.jike.cashocean.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MessageBean {

    /**
     * ret : 200
     * data : {"code":100,"msg":"Telah berhasil Konfirmasi","datas":{"code":100,"data":{
     * "ad_wheel_list":[{"msg":"1 menit yang lalu，82******53 berhasil dapat 5.4juta rupiah"},{
     * "msg":"2 menit yang lalu，84******20 berhasil dapat 4juta rupiah"},{"msg":"3 menit yang
     * lalu，86******14 berhasil dapat 1.6juta rupiah"},{"msg":"4 menit yang lalu，86******94
     * berhasil dapat 4.6juta rupiah"},{"msg":"5 menit yang lalu，80******69 berhasil dapat 3
     * .9juta rupiah"},{"msg":"6 menit yang lalu，82******68 berhasil dapat 4.9juta rupiah"},{"msg
     * ":"7 menit yang lalu，82******64 berhasil dapat 3.5juta rupiah"},{"msg":"8 menit yang
     * lalu，83******78 berhasil dapat 4.4juta rupiah"},{"msg":"9 menit yang lalu，89******98
     * berhasil dapat 4juta rupiah"},{"msg":"0 menit yang lalu，86******95 berhasil dapat 3.1juta
     * rupiah"},{"msg":"1 menit yang lalu，87******16 berhasil dapat 4.8juta rupiah"},{"msg":"2
     * menit yang lalu，87******41 berhasil dapat 1.7juta rupiah"},{"msg":"3 menit yang
     * lalu，82******55 berhasil dapat 5juta rupiah"},{"msg":"4 menit yang lalu，82******26
     * berhasil dapat 5juta rupiah"},{"msg":"5 menit yang lalu，89******10 berhasil dapat 4.6juta
     * rupiah"},{"msg":"6 menit yang lalu，80******90 berhasil dapat 3juta rupiah"},{"msg":"7
     * menit yang lalu，84******89 berhasil dapat 3.2juta rupiah"},{"msg":"8 menit yang
     * lalu，89******92 berhasil dapat 3.9juta rupiah"},{"msg":"9 menit yang lalu，84******25
     * berhasil dapat 2juta rupiah"},{"msg":"0 menit yang lalu，86******28 berhasil dapat 4.2juta
     * rupiah"},{"msg":"1 menit yang lalu，83******51 berhasil dapat 0.8juta rupiah"},{"msg":"2
     * menit yang lalu，87******36 berhasil dapat 4.7juta rupiah"},{"msg":"3 menit yang
     * lalu，87******41 berhasil dapat 5.6juta rupiah"},{"msg":"4 menit yang lalu，84******50
     * berhasil dapat 0.8juta rupiah"},{"msg":"5 menit yang lalu，88******77 berhasil dapat 5
     * .5juta rupiah"},{"msg":"6 menit yang lalu，87******12 berhasil dapat 4.6juta rupiah"},{"msg
     * ":"7 menit yang lalu，89******83 berhasil dapat 5.1juta rupiah"},{"msg":"8 menit yang
     * lalu，86******63 berhasil dapat 4.2juta rupiah"},{"msg":"9 menit yang lalu，85******85
     * berhasil dapat 2.4juta rupiah"},{"msg":"0 menit yang lalu，80******95 berhasil dapat 4
     * .5juta rupiah"},{"msg":"1 menit yang lalu，87******88 berhasil dapat 4.3juta rupiah"},{"msg
     * ":"2 menit yang lalu，80******84 berhasil dapat 1.8juta rupiah"},{"msg":"3 menit yang
     * lalu，86******11 berhasil dapat 4.3juta rupiah"},{"msg":"4 menit yang lalu，83******64
     * berhasil dapat 4.3juta rupiah"},{"msg":"5 menit yang lalu，82******36 berhasil dapat 1juta
     * rupiah"},{"msg":"6 menit yang lalu，88******58 berhasil dapat 1.9juta rupiah"},{"msg":"7
     * menit yang lalu，88******93 berhasil dapat 2juta rupiah"},{"msg":"8 menit yang
     * lalu，89******51 berhasil dapat 3.1juta rupiah"},{"msg":"9 menit yang lalu，80******68
     * berhasil dapat 1juta rupiah"},{"msg":"0 menit yang lalu，81******52 berhasil dapat 5.8juta
     * rupiah"},{"msg":"1 menit yang lalu，88******17 berhasil dapat 0.8juta rupiah"},{"msg":"2
     * menit yang lalu，86******21 berhasil dapat 5juta rupiah"},{"msg":"3 menit yang
     * lalu，86******75 berhasil dapat 6juta rupiah"},{"msg":"4 menit yang lalu，86******87
     * berhasil dapat 3juta rupiah"},{"msg":"5 menit yang lalu，80******70 berhasil dapat 3juta
     * rupiah"},{"msg":"6 menit yang lalu，84******78 berhasil dapat 4juta rupiah"},{"msg":"7
     * menit yang lalu，80******39 berhasil dapat 1.8juta rupiah"},{"msg":"8 menit yang
     * lalu，80******53 berhasil dapat 4juta rupiah"},{"msg":"9 menit yang lalu，86******17
     * berhasil dapat 1.1juta rupiah"},{"msg":"0 menit yang lalu，85******48 berhasil dapat 3
     * .2juta rupiah"}]}}}
     * msg :
     * debug : {"收到的sign:":"ebf2dd583fb8d043cf68e9a166618a72",
     * "用于生成sign的str:":"lang=ina&timestamp=1574418567&token=&version=2",
     * "服务器生成的sign:":"ebf2dd583fb8d043cf68e9a166618a72","stack":["[#1 - 0ms -
     * PHALAPI_INIT]/var/www/yndc/yn_1911221624/public/index.php(8)","[#2 - 0.1ms -
     * PHALAPI_RESPONSE]/var/www/yndc/yn_1911221624/vendor/phalapi/kernal/src/PhalApi.php(47)",
     * "[#3 - 0.6ms - PHALAPI_FINISH]/var/www/yndc/yn_1911221624/vendor/phalapi/kernal/src
     * /PhalApi.php(75)"],"sqls":[],"version":"2.7.0"}
     */

    private int ret;
    private DataBeanX data;
    private String msg;
    private DebugBean debug;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DebugBean getDebug() {
        return debug;
    }

    public void setDebug(DebugBean debug) {
        this.debug = debug;
    }

    public static class DataBeanX {
        /**
         * code : 100
         * msg : Telah berhasil Konfirmasi
         * datas : {"code":100,"data":{"ad_wheel_list":[{"msg":"1 menit yang lalu，82******53
         * berhasil dapat 5.4juta rupiah"},{"msg":"2 menit yang lalu，84******20 berhasil dapat
         * 4juta rupiah"},{"msg":"3 menit yang lalu，86******14 berhasil dapat 1.6juta rupiah"},{
         * "msg":"4 menit yang lalu，86******94 berhasil dapat 4.6juta rupiah"},{"msg":"5 menit
         * yang lalu，80******69 berhasil dapat 3.9juta rupiah"},{"msg":"6 menit yang
         * lalu，82******68 berhasil dapat 4.9juta rupiah"},{"msg":"7 menit yang lalu，82******64
         * berhasil dapat 3.5juta rupiah"},{"msg":"8 menit yang lalu，83******78 berhasil dapat 4
         * .4juta rupiah"},{"msg":"9 menit yang lalu，89******98 berhasil dapat 4juta rupiah"},{
         * "msg":"0 menit yang lalu，86******95 berhasil dapat 3.1juta rupiah"},{"msg":"1 menit
         * yang lalu，87******16 berhasil dapat 4.8juta rupiah"},{"msg":"2 menit yang
         * lalu，87******41 berhasil dapat 1.7juta rupiah"},{"msg":"3 menit yang lalu，82******55
         * berhasil dapat 5juta rupiah"},{"msg":"4 menit yang lalu，82******26 berhasil dapat
         * 5juta rupiah"},{"msg":"5 menit yang lalu，89******10 berhasil dapat 4.6juta rupiah"},{
         * "msg":"6 menit yang lalu，80******90 berhasil dapat 3juta rupiah"},{"msg":"7 menit yang
         * lalu，84******89 berhasil dapat 3.2juta rupiah"},{"msg":"8 menit yang lalu，89******92
         * berhasil dapat 3.9juta rupiah"},{"msg":"9 menit yang lalu，84******25 berhasil dapat
         * 2juta rupiah"},{"msg":"0 menit yang lalu，86******28 berhasil dapat 4.2juta rupiah"},{
         * "msg":"1 menit yang lalu，83******51 berhasil dapat 0.8juta rupiah"},{"msg":"2 menit
         * yang lalu，87******36 berhasil dapat 4.7juta rupiah"},{"msg":"3 menit yang
         * lalu，87******41 berhasil dapat 5.6juta rupiah"},{"msg":"4 menit yang lalu，84******50
         * berhasil dapat 0.8juta rupiah"},{"msg":"5 menit yang lalu，88******77 berhasil dapat 5
         * .5juta rupiah"},{"msg":"6 menit yang lalu，87******12 berhasil dapat 4.6juta rupiah"},{
         * "msg":"7 menit yang lalu，89******83 berhasil dapat 5.1juta rupiah"},{"msg":"8 menit
         * yang lalu，86******63 berhasil dapat 4.2juta rupiah"},{"msg":"9 menit yang
         * lalu，85******85 berhasil dapat 2.4juta rupiah"},{"msg":"0 menit yang lalu，80******95
         * berhasil dapat 4.5juta rupiah"},{"msg":"1 menit yang lalu，87******88 berhasil dapat 4
         * .3juta rupiah"},{"msg":"2 menit yang lalu，80******84 berhasil dapat 1.8juta rupiah"},{
         * "msg":"3 menit yang lalu，86******11 berhasil dapat 4.3juta rupiah"},{"msg":"4 menit
         * yang lalu，83******64 berhasil dapat 4.3juta rupiah"},{"msg":"5 menit yang
         * lalu，82******36 berhasil dapat 1juta rupiah"},{"msg":"6 menit yang lalu，88******58
         * berhasil dapat 1.9juta rupiah"},{"msg":"7 menit yang lalu，88******93 berhasil dapat
         * 2juta rupiah"},{"msg":"8 menit yang lalu，89******51 berhasil dapat 3.1juta rupiah"},{
         * "msg":"9 menit yang lalu，80******68 berhasil dapat 1juta rupiah"},{"msg":"0 menit yang
         * lalu，81******52 berhasil dapat 5.8juta rupiah"},{"msg":"1 menit yang lalu，88******17
         * berhasil dapat 0.8juta rupiah"},{"msg":"2 menit yang lalu，86******21 berhasil dapat
         * 5juta rupiah"},{"msg":"3 menit yang lalu，86******75 berhasil dapat 6juta rupiah"},{
         * "msg":"4 menit yang lalu，86******87 berhasil dapat 3juta rupiah"},{"msg":"5 menit yang
         * lalu，80******70 berhasil dapat 3juta rupiah"},{"msg":"6 menit yang lalu，84******78
         * berhasil dapat 4juta rupiah"},{"msg":"7 menit yang lalu，80******39 berhasil dapat 1
         * .8juta rupiah"},{"msg":"8 menit yang lalu，80******53 berhasil dapat 4juta rupiah"},{
         * "msg":"9 menit yang lalu，86******17 berhasil dapat 1.1juta rupiah"},{"msg":"0 menit
         * yang lalu，85******48 berhasil dapat 3.2juta rupiah"}]}}
         */

        private int code;
        private String msg;
        private DatasBean datas;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public DatasBean getDatas() {
            return datas;
        }

        public void setDatas(DatasBean datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * code : 100
             * data : {"ad_wheel_list":[{"msg":"1 menit yang lalu，82******53 berhasil dapat 5
             * .4juta rupiah"},{"msg":"2 menit yang lalu，84******20 berhasil dapat 4juta rupiah"}
             * ,{"msg":"3 menit yang lalu，86******14 berhasil dapat 1.6juta rupiah"},{"msg":"4
             * menit yang lalu，86******94 berhasil dapat 4.6juta rupiah"},{"msg":"5 menit yang
             * lalu，80******69 berhasil dapat 3.9juta rupiah"},{"msg":"6 menit yang
             * lalu，82******68 berhasil dapat 4.9juta rupiah"},{"msg":"7 menit yang
             * lalu，82******64 berhasil dapat 3.5juta rupiah"},{"msg":"8 menit yang
             * lalu，83******78 berhasil dapat 4.4juta rupiah"},{"msg":"9 menit yang
             * lalu，89******98 berhasil dapat 4juta rupiah"},{"msg":"0 menit yang lalu，86******95
             * berhasil dapat 3.1juta rupiah"},{"msg":"1 menit yang lalu，87******16 berhasil
             * dapat 4.8juta rupiah"},{"msg":"2 menit yang lalu，87******41 berhasil dapat 1.7juta
             * rupiah"},{"msg":"3 menit yang lalu，82******55 berhasil dapat 5juta rupiah"},{"msg
             * ":"4 menit yang lalu，82******26 berhasil dapat 5juta rupiah"},{"msg":"5 menit yang
             * lalu，89******10 berhasil dapat 4.6juta rupiah"},{"msg":"6 menit yang
             * lalu，80******90 berhasil dapat 3juta rupiah"},{"msg":"7 menit yang lalu，84******89
             * berhasil dapat 3.2juta rupiah"},{"msg":"8 menit yang lalu，89******92 berhasil
             * dapat 3.9juta rupiah"},{"msg":"9 menit yang lalu，84******25 berhasil dapat 2juta
             * rupiah"},{"msg":"0 menit yang lalu，86******28 berhasil dapat 4.2juta rupiah"},{
             * "msg":"1 menit yang lalu，83******51 berhasil dapat 0.8juta rupiah"},{"msg":"2
             * menit yang lalu，87******36 berhasil dapat 4.7juta rupiah"},{"msg":"3 menit yang
             * lalu，87******41 berhasil dapat 5.6juta rupiah"},{"msg":"4 menit yang
             * lalu，84******50 berhasil dapat 0.8juta rupiah"},{"msg":"5 menit yang
             * lalu，88******77 berhasil dapat 5.5juta rupiah"},{"msg":"6 menit yang
             * lalu，87******12 berhasil dapat 4.6juta rupiah"},{"msg":"7 menit yang
             * lalu，89******83 berhasil dapat 5.1juta rupiah"},{"msg":"8 menit yang
             * lalu，86******63 berhasil dapat 4.2juta rupiah"},{"msg":"9 menit yang
             * lalu，85******85 berhasil dapat 2.4juta rupiah"},{"msg":"0 menit yang
             * lalu，80******95 berhasil dapat 4.5juta rupiah"},{"msg":"1 menit yang
             * lalu，87******88 berhasil dapat 4.3juta rupiah"},{"msg":"2 menit yang
             * lalu，80******84 berhasil dapat 1.8juta rupiah"},{"msg":"3 menit yang
             * lalu，86******11 berhasil dapat 4.3juta rupiah"},{"msg":"4 menit yang
             * lalu，83******64 berhasil dapat 4.3juta rupiah"},{"msg":"5 menit yang
             * lalu，82******36 berhasil dapat 1juta rupiah"},{"msg":"6 menit yang lalu，88******58
             * berhasil dapat 1.9juta rupiah"},{"msg":"7 menit yang lalu，88******93 berhasil
             * dapat 2juta rupiah"},{"msg":"8 menit yang lalu，89******51 berhasil dapat 3.1juta
             * rupiah"},{"msg":"9 menit yang lalu，80******68 berhasil dapat 1juta rupiah"},{"msg
             * ":"0 menit yang lalu，81******52 berhasil dapat 5.8juta rupiah"},{"msg":"1 menit
             * yang lalu，88******17 berhasil dapat 0.8juta rupiah"},{"msg":"2 menit yang
             * lalu，86******21 berhasil dapat 5juta rupiah"},{"msg":"3 menit yang lalu，86******75
             * berhasil dapat 6juta rupiah"},{"msg":"4 menit yang lalu，86******87 berhasil dapat
             * 3juta rupiah"},{"msg":"5 menit yang lalu，80******70 berhasil dapat 3juta rupiah"},
             * {"msg":"6 menit yang lalu，84******78 berhasil dapat 4juta rupiah"},{"msg":"7 menit
             * yang lalu，80******39 berhasil dapat 1.8juta rupiah"},{"msg":"8 menit yang
             * lalu，80******53 berhasil dapat 4juta rupiah"},{"msg":"9 menit yang lalu，86******17
             * berhasil dapat 1.1juta rupiah"},{"msg":"0 menit yang lalu，85******48 berhasil dapat 3.2juta rupiah"}]}
             */

            private int code;
            private DataBean data;

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }

            public DataBean getData() {
                return data;
            }

            public void setData(DataBean data) {
                this.data = data;
            }

            public static class DataBean {
                private List<AdWheelListBean> ad_wheel_list;

                public List<AdWheelListBean> getAd_wheel_list() {
                    return ad_wheel_list;
                }

                public void setAd_wheel_list(List<AdWheelListBean> ad_wheel_list) {
                    this.ad_wheel_list = ad_wheel_list;
                }

                public static class AdWheelListBean {
                    /**
                     * msg : 1 menit yang lalu，82******53 berhasil dapat 5.4juta rupiah
                     */

                    private String msg;

                    public String getMsg() {
                        return msg;
                    }

                    public void setMsg(String msg) {
                        this.msg = msg;
                    }
                }
            }
        }
    }

    public static class DebugBean {
        @SerializedName("收到的sign:")
        private String _$Sign84; // FIXME check this code
        @SerializedName("用于生成sign的str:")
        private String _$SignStr76; // FIXME check this code
        @SerializedName("服务器生成的sign:")
        private String _$Sign121; // FIXME check this code
        private String version;
        private List<String> stack;
        private List<?> sqls;

        public String get_$Sign84() {
            return _$Sign84;
        }

        public void set_$Sign84(String _$Sign84) {
            this._$Sign84 = _$Sign84;
        }

        public String get_$SignStr76() {
            return _$SignStr76;
        }

        public void set_$SignStr76(String _$SignStr76) {
            this._$SignStr76 = _$SignStr76;
        }

        public String get_$Sign121() {
            return _$Sign121;
        }

        public void set_$Sign121(String _$Sign121) {
            this._$Sign121 = _$Sign121;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public List<String> getStack() {
            return stack;
        }

        public void setStack(List<String> stack) {
            this.stack = stack;
        }

        public List<?> getSqls() {
            return sqls;
        }

        public void setSqls(List<?> sqls) {
            this.sqls = sqls;
        }
    }
}
