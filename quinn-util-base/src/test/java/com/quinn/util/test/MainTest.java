package com.quinn.util.test;

import com.quinn.util.base.convertor.BaseConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 工具方法测试类
 *
 * @author Qunhua.Liao
 * @since 2020-03-29
 */
public class MainTest {

    public static void main(String[] args) {
        System.out.println(BaseConverter.staticConvert("12", Short.class));
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse("2020-02-29"));
        System.out.println(LocalDate.now().toEpochDay());
        System.out.println(BaseConverter.staticToString(LocalDateTime.now()));
    }

}
