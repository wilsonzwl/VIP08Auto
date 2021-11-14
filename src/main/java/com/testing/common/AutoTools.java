package com.testing.common;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Classname AutoTools
 * @Description 通用工具类
 * @Date 2021/8/21 12:18
 * @Created by zhangwenliang
 */
public class AutoTools {

    public static String createTime(String format) {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String result = sdf.format(now);
        return result;
    }

    //反射方法，用于通过excel表中一行内容中的关键字名，查找方法并传递参数进行调用
    public static void invokeI(Object keyword, List<String> rowContent, int col) {
        //借助exception来进行查找判断，找到正确的参数个数
        //先尝试无参的方法
        try {
            Method target = keyword.getClass().getDeclaredMethod(rowContent.get(col));
            target.invoke(keyword);
            //如果进入try并执行成功了，return退出方法执行，不再去查找更过个参数的可能。
            return;
        } catch (Exception e) {
        }
        //1个参数的方法
        try {
            Method target = keyword.getClass().getDeclaredMethod(rowContent.get(col), String.class);
            target.invoke(keyword, rowContent.get(col + 1));
            return;
        } catch (Exception e) {
        }
        //2个参数的方法
        try {
            Method target = keyword.getClass().getDeclaredMethod(rowContent.get(col), String.class, String.class);
            target.invoke(keyword, rowContent.get(col + 1), rowContent.get(col + 2));
            return;
        } catch (Exception e) {
        }
        //3个参数的方法
        try {
            Method target = keyword.getClass().getDeclaredMethod(rowContent.get(3), String.class, String.class, String.class);
            target.invoke(keyword, rowContent.get(col + 1), rowContent.get(col + 2), rowContent.get(col + 3));
            return;
        } catch (Exception e) {
        }
    }

}
