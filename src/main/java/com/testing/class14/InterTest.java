package com.testing.class14;

import com.testing.inter.InterKw;

/**
 * @Classname InterTest
 * @Description 类型说明
 * @Date 2021/8/19 18:02
 * @Created by zhangwenliang
 */
public class InterTest {
    public static void main(String[] args) {
        InterKw roy = new InterKw();
        roy.doPostUrl("http://www.testingedu.com.cn:8081/inter/HTTP/auth", "");
        roy.saveParam("tokenV", "$.token");
        roy.addHeader("{\"roy\":\"test\", \"token\":\"{tokenV}\"}");
        roy.doPostUrl("http://www.testingedu.com.cn:8081/inter/HTTP/login", "username=wilsons59&password=123456");
        roy.doPostUrl("http://www.testingedu.com.cn:8081/inter/HTTP//logout", "");


    }
}
