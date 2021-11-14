package com.testing.class14;

import com.alibaba.fastjson.JSONPath;
import com.testing.common.AutoLogger;
import com.testing.inter.HttpClientUtils;

/**
 * @Classname HeaderTest
 * @Description 使用HttpClientUtils完成头域添加操作
 * @Date 2021/8/19 15:13
 * @Created by zhangwenliang
 */
public class HeaderTest {
    public static void main(String[] args) {
        //完成对象实例化，包括cookiestore和mapheader的实例化
        HttpClientUtils roy = new HttpClientUtils();
        //auth请求
        String authResult = roy.doPostUrl("http://www.testingedu.com.cn:8081/inter/HTTP/auth", "");
        AutoLogger.log.info("auth接口的返回是：" + authResult);
        //获取token值
        String tokenValue = JSONPath.read(authResult, "$.token").toString();
        AutoLogger.log.info("token的值是：" + tokenValue);
        //添加头域
        roy.addHeader("{\"roy\":\"test\", \"token\":\"" + tokenValue + "\"}");
        //login请求
        String loginResult = roy.doPostUrl("http://www.testingedu.com.cn:8081/inter/HTTP/login", "username=wilsons59&password=123456");
        AutoLogger.log.info("login接口的返回是：" + loginResult);

        roy.notUseHeader();
        //login请求
        String logoutResult = roy.doPostUrl("http://www.testingedu.com.cn:8081/inter/HTTP//logout", "");
        AutoLogger.log.info("logout接口的返回是：" + logoutResult);


    }
}
