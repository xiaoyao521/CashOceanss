package com.jike.cashocean.model;

import java.util.List;

public class HomeApplistDataEntity {

    /**
     * ret : 200
     * data : {"code":100,"app_list":[{"id":1,"logo_img":"","app_name":"这是一个app","stars":4.5,"loan_min_money":100000,"loan_min_rate":0.5,"loan_max_rate":0.6,"loan_min_day":10,"loan_max_day":10,"desc_content":"这是一串简介"}]}
     * msg :
     */

    private int ret;
    private DataBean data;
    private String msg;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

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

    public static class DataBean {
        /**
         * code : 100
         * app_list : [{"id":1,"logo_img":"","app_name":"这是一个app","stars":4.5,"loan_min_money":100000,"loan_min_rate":0.5,"loan_max_rate":0.6,"loan_min_day":10,"loan_max_day":10,"desc_content":"这是一串简介"}]
         */

        private int code;
        private List<AppListBean> app_list;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public List<AppListBean> getApp_list() {
            return app_list;
        }

        public void setApp_list(List<AppListBean> app_list) {
            this.app_list = app_list;
        }

        public static class AppListBean {
            /**
             * id : 1
             * logo_img :
             * app_name : 这是一个app
             * stars : 4.5
             * loan_min_money : 100000
             * loan_min_rate : 0.5
             * loan_max_rate : 0.6
             * loan_min_day : 10
             * loan_max_day : 10
             * desc_content : 这是一串简介
             */

            private int id;
            private String logo_img;
            private String app_name;
            private double stars;
            private int loan_min_money;
            private double loan_min_rate;
            private double loan_max_rate;
            private int loan_min_day;
            private int loan_max_day;
            private String desc_content;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLogo_img() {
                return logo_img;
            }

            public void setLogo_img(String logo_img) {
                this.logo_img = logo_img;
            }

            public String getApp_name() {
                return app_name;
            }

            public void setApp_name(String app_name) {
                this.app_name = app_name;
            }

            public double getStars() {
                return stars;
            }

            public void setStars(double stars) {
                this.stars = stars;
            }

            public int getLoan_min_money() {
                return loan_min_money;
            }

            public void setLoan_min_money(int loan_min_money) {
                this.loan_min_money = loan_min_money;
            }

            public double getLoan_min_rate() {
                return loan_min_rate;
            }

            public void setLoan_min_rate(double loan_min_rate) {
                this.loan_min_rate = loan_min_rate;
            }

            public double getLoan_max_rate() {
                return loan_max_rate;
            }

            public void setLoan_max_rate(double loan_max_rate) {
                this.loan_max_rate = loan_max_rate;
            }

            public int getLoan_min_day() {
                return loan_min_day;
            }

            public void setLoan_min_day(int loan_min_day) {
                this.loan_min_day = loan_min_day;
            }

            public int getLoan_max_day() {
                return loan_max_day;
            }

            public void setLoan_max_day(int loan_max_day) {
                this.loan_max_day = loan_max_day;
            }

            public String getDesc_content() {
                return desc_content;
            }

            public void setDesc_content(String desc_content) {
                this.desc_content = desc_content;
            }
        }
    }
}
