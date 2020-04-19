package com.quinn.util.constant;

import java.util.regex.Pattern;

/**
 * 正则表达式常量
 *
 * @author Qunhua.Liao
 * @since 2020-03-29
 */
public interface RegexConstant {

    /**
     * 日期格式【YYYY-MM-DD】正则表达式
     */
    Pattern DATE_PATTEN_YYYY_MM_DD_SEPARATOR_1 = Pattern.compile("\\d{4}-[0,1][0-9]-[0-3][0-9]");

    /**
     * 日期格式【YYYY/MM/DD】正则表达式
     */
    Pattern DATE_PATTEN_YYYY_MM_DD_SEPARATOR_2 = Pattern.compile("\\d{4}/[0,1][0-9]/[0-3][0-9]");

    /**
     * 日期格式【YYYYMMDD】正则表达式
     */
    Pattern DATE_PATTEN_YYYY_MM_DD_NO_SEPARATOR = Pattern.compile("\\d{4}[0,1][0-9][0-3][0-9]");

    /**
     * 日期格式【YYYY-MM-DD HH:mm:ss】正则表达式
     */
    Pattern DATE_PATTEN_YYYY_MM_DD_HH_MM_SS_SEPARATOR_1 = Pattern.compile("\\d{4}-[0,1][0-9]-[0-3][0-9] [0,2][0-9](:[0-5][0-9]){2}");

    /**
     * 日期格式【YYYY/MM/DD HH:mm:ss】正则表达式
     */
    Pattern DATE_PATTEN_YYYY_MM_DD_HH_MM_SS_SEPARATOR_2 = Pattern.compile("\\d{4}/[0,1][0-9]/[0-3][0-9] [0,2][0-9](:[0-5][0-9]){2}");

    /**
     * 日期格式【YYYYMMDDHHmmss】正则表达式
     */
    Pattern DATE_PATTEN_YYYY_MM_DD_HH_MM_SS_NO_SEPARATOR = Pattern.compile("\\d{4}[0,1][0-9][0-3][0-9][0,2][0-9]([0-5][0-9]){2}");

    /**
     * 日期格式【YYYY-MM-DD HH:mm:ss.SSS】正则表达式
     */
    Pattern DATE_PATTEN_YYYY_MM_DD_HH_MM_SS_SSS_SEPARATOR_1 = Pattern.compile("\\d{4}-[0,1][0-9]-[0-3][0-9] [0,2][0-9](:[0-5][0-9]){2}\\.\\d{3}");

    /**
     * 日期格式【YYYY/MM/DD HH:mm:ss.SSS】正则表达式
     */
    Pattern DATE_PATTEN_YYYY_MM_DD_HH_MM_SS_SSS_SEPARATOR_2 = Pattern.compile("\\d{4}/[0,1][0-9]/[0-3][0-9] [0,2][0-9](:[0-5][0-9]){2}\\.\\d{3}");

    /**
     * 日期格式【YYYYMMDDHHmmss.SSS】正则表达式
     */
    Pattern DATE_PATTEN_YYYY_MM_DD_HH_MM_SS_SSS_NO_SEPARATOR = Pattern.compile("\\d{4}[0,1][0-9][0-3][0-9][0,2][0-9]([0-5][0-9]){2}\\.\\d{3}");

    /**
     * 取顶级域名的正则, 可以匹配到xxx.xxx.com, 不能匹配到xxx.com及IP, localhost等
     * */
    String NET_PATTERN_TOP_DOMAIN_STRING = "[a-zA-Z0-9][-a-zA-Z0-9]{0,62}\\.([a-zA-Z0-9][-a-zA-Z0-9]{0,62}\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})";

    Pattern NET_PATTERN_TOP_DOMAIN = Pattern.compile(NET_PATTERN_TOP_DOMAIN_STRING);

    String NET_PATTERN_INTRANET_STRING = "(10|172|192|127)\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})";

    Pattern NET_PATTERN_INTRANET = Pattern.compile(NET_PATTERN_INTRANET_STRING);


    /**
     * 手机号正则
     * */
    String MOBILE_PATTERN_STRING = "^1[3,4,5,6,7,8,9]\\d{9}$";

    Pattern MOBILE_PATTERN = Pattern.compile(MOBILE_PATTERN_STRING);

}
