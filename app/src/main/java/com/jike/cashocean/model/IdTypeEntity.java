package com.jike.cashocean.model;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * Created by Yey on 2018/1/9.
 */

public class IdTypeEntity {

    /**
     * ret : 200
     * data : {"code":100,"msg":"获取成功","datas":{"list":[{"id":"1","type_name":"UMID"},{"id":"2",
     * "type_name":"Passport"},{"id":"3","type_name":"Driver's License"},{"id":"4",
     * "type_name":"SSS ID"},{"id":"13","type_name":"PRC ID"},{"id":"14","type_name":"TIN ID"},{
     * "id":"15","type_name":"Voter's ID"}]}}
     * msg :
     * debug : {"收到的sign:":"12","用于生成sign的str:":"version=1",
     * "服务器生成的sign:":"e42dadd15d4bc1646e84d1ded9a5d1f5","stack":["[#1 - 0ms -
     * PHALAPI_INIT]/var/www/yndc/njkascx23erasxcdb/public/index.php(8)","[#2 - 0ms -
     * PHALAPI_RESPONSE]/var/www/yndc/njkascx23erasxcdb/vendor/phalapi/kernal/src/PhalApi.php(47)
     * ","[#3 - 0.6ms - PHALAPI_FINISH]/var/www/yndc/njkascx23erasxcdb/vendor/phalapi/kernal/src
     * /PhalApi.php(75)"],"sqls":[],"version":"2.7.0"}
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
         * msg : 获取成功
         * datas : {"list":[{"id":"1","type_name":"UMID"},{"id":"2","type_name":"Passport"},{"id
         * ":"3","type_name":"Driver's License"},{"id":"4","type_name":"SSS ID"},{"id":"13",
         * "type_name":"PRC ID"},{"id":"14","type_name":"TIN ID"},{"id":"15","type_name":"Voter's
         * ID"}]}
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
            private List<ListBean> list;

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean implements IPickerViewData {
                /**
                 * id : 1
                 * type_name : UMID
                 */

                private String id;
                private String type_name;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getType_name() {
                    return type_name;
                }

                public void setType_name(String type_name) {
                    this.type_name = type_name;
                }

                @Override
                public String getPickerViewText() {
                    return type_name;
                }
            }
        }
    }
}
