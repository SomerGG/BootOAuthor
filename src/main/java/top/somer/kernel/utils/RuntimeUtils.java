package top.somer.kernel.utils;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * @author Somer
 * @date 2017/10/12
 */

public class RuntimeUtils {

    /**
     * 获取ObjectId
     *
     * @return
     */
    public static String getObjectId() {
        return new ObjectId().toString();
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public static Integer getCurrentYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    /**
     * 返回当前月份
     *
     * @return
     */
    public static Integer getCurrentMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1;
    }

    public static void main(String[] args) {
        System.out.println(getObjectId());
    }

}
