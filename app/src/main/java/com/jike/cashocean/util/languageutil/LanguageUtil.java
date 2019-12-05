package com.jike.cashocean.util.languageutil;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

public class LanguageUtil {
    public static void applyLanguage(Context context, String newLanguage) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale;
        if (TextUtils.isEmpty(newLanguage)) {//如果没有指定语言使用系统首选语言
            locale = Locale.ENGLISH;
        } else {//指定了语言使用指定语言，没有则使用首选语言
            locale = new Locale("tl", "PH");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // apply locale
            configuration.setLocale(locale);
        } else {
            // updateConfiguration
            configuration.locale = locale;
            DisplayMetrics dm = resources.getDisplayMetrics();
            resources.updateConfiguration(configuration, dm);
        }
    }

    public static Context attachBaseContext(Context context, String language) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return createConfigurationResources(context, language);
        } else {
            applyLanguage(context, language);
            return context;
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context createConfigurationResources(Context context, String language) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale;
        if (TextUtils.isEmpty(language)) {//如果没有指定语言使用系统首选语言
            locale = new Locale("tl", "PH");
        } else {//指定了语言使用指定语言，没有则使用首选语言
            locale = Locale.ENGLISH;
        }
//        String country = locale.getCountry();
//        String language1 = locale.getLanguage();
//        Log.e("选择的语言", country + "  " + language1);
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }
}
