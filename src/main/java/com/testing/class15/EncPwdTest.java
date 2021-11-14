package com.testing.class15;

import com.testing.common.Encrypt;
import com.testing.inter.InterKw;

/**
 * @Classname InterTest
 * @Description 类型说明
 * @Date 2021/8/19 18:02
 * @Created by zhangwenliang
 */
public class EncPwdTest {
    public static void main(String[] args) {
        InterKw roy = new InterKw();
        roy.doPostUrl("http://localhost:8080/Inter/HTTP/auth", "");
        roy.saveParam("tokenV", "$.token");
        roy.addHeader("{\"roy\":\"test\", \"token\":\"{tokenV}\"}");
        roy.saveRandomParam("userR", "wilson");
        roy.saveConstant("pwd", "123456");
        roy.doPostUrl("http://localhost:8080/Inter/HTTP//register", "username={userR}&pwd={pwd}&nickname=test1&describe=test2");
        roy.saveEncPwd("encPwd", "{pwd}");
        System.out.println(roy.paramMap);
        roy.doPostUrl("http://localhost:8080/Inter/HTTP/login", "username={userR}&password={encPwd}");
        roy.saveParam("idV", "$.userid");
        roy.doPostUrl("http://localhost:8080/Inter/HTTP/getUserInfo", "id={idV}");
        roy.doPostUrl("http://localhost:8080/Inter/HTTP//logout", "");


    }
}
