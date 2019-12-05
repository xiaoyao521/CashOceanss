package com.jike.cashocean.util;

import android.text.TextUtils;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.jike.cashocean.BuildConfig;
import com.jike.cashocean.net.Key;

import java.util.HashMap;
import java.util.Map;

public class MapUrlTools {
    /**
     * 将url参数转换成map
     *
     * @param param aa=11&bb=22&cc=33
     * @return
     */
    public static Map<String, Object> getUrlParams(String param) {
        Map<String, Object> map = new HashMap<String, Object>(0);

        if (param.length() == 0) {
            return map;
        }
        String[] params = param.split("&");
        for (int i = 0; i < params.length; i++) {
            String[] p = params[i].split("=");
            if (p.length == 2) {
                map.put(p[0], p[1]);
            }
        }
        return map;
    }

    public static String getSign(Map<String, String> map) {
        dealMap(map);
        String urlParamsByMap = getUrlParamsByMap(map);
        String encryptMD5ToString = EncryptUtils.encryptMD5ToString(urlParamsByMap);
        String sign = encryptMD5ToString.toLowerCase();
        return sign;
    }

    public static String getSign(Map<String, String> map, boolean isNeedTOken) {
        if (isNeedTOken) {
            dealMap(map);
        } else {
            dealMap1(map);

        }
        String urlParamsByMap = getUrlParamsByMap(map);
        String encryptMD5ToString = EncryptUtils.encryptMD5ToString(urlParamsByMap);
        String sign = encryptMD5ToString.toLowerCase();
        return sign;
    }

    //添加公共参数
    public static void dealMap(Map<String, String> map) {
        String string = SPUtils.getInstance().getString(Key.LANGUAGE_KEY, "");
        if (TextUtils.isEmpty(string)) {
            map.put("lang", "en");
        } else {
            map.put("lang", "ph");
        }
        map.put("timestamp", System.currentTimeMillis() / 1000 + "");
        map.put(Key.VERSION, String.valueOf(AppUtils.getAppVersionCode()));
        map.put(Key.TOKEN, SPUtils.getInstance().getString(Key.TOKEN));
    }

    //添加公共参数
    public static void dealMap1(Map<String, String> map) {
        String string = SPUtils.getInstance().getString(Key.LANGUAGE_KEY, "");
        if (TextUtils.isEmpty(string)) {
            map.put("lang", "en");
        } else {
            map.put("lang", "ph");
        }
        map.put("timestamp", System.currentTimeMillis() / 1000 + "");
        map.put(Key.VERSION, String.valueOf(AppUtils.getAppVersionCode()));
    }

    /**
     * 将map转换成url
     *
     * @param map
     * @return
     */
    public static String getUrlParamsByMap(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        String substring = "";
        if (s.endsWith("&")) {
            int length = s.length();
            substring = s.substring(0, length - 1);
            if (BuildConfig.DEBUG) {
                LogUtils.e("Map 转 URL", substring);
            }
        }
        return substring;
    }
}
