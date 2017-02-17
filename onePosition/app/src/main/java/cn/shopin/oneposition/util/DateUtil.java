package cn.shopin.oneposition.util;

/**
 * Created by zcs on 2017/2/16.
 */

public class DateUtil {

    /**
     * 根据/date(1481875552000)/取出1481875552000
     *
     * @param time
     * @return
     */
    public static String getSubDate(String time) {
        return time.substring(time.indexOf("(") + 1, time.indexOf(")"));
    }
}
