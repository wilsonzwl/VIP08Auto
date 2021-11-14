package com.testing.class7;

import java.util.Scanner;

/**
 * @Classname PhoneRegex
 * @Description 类型说明
 * @Date 2021/8/16 12:45
 * @Created by zhangwenliang
 */
public class PhoneRegex {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            String s = sc.nextLine();
            System.out.println(s + "的结果是：" +checkPhoneNumber(s));
        }


    }

    //对手机号码进行验证，是否符合手机号码规则
    public static boolean checkPhoneNumber(String phoneNum) {
        String regex = "1[3-9]\\d{9}";
        return phoneNum.matches(regex);
    }
}
