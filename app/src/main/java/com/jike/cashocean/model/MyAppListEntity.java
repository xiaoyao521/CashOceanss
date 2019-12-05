package com.jike.cashocean.model;

import java.util.List;

/**
 * Created by Yey on 2018/1/9.
 */

public class MyAppListEntity {

    /**
     * data : {"code":"100","datas":{"list":[{"advertising_id":"1","android_id":"11","app_name":"","appid":"1","created_at":"2019-08-08 17:40:59","id":"33","loan_max_money":"","updated_at":"2019-08-08 17:40:59","user_id":"31"},{"advertising_id":"1231231","android_id":"123123","app_name":"","appid":"1","created_at":"2019-08-08 17:37:10","id":"32","loan_max_money":"","updated_at":"2019-08-08 17:37:10","user_id":"31"},{"advertising_id":"1231231","android_id":"123123","app_name":"","appid":"1","created_at":"2019-08-06 23:09:10","id":"31","loan_max_money":"","updated_at":"2019-08-06 23:09:10","user_id":"31"},{"advertising_id":"1231231","android_id":"123123","app_name":"","appid":"1","created_at":"2019-08-06 23:08:13","id":"26","loan_max_money":"","updated_at":"2019-08-06 23:08:13","user_id":"31"},{"advertising_id":"1231231","android_id":"123123","app_name":"","appid":"1","created_at":"2019-08-06 23:08:13","id":"27","loan_max_money":"","updated_at":"2019-08-06 23:08:13","user_id":"31"},{"advertising_id":"1231231","android_id":"123123","app_name":"","appid":"1","created_at":"2019-08-06 23:08:13","id":"28","loan_max_money":"","updated_at":"2019-08-06 23:08:13","user_id":"31"},{"advertising_id":"1231231","android_id":"123123","app_name":"","appid":"1","created_at":"2019-08-06 23:08:13","id":"29","loan_max_money":"","updated_at":"2019-08-06 23:08:13","user_id":"31"},{"advertising_id":"1231231","android_id":"123123","app_name":"","appid":"1","created_at":"2019-08-06 23:08:13","id":"30","loan_max_money":"","updated_at":"2019-08-06 23:08:13","user_id":"31"},{"advertising_id":"1231231","android_id":"123123","app_name":"","appid":"1","created_at":"2019-08-06 23:08:12","id":"22","loan_max_money":"","updated_at":"2019-08-06 23:08:12","user_id":"31"},{"advertising_id":"1231231","android_id":"123123","app_name":"","appid":"1","created_at":"2019-08-06 23:08:12","id":"23","loan_max_money":"","updated_at":"2019-08-06 23:08:12","user_id":"31"}]},"msg":"获取成功"}
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
         * datas : {"list":[{"advertising_id":"1","android_id":"11","app_name":"","appid":"1","created_at":"2019-08-08 17:40:59","id":"33","loan_max_money":"","updated_at":"2019-08-08 17:40:59","user_id":"31"},{"advertising_id":"1231231","android_id":"123123","app_name":"","appid":"1","created_at":"2019-08-08 17:37:10","id":"32","loan_max_money":"","updated_at":"2019-08-08 17:37:10","user_id":"31"},{"advertising_id":"1231231","android_id":"123123","app_name":"","appid":"1","created_at":"2019-08-06 23:09:10","id":"31","loan_max_money":"","updated_at":"2019-08-06 23:09:10","user_id":"31"},{"advertising_id":"1231231","android_id":"123123","app_name":"","appid":"1","created_at":"2019-08-06 23:08:13","id":"26","loan_max_money":"","updated_at":"2019-08-06 23:08:13","user_id":"31"},{"advertising_id":"1231231","android_id":"123123","app_name":"","appid":"1","created_at":"2019-08-06 23:08:13","id":"27","loan_max_money":"","updated_at":"2019-08-06 23:08:13","user_id":"31"},{"advertising_id":"1231231","android_id":"123123","app_name":"","appid":"1","created_at":"2019-08-06 23:08:13","id":"28","loan_max_money":"","updated_at":"2019-08-06 23:08:13","user_id":"31"},{"advertising_id":"1231231","android_id":"123123","app_name":"","appid":"1","created_at":"2019-08-06 23:08:13","id":"29","loan_max_money":"","updated_at":"2019-08-06 23:08:13","user_id":"31"},{"advertising_id":"1231231","android_id":"123123","app_name":"","appid":"1","created_at":"2019-08-06 23:08:13","id":"30","loan_max_money":"","updated_at":"2019-08-06 23:08:13","user_id":"31"},{"advertising_id":"1231231","android_id":"123123","app_name":"","appid":"1","created_at":"2019-08-06 23:08:12","id":"22","loan_max_money":"","updated_at":"2019-08-06 23:08:12","user_id":"31"},{"advertising_id":"1231231","android_id":"123123","app_name":"","appid":"1","created_at":"2019-08-06 23:08:12","id":"23","loan_max_money":"","updated_at":"2019-08-06 23:08:12","user_id":"31"}]}
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
            private List<ListBean> list;

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * advertising_id : 1
                 * android_id : 11
                 * app_name :
                 * appid : 1
                 * created_at : 2019-08-08 17:40:59
                 * id : 33
                 * loan_max_money :
                 * updated_at : 2019-08-08 17:40:59
                 * user_id : 31
                 */

                private String advertising_id;
                private String android_id;
                private String app_name;
                private String appid;
                private String created_at;
                private String id;
                private String loan_max_money;
                private String updated_at;
                private String user_id;

                public String getAdvertising_id() {
                    return advertising_id;
                }

                public void setAdvertising_id(String advertising_id) {
                    this.advertising_id = advertising_id;
                }

                public String getAndroid_id() {
                    return android_id;
                }

                public void setAndroid_id(String android_id) {
                    this.android_id = android_id;
                }

                public String getApp_name() {
                    return app_name;
                }

                public void setApp_name(String app_name) {
                    this.app_name = app_name;
                }

                public String getAppid() {
                    return appid;
                }

                public void setAppid(String appid) {
                    this.appid = appid;
                }

                public String getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(String created_at) {
                    this.created_at = created_at;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getLoan_max_money() {
                    return loan_max_money;
                }

                public void setLoan_max_money(String loan_max_money) {
                    this.loan_max_money = loan_max_money;
                }

                public String getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(String updated_at) {
                    this.updated_at = updated_at;
                }

                public String getUser_id() {
                    return user_id;
                }

                public void setUser_id(String user_id) {
                    this.user_id = user_id;
                }
            }
        }
    }
}
