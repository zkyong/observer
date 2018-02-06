

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /** 时间格式: 年-月-日 */
    static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
    /** 时间格式: 年-月-日 时:分:秒*/
    static final String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    /** 时间格式: 年-月-日 时:分:秒.毫秒*/
    static final String DATE_FORMAT_YMDHMSS = "yyyy-MM-dd HH:mm:ss.sss";
    /** 时间格式: 年-月-日 时:分:秒 时区*/
    static final String DATE_FORMAT_YMDHMSZ = "yyyy-MM-dd HH:mm:ss z";
    /** 时间格式: 年-月-日 时:分:秒.毫秒 时区*/
    static final String DATE_FORMAT_YMDHMSSZ = "yyyy-MM-dd HH:mm:ss.sss z";
    /** 时间格式: 年月日 */
    static final String DATE_FORMAT_YMD_UNSIGNED = "yyyyMMdd";
    /** 时间格式: 年月日时分秒*/
    static final String DATE_FORMAT_YMDHMS_UNSIGNED = "yyyyMMddHHmmss";
    
    /**
     * 将Date按指定格式转换为String
     * @param date 时间
     * @param format 时间格式
     * @return String 转换后的时间字符串
     */
    public static String date2String(Date date,String format) {
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        return null == date ? null : fmt.format(date);
    }
    
    /**
     * 将String格式的时间按指定格式转换为Date
     * @param date 时间字符串
     * @param format 时间格式
     * @return Date 转换后的时间字符串
     * @throws ParseException 
     */
    public static Date string2Date(String date,String format) {
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        try {
            return null == date ? null : fmt.parse(date);
        } catch (ParseException e) {
            return null; 
        }
    }
    
    /**
     * 将Date按指定格式截取
     * @param date 时间
     * @param format 时间格式
     * @return Date 转换后的时间
     */
    public static Date date2Date(Date date,String format) {
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        try {
            return null == date ? null : fmt.parse(fmt.format(date));
        } catch (ParseException e) {
            return null;
        }
    }
    
    /** 时间格式化为字符串: 年-月-日 */
    public static String date2StringYMD(Date date) {
        return date2String(date, DATE_FORMAT_YMD);
    }
    /** 时间格式化为字符串: 年-月-日 时:分:秒 */
    public static String date2StringYMDHMS(Date date) {
        return date2String(date, DATE_FORMAT_YMDHMS);
    }
    /** 时间格式化为字符串: 年-月-日 时:分:秒.毫秒*/
    public static String date2StringYMDHMSS(Date date) {
        return date2String(date, DATE_FORMAT_YMDHMSS);
    }
    /** 时间格式化为字符串: 年-月-日 时:分:秒 时区*/
    public static String date2StringYMDHMSZ(Date date) {
        return date2String(date, DATE_FORMAT_YMDHMSZ);
    }
    /** 时间格式化为字符串: 年-月-日 时:分:秒.毫秒 时区*/
    public static String date2StringYMDHMSSZ(Date date) {
        return date2String(date, DATE_FORMAT_YMDHMSSZ);
    }
    /** 时间格式化为字符串: 年月日时分秒*/
    public static String date2StringUnsignedYMDHMS(Date date) {
        return date2String(date, DATE_FORMAT_YMDHMS_UNSIGNED);
    }
    /** 字符串格式化为时间: 年-月-日*/
    public static Date string2DateYMD(String date) {
        return string2Date(date, DATE_FORMAT_YMD);
    }
    /** 字符串格式化为时间: 年-月-日 时:分:秒*/
    public static Date string2DateYMDHMS(String date) {
        return string2Date(date, DATE_FORMAT_YMDHMS);
    }
    /** 字符串格式化为时间: 年-月-日 时:分:秒.毫秒*/
    public static Date string2DateYMDHMSS(String date) {
        return string2Date(date, DATE_FORMAT_YMDHMSS);
    }
    /** 字符串格式化为时间: 年-月-日 时:分:秒 时区*/
    public static Date string2DateYMDHMSZ(String date) {
        return string2Date(date, DATE_FORMAT_YMDHMSZ);
    }
    /** 字符串格式化为时间: 年-月-日 时:分:秒.毫秒 时区*/
    public static Date string2DateYMDHMSSZ(String date) {
        return string2Date(date, DATE_FORMAT_YMDHMSSZ);
    }
    /** 字符串格式化为时间: 年月日*/
    public static Date string2DateUnsignedYMD(String date) {
        return string2Date(date, DATE_FORMAT_YMD_UNSIGNED);
    }
    /** 截取时间: 年-月-日*/
    public static Date date2DateYMD(Date date) {
        return date2Date(date, DATE_FORMAT_YMD);
    }
    
    public static int getDateInterval(Date fromDate, Date toDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long from;  
        long to;
        try {
            from = sdf.parse(sdf.format(fromDate)).getTime();
            to = sdf.parse(sdf.format(toDate)).getTime();
            return (int) ((to - from) / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }  
        return 0;  
    }  
    
    public static void main(String[] args) throws Exception {
        Date fromDate = string2DateYMDHMSSZ("2017-10-07 16:02:30.121 CST");
        Date toDate   = string2DateYMDHMSSZ("2017-11-07 16:02:30.221 CST");
        System.out.println(getDateInterval(fromDate, toDate));
        System.out.println(date2StringUnsignedYMDHMS(new Date()));
    }
}
