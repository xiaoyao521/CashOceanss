package com.jike.cashocean.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.blankj.utilcode.util.AppUtils;
import com.google.gson.Gson;
import com.jike.cashocean.model.AppInfoBean;
import com.jike.cashocean.ui.CashMallApplication;

import java.util.ArrayList;
import java.util.List;

public class PhoneReadUtils {
    public static String getAppList() {
        List<AppUtils.AppInfo> appsInfo = AppUtils.getAppsInfo();
        List<AppUtils.AppInfo> applist = new ArrayList<>();
        for (int i = 0; i < appsInfo.size(); i++) {
            if (!appsInfo.get(i).isSystem()) {
                applist.add(appsInfo.get(i));
            }
        }
        return new Gson().toJson(applist);
    }

    public static String getAppListNew() {
        List<AppUtils.AppInfo> appsInfo = AppUtils.getAppsInfo();
        ArrayList<AppInfoBean> appNameS = new ArrayList<>();

        PackageManager packageManager = CashMallApplication.getContext().getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        for (int i = 0; i < appsInfo.size(); i++) {
            AppUtils.AppInfo appInfo = appsInfo.get(i);
            PackageInfo packageInfo = packageInfos.get(i);

            if ((ApplicationInfo.FLAG_SYSTEM & packageInfo.applicationInfo.flags) == 0) {
                appNameS.add(new AppInfoBean(appInfo.getPackageName(), appInfo.getName(),
                        packageInfo.firstInstallTime / 1000,
                        packageInfo.lastUpdateTime / 1000));
            }
        }
        String appListStr = new Gson().toJson(appNameS);
        return appListStr;
    }

    /*
     * https://blog.csdn.net/divaid/article/details/83054588
     *
     * 可以通过获取某个应用信息并捕获未安装时的异常判断：
     * */
    private boolean checkAppInstalled(Context context, String pkgName) {
        if (pkgName == null || pkgName.isEmpty()) {
            return false;
        }
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        if (packageInfo == null) {
            return false;
        } else {
            return true;//true为安装了，false为未安装
        }
    }

    /*
     * 二、通过获取应用列表对比判断
     * */
    private boolean checkAppInstalled2(Context context, String pkgName) {
        if (pkgName == null || pkgName.isEmpty()) {
            return false;
        }
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> info = packageManager.getInstalledPackages(0);
        if (info == null || info.isEmpty())
            return false;
        for (int i = 0; i < info.size(); i++) {
            if (pkgName.equals(info.get(i).packageName)) {
                return true;
            }
        }
        return false;
    }
}
