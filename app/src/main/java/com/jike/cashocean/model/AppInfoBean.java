package com.jike.cashocean.model;

public class AppInfoBean {
    private String package_name;
    private String app_name;
    private long install_time;
    private long updata_time;

    public AppInfoBean(String package_name, String app_name, long install_time, long updata_time) {
        this.package_name = package_name;
        this.app_name = app_name;
        this.install_time = install_time;
        this.updata_time = updata_time;
    }
}
