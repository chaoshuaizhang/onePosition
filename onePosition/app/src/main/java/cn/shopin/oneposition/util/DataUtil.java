package cn.shopin.oneposition.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;

/**
 * Created by zcs on 2017/4/3.
 */

public class DataUtil {
    /**
     * @param data
     * @return
     * @desc 得到逗号分隔开的数据
     */
    public static String getDataFormat1(Object data) {
        Format formatter = new DecimalFormat("#,###");
        return formatter.format(data);
    }

    /**
     * @param data
     * @return
     * @desc 置顶数据转换为百分比
     */
    public static String getPercentData(Object data) {
        BigDecimal bigDecimal = new BigDecimal(data.toString());
        DecimalFormat decimalFormat = new DecimalFormat("#0.00%");
        return decimalFormat.format(bigDecimal);
    }
}
