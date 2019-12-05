package com.jike.cashocean.util;

import java.util.Date;

public class TimeUtils {

    /**
     * 获取精确到秒时间戳
     *通过string.substring将后三位去掉
     * @param date
     * @return
     */
    public static int getSecondTimestamp(Date date) {
        if (date == null) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0, length - 3));
        } else {
            return 0;
        }

    }

    /**
     * 获取到秒的时间戳
     * 利用整数将最后三位去掉
     * @param date
     * @return
     */
    public static int getSecondTimestampTwo(Date date) {
        if (date == null) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        return Integer.valueOf(timestamp);
    }
}
