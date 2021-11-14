package com.testing.class7;

/**
 * @Classname RegexLearn
 * @Description 类型说明
 * @Date 2021/8/15 21:25
 * @Created by zhangwenliang
 */
public class RegexLearn {
    public static void main(String[] args) {
        String roy = "roy老师";
        String will = "will老师";
        String tufei = "土匪.老师";
        String youmi = "悠米老师";
        String rmi = "r米老师";
        String kaka = "卡卡";
        String gang = "\\老师";
        String doubleroy = "royrop老师";

        //制定一个规则，最后一位是老师结尾的字符串，这就是一个正则表达式regex
        String teacher = "^..\\.老师$";
        String gangteacher = "\\\\老师";
        String threewteacher = "...老师";
        String royandy = "r[roy悠米\\.\\\\]{1,3}老师";
        String roydouble1 = "(ro[yp]){2}老师";
        String roydouble = "(roy|rop){2}老师";

        System.out.println(roy + " 匹配结果是：" + roy.matches(royandy));
        System.out.println(will + " 匹配结果是：" + will.matches(royandy));
        System.out.println(tufei + " 匹配结果是：" + tufei.matches(royandy));
        System.out.println(youmi + " 匹配结果是：" + youmi.matches(royandy));
        System.out.println(kaka + " 匹配结果是：" + kaka.matches(royandy));
        System.out.println(rmi + " 匹配结果是：" + rmi.matches(royandy));
        System.out.println(gang + " 匹配结果是：" + gang.matches(royandy));
        System.out.println(doubleroy + " 匹配结果是：" + doubleroy.matches(roydouble));

    }
}
