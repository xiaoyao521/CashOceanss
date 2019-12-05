package com.jike.cashocean.model;

import java.util.List;

public class HomeListData {

    /**
     * data : {"code":100,"datas":{"app_list":[{"app_name":"这是一个app1","desc_content":"这是一个app","id":"1","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一2个app","desc_content":"这是一个app","id":"2","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一4个app","desc_content":"这是一个app","id":"3","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一6个app","desc_content":"这是一个app","id":"4","loan_max_day":"999","loan_max_money":"6","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一3个app","desc_content":"这是一个app","id":"5","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一5个app","desc_content":"这是一个app","id":"6","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一个app","desc_content":"这是一个app","id":"7","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一个app","desc_content":"这是一个app","id":"8","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一个app","desc_content":"这是一个app","id":"9","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一个app","desc_content":"这是一个app","id":"10","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"}],"banner_list":[{"banner_image":"http://img.cashmall24.com/4","banner_type":"2","banner_value":"3"},{"banner_image":"http://img.cashmall24.com/4","banner_type":"2","banner_value":"3"},{"banner_image":"http://img.cashmall24.com/4","banner_type":"2","banner_value":"3"},{"banner_image":"http://img.cashmall24.com/4","banner_type":"2","banner_value":"3"},{"banner_image":"http://img.cashmall24.com/4","banner_type":"2","banner_value":"3"}],"code":100},"msg":"获取成功"}
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
         * datas : {"app_list":[{"app_name":"这是一个app1","desc_content":"这是一个app","id":"1","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一2个app","desc_content":"这是一个app","id":"2","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一4个app","desc_content":"这是一个app","id":"3","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一6个app","desc_content":"这是一个app","id":"4","loan_max_day":"999","loan_max_money":"6","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一3个app","desc_content":"这是一个app","id":"5","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一5个app","desc_content":"这是一个app","id":"6","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一个app","desc_content":"这是一个app","id":"7","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一个app","desc_content":"这是一个app","id":"8","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一个app","desc_content":"这是一个app","id":"9","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一个app","desc_content":"这是一个app","id":"10","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"}],"banner_list":[{"banner_image":"http://img.cashmall24.com/4","banner_type":"2","banner_value":"3"},{"banner_image":"http://img.cashmall24.com/4","banner_type":"2","banner_value":"3"},{"banner_image":"http://img.cashmall24.com/4","banner_type":"2","banner_value":"3"},{"banner_image":"http://img.cashmall24.com/4","banner_type":"2","banner_value":"3"},{"banner_image":"http://img.cashmall24.com/4","banner_type":"2","banner_value":"3"}],"code":100}
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
             * app_list : [{"app_name":"这是一个app1","desc_content":"这是一个app","id":"1","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一2个app","desc_content":"这是一个app","id":"2","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一4个app","desc_content":"这是一个app","id":"3","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一6个app","desc_content":"这是一个app","id":"4","loan_max_day":"999","loan_max_money":"6","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一3个app","desc_content":"这是一个app","id":"5","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一5个app","desc_content":"这是一个app","id":"6","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一个app","desc_content":"这是一个app","id":"7","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一个app","desc_content":"这是一个app","id":"8","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一个app","desc_content":"这是一个app","id":"9","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"},{"app_name":"这是一个app","desc_content":"这是一个app","id":"10","loan_max_day":"999","loan_max_money":"5","loan_max_rate":"2.00","loan_min_day":"1","loan_min_money":"1","loan_min_rate":"1.00","logo_img":"http://img.cashmall24.com/","stars":"1.00"}]
             * banner_list : [{"banner_image":"http://img.cashmall24.com/4","banner_type":"2","banner_value":"3"},{"banner_image":"http://img.cashmall24.com/4","banner_type":"2","banner_value":"3"},{"banner_image":"http://img.cashmall24.com/4","banner_type":"2","banner_value":"3"},{"banner_image":"http://img.cashmall24.com/4","banner_type":"2","banner_value":"3"},{"banner_image":"http://img.cashmall24.com/4","banner_type":"2","banner_value":"3"}]
             * code : 100
             */

            private int code;
            private List<AppListBean> app_list;
            private List<BannerListBean> banner_list;

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

            public List<BannerListBean> getBanner_list() {
                return banner_list;
            }

            public void setBanner_list(List<BannerListBean> banner_list) {
                this.banner_list = banner_list;
            }

            public static class AppListBean {
                public AppListBean(boolean isShow) {
                    this.isShow = isShow;
                }

                public boolean isShow() {
                    return isShow;
                }

                public void setShow(boolean show) {
                    isShow = show;
                }

                private boolean isShow;
                /**
                 * app_name : 这是一个app1
                 * desc_content : 这是一个app
                 * id : 1
                 * loan_max_day : 999
                 * loan_max_money : 5
                 * loan_max_rate : 2.00
                 * loan_min_day : 1
                 * loan_min_money : 1
                 * loan_min_rate : 1.00
                 * logo_img : http://img.cashmall24.com/
                 * stars : 1.00
                 */
                private String app_name;
                private String desc_content;
                private String id;
                private String loan_max_day;
                private String loan_max_money;
                private String loan_max_rate;
                private String loan_min_day;
                private String loan_min_money;
                private String loan_min_rate;
                private String logo_img;
                private float stars;
                private int is_hot;


                public int getIs_hot() {
                    return is_hot;
                }

                public void setIs_hot(int is_hot) {
                    this.is_hot = is_hot;
                }

                public String getApp_name() {
                    return app_name;
                }

                public void setApp_name(String app_name) {
                    this.app_name = app_name;
                }

                public String getDesc_content() {
                    return desc_content;
                }

                public void setDesc_content(String desc_content) {
                    this.desc_content = desc_content;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getLoan_max_day() {
                    return loan_max_day;
                }

                public void setLoan_max_day(String loan_max_day) {
                    this.loan_max_day = loan_max_day;
                }

                public String getLoan_max_money() {
                    return loan_max_money;
                }

                public void setLoan_max_money(String loan_max_money) {
                    this.loan_max_money = loan_max_money;
                }

                public String getLoan_max_rate() {
                    return loan_max_rate;
                }

                public void setLoan_max_rate(String loan_max_rate) {
                    this.loan_max_rate = loan_max_rate;
                }

                public String getLoan_min_day() {
                    return loan_min_day;
                }

                public void setLoan_min_day(String loan_min_day) {
                    this.loan_min_day = loan_min_day;
                }

                public String getLoan_min_money() {
                    return loan_min_money;
                }

                public void setLoan_min_money(String loan_min_money) {
                    this.loan_min_money = loan_min_money;
                }

                public String getLoan_min_rate() {
                    return loan_min_rate;
                }

                public void setLoan_min_rate(String loan_min_rate) {
                    this.loan_min_rate = loan_min_rate;
                }

                public String getLogo_img() {
                    return logo_img;
                }

                public void setLogo_img(String logo_img) {
                    this.logo_img = logo_img;
                }

                public float getStars() {
                    return stars;
                }

                public void setStars(float stars) {
                    this.stars = stars;
                }
            }

            public static class BannerListBean {
                /**
                 * banner_image : http://img.cashmall24.com/4
                 * banner_type : 2
                 * banner_value : 3
                 */

                private String banner_image;
                private String banner_type;
                private String banner_value;

                public String getBanner_image() {
                    return banner_image;
                }

                public void setBanner_image(String banner_image) {
                    this.banner_image = banner_image;
                }

                public String getBanner_type() {
                    return banner_type;
                }

                public void setBanner_type(String banner_type) {
                    this.banner_type = banner_type;
                }

                public String getBanner_value() {
                    return banner_value;
                }

                public void setBanner_value(String banner_value) {
                    this.banner_value = banner_value;
                }
            }
        }
    }
}
