package com.music.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 初始化当前时间字符串
     * @return String
     */
    public static String initializeCreateTime() {
        Date date = new Date();
        return DATE_FORMAT.format(date);
    }
    /**
     * 计算两个时间之间的差值，以小时为单位
     * @param date1 被减数
     * @param date2 减数
     * @return 时间之间的差值
     */
    public static int calculateTime(Date date1, Date date2) {
        /*// 参数校验
        if (date1 == null || date2 == null) {
            // 增加参数异常日志
            System.out.println("调用函数的参数异常, method : " + "calculateTime" + "\t" + "date1 : " + date1 + "\t" + "date2 : " + date2);
            // 抛出运行时异常 视业务需求而定
            throw new RuntimeException();
        }*/
        paramCheck("calculateTime", date1);
        paramCheck("calculateTime", date2);

        long date1Long = date1.getTime();
        long date2Long = date2.getTime();

        long dateLongResult = date1Long - date2Long;
        // int dateResultInt = (int) (dateLongResult / 3600 / 1000);
        return (int) (dateLongResult / 3600 / 1000);
    }

    /**
     * 查询n天前的时间
     * @param date 当前日期
     * @param days 天数 注意参数应该为负数
     * @return N天前的日期
     */
    public static String queryDateBeforeDays(Date date, int days) {
        // 参数校验
        if (date == null) {
            // 增加参数异常日志
            System.out.println("调用函数的参数异常, method : " + "queryDateBeforeDays" + "\t" + "date : " + date);
            // 抛出运行时异常 视业务需求而定
            throw new RuntimeException();
        }

        if (days > 0) {
            // 增加参数异常日志
            System.out.println("调用函数的参数异常, method : " + "queryDateBeforeDays" + "\t" + "days : " + days);
            // 抛出运行时异常 视业务需求而定
            throw new RuntimeException();
        }

        // 获取Calendar实例
        Calendar calendar = Calendar.getInstance();
        // 通过add方法对日期进行加减处理
        calendar.add(Calendar.DATE, days);
        // 返回格式化的日期
        return DATE_FORMAT.format(calendar.getTime());
    }

    /**
     * 日期转字符串
     * @param date 日期
     * @param pattern 字符串日期格式
     * @return 格式化好的日期字符串
     */
    public static String dateToString(Date date, String pattern) {
        // 参数校验
        if (date == null) {
            // 增加参数异常日志
            System.out.println("调用函数的参数异常, method : " + "dateToString" + "\t" + "date : " + date);
            // 抛出运行时异常 视业务需求而定
            throw new RuntimeException();
        }

        if (pattern == null ||  "".equals(pattern)) {
            // 增加参数异常日志
            System.out.println("调用函数的参数异常, method : " + "dateToString" + "\t" + "pattern : " + pattern);
            // 抛出运行时异常 视业务需求而定
            throw new RuntimeException();
        }

        // 根据模式串设置日期
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 将时间串从一个格式转换成另一个格式
     * @param pattern1 第一个字符串格式
     * @param pattern2 第二个字符串格式
     * @return 转换后的字符串格式
     * @throws java.text.ParseException
     */
    public static String changeDateString(String time, String pattern1, String pattern2) throws ParseException {
        // 参数校验
        if (time == null || "".equals(time) || pattern1 == null || "".equals(pattern1) || pattern2 == null || "".equals(pattern2)) {
            // 增加参数异常日志
            System.out.println("调用函数的参数异常, method : " + "changeDateString" + "\t" + "pattern1 : " + pattern1 + "\t" + "pattern2 : " + pattern2);
            // 抛出运行时异常 视业务需求而定
            throw new RuntimeException();
        }

        DateFormat dateFormat1 = new SimpleDateFormat(pattern1);
        Date date = dateFormat1.parse(time);

        return dateToString(date, pattern2);
    }

    /**
     * 参数校验公共方法
     * @param methodName 方法名
     * @param date 日期参数
     */
    private static void paramCheck(String methodName, Date date) {
        if (date == null) {
            // 增加参数异常日志
            System.out.println("调用函数的参数异常, method : " + methodName + "\t" + "date : " + date);
            // 抛出运行时异常 视业务需求而定
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) throws ParseException {
        int calculateTimeResult = calculateTime(new Date(System.currentTimeMillis() + 3600 * 1000), new Date());
        System.out.println(calculateTimeResult);

        String queryDateBeforeDays = queryDateBeforeDays(new Date(), -5);
        System.out.println(queryDateBeforeDays);

        String dateToString = dateToString(new Date(), "yyy-MM-dd HH:mm:ss");
        System.out.println("dateToString : " + dateToString);

        String str = changeDateString("2018年10月12日", "yyyy年MM月dd日", "yyyy-MM-dd");
        System.out.println("str : " + str);
    }
}
