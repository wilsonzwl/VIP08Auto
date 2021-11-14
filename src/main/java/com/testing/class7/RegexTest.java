package com.testing.class7;

/**
 * @Classname RegexTest
 * @Description 类型说明
 * @Date 2021/8/15 21:12
 * @Created by zhangwenliang
 */
public class RegexTest {
    public static void main(String[] args) {
        String listname="[1, 2, 33, 5, 43, 6, 9, 7, 8, 0]";
//        String result = listname.replace("3", "x");
//        System.out.println(result);
//        result = result.replace("4", "x");
//        System.out.println(result);

//        String result = listname;
//        for (int i = 3; i <= 7; i++) {
//            result = result.replace(i + "", "x");
//        }
//        System.out.println(result);

//        String regex = "\\d";
//        String x = listname.replace(".", "x");
//        System.out.println(x);
        String x1 = listname.replaceAll("[3-7]{2}", "roy");
        System.out.println(x1);

//        String splitReg = "[,\\[,\\]]";
//        String[] split = listname.split(splitReg);
//        for (String s : split) {
//            System.out.println(s.trim());
//        }


    }
}
