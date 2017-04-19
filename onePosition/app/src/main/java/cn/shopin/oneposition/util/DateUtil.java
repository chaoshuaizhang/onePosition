package cn.shopin.oneposition.util;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;

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

    /**
     * @return
     * @desc 得到当前的毫秒数
     */
    public static String currdate2MillSecond() {
        return Long.toString(new Date().getTime());
    }

    /**
     * @param year
     * @param month
     * @param day
     * @return
     * @desc 根据指定年月日获得毫秒数
     */
    public static String date2MillSecond(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int millsecond = calendar.get(Calendar.MILLISECOND);
        calendar.set(year, month - 1, day, hour, minute, second);
        long mills = calendar.getTime().getTime() + millsecond;
        return String.valueOf(mills);
    }

    public static int[] getCurYMD(int[] selectedDay) {
        Calendar calendar = Calendar.getInstance();
        selectedDay[0] = calendar.get(Calendar.YEAR);
        selectedDay[1] = calendar.get(Calendar.MONTH) + 1;
        selectedDay[2] = calendar.get(Calendar.DATE);
        return selectedDay;
    }

    /**
     * @param selectedDay
     * @param d
     * @return
     * @desc 通过指定日期得到前一天
     */
    public static int[] getPreDayByYMD(int[] selectedDay, int d) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(selectedDay[0], selectedDay[1] - 1, selectedDay[2]);
        calendar.add(Calendar.DATE, d);
        selectedDay[0] = calendar.get(Calendar.YEAR);
        selectedDay[1] = calendar.get(Calendar.MONTH) + 1;
        selectedDay[2] = calendar.get(Calendar.DATE);
        return selectedDay;
    }

    /**
     * @param selectedDay
     * @param d
     * @return
     * @desc 通过指定日期得到后一天
     */
    public static int[] getNextDayByYMD(int[] selectedDay, int d) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(selectedDay[0], selectedDay[1] - 1, selectedDay[2]);
        calendar.add(Calendar.DATE, d);
        selectedDay[0] = calendar.get(Calendar.YEAR);
        selectedDay[1] = calendar.get(Calendar.MONTH) + 1;
        selectedDay[2] = calendar.get(Calendar.DATE);
        return selectedDay;
    }

    public static int[] setDate(int[] selectedDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(selectedDay[0], selectedDay[1] - 1, selectedDay[2]);
        selectedDay[0] = calendar.get(Calendar.YEAR);
        selectedDay[1] = calendar.get(Calendar.MONTH) + 1;
        selectedDay[2] = calendar.get(Calendar.DATE);
        return selectedDay;
    }
}
