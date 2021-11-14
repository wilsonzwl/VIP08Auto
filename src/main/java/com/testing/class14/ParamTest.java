package com.testing.class14;

import com.alibaba.fastjson.JSONPath;
import com.testing.common.AutoLogger;
import com.testing.inter.HttpClientUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname ParamTest
 * @Description 类型说明
 * @Date 2021/8/19 16:34
 * @Created by zhangwenliang
 */
public class ParamTest {

    public static Map<String, String> paramMap;

    public static void main(String[] args) {
        //完成对象实例化，包括cookiestore和mapheader的实例化
        HttpClientUtils roy = new HttpClientUtils();
        paramMap = new HashMap<>();
        //auth请求
        String authResult = roy.doPostUrl("http://www.testingedu.com.cn:8081/inter/HTTP/auth", "");
        AutoLogger.log.info("auth接口的返回是：" + authResult);
        //获取token值
//        String tokenValue = JSONPath.read(authResult, "$.token").toString();
//        AutoLogger.log.info("token的值是：" + tokenValue);
        saveParam("tokenV", authResult, "$.token");

        String finish = useParam("{\"roy\":\"test\", \"token\":\"{tokenV}\"}");
        AutoLogger.log.info("替换之后的结果是：" + finish);

        //添加头域
        roy.addHeader(finish);
        //login请求
        String loginResult = roy.doPostUrl("http://www.testingedu.com.cn:8081/inter/HTTP/login", "username=wilsons59&password=123456");
        AutoLogger.log.info("login接口的返回是：" + loginResult);

        //login请求
        String logoutResult = roy.doPostUrl("http://www.testingedu.com.cn:8081/inter/HTTP//logout", "");
        AutoLogger.log.info("logout接口的返回是：" + logoutResult);
    }

    public static void saveParam(String paramKey, String result, String jsonPath) {
        String paramValue = JSONPath.read(result, jsonPath).toString();
        paramMap.put(paramKey, paramValue);
    }

    //将字符串中的变量名替换为它在parammap中存储的变量值
    public static String useParam(String origin) {
        for (String key : paramMap.keySet()) {
            origin = origin.replaceAll("\\{" + key + "\\}", paramMap.get(key));
        }
        return origin;
    }


}
