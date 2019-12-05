package com.jike.cashocean.model;

/**
 * Created by Yey on 2018/1/9.
 */

public class RegisterUserEntity {

    /**
     * data : {"code":100,"datas":{"code":100,"phone":"81293484677","token":"4e7988c64a92bdd35cb9300099eac80529"},"msg":"注册成功"}
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
         * datas : {"code":100,"phone":"81293484677","token":"4e7988c64a92bdd35cb9300099eac80529"}
         * msg : 注册成功
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
             * code : 100
             * phone : 81293484677
             * token : 4e7988c64a92bdd35cb9300099eac80529
             */

            private int code;
            private String phone;
            private String token;

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }
        }
    }
}
