package com.jike.cashocean.util;

import android.content.Context;

import java.io.File;

public class MakePath {
    public static String getFilePath(Context context, String fileName) {
        return context.getCacheDir().getAbsolutePath() + File.separator + fileName;
    }

    public static File getFile(Context context, String fileName) {
        String filePath = context.getCacheDir().getAbsolutePath() + File.separator + fileName;
        return new File(filePath);
    }

    public static File getRxCache(Context context) {
        String filePath = context.getCacheDir().getAbsolutePath() + File.separator + "getRxCache_oklik";
        File file = new File(filePath);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
            return file;
        } else {
            return file;
        }
    }
}
