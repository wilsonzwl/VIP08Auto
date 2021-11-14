package com.testing.class15;

import com.testing.inter.InterKw;

/**
 * @Classname RestTest
 * @Description 类型说明
 * @Date 2021/8/20 11:58
 * @Created by zhangwenliang
 */
public class RestTest {
    public static void main(String[] args) {
        InterKw roy = new InterKw();
        roy.doPostUrl("http://localhost:8080/Inter/REST/auth", "");
        roy.saveParam("tokenV", "$.token");
        roy.addHeader("{\"token\":\"{tokenV}\"}");
        roy.saveRandomParam("userR", "kaka");
        roy.saveConstant("pwd", "123456");
        roy.doPostUrl("http://localhost:8080/Inter/REST/user/register", "{\"username\":\"{userR}\",\"pwd\":\"{pwd}\",\"nickname\":\"rest1\",\"describe\":\"rest1\"}");
        roy.doPostUrl("http://localhost:8080/Inter/REST/login/{userR}/{pwd}", "");
        roy.saveParam("idV", "$.userid");
        roy.doPostUrl("http://localhost:8080/Inter/REST/login/{idV}", "");
        roy.doPostUrl("http://localhost:8080/Inter/REST/login", "");
    }
}
