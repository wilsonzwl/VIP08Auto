package com.testing.class5;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Classname RandomTest
 * @Description 类型说明
 * @Date 2021/8/7 11:25
 * @Created by zhangwenliang
 */
public class RandomTest {
    public static void main(String[] args) {
//        Random random = new Random();
//        String[] availStr = {"roy", "will", "土匪", "卡卡"};
//        int number = random.nextInt(availStr.length - 1);
//        System.out.println("获取到的随机值是：" + availStr[number]);

        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        SimpleDateFormat goodsNum = new SimpleDateFormat("ddHHmm");
        String format = goodsNum.format(date);
        System.out.println(format);

    }
}
