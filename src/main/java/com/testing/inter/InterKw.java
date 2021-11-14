package com.testing.inter;

import com.alibaba.fastjson.JSONPath;
import com.testing.common.AutoLogger;
import com.testing.common.Encrypt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname InterKw
 * @Description 接口自动化测试的关键字，包括断言等与请求发包无关的操作。
 * @Date 2021/8/17 20:42
 * @Created by zhangwenliang
 */
public class InterKw {

    public Map<String, String> paramMap;

    public String responseResult;

    public HttpClientUtils httpClientUtils;

    public InterKw() {
        paramMap = new HashMap<>();
        httpClientUtils = new HttpClientUtils();
    }

    public void addHeader(String headerJson) {
        headerJson = useParam(headerJson);
        httpClientUtils.addHeader(headerJson);
    }

    public void doPostUrl(String url, String param) {
        url = useParam(url);
        param = useParam(param);
        responseResult = httpClientUtils.doPostUrl(url, param);
        AutoLogger.log.info(responseResult);

    }

    public void doPostSoap(String url, String param) {
        url = useParam(url);
        param = useParam(param);
        responseResult = httpClientUtils.doPostSoap(url, param);
        AutoLogger.log.info(responseResult);

    }



    public void saveParam(String paramKey, String jsonPath) {
        String paramValue = JSONPath.read(responseResult, jsonPath).toString();
        paramMap.put(paramKey, paramValue);
    }

    //进行加密，得到加密后的密码字符串，存到paramMap供之后使用
    public void saveEncPwd(String paramKey, String originString) {
        Encrypt enc = new Encrypt();
        String paramValue = enc.enCrypt(useParam(originString));
        paramMap.put(paramKey, paramValue);
    }

    //存储随机生产的用户名参数
    public void saveRandomParam(String paramKey, String originString) {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String timeStamp = sdf.format(now);
        String paramValue = originString + timeStamp;
        //将参数存储到paramMap中
        paramMap.put(paramKey, paramValue);
    }

    //存储固定的之后要用的参数
    public void saveConstant(String paramKey, String paramValue) {
        paramMap.put(paramKey, paramValue);
    }


    //将字符串中的变量名替换为它在parammap中存储的变量值
    public String useParam(String origin) {
        for (String key : paramMap.keySet()) {
            origin = origin.replaceAll("\\{" + key + "\\}", paramMap.get(key));
        }
        return origin;
    }


    /**
     * 断言返回结果通过正则表达式获取其中部分内容之后，是否和预期结果一致。
     *
     * @param regex    断言时使用的正则表达式
     * @param expected 预期结果
     * @return
     */
    public boolean assertRegexEq(String regex, String expected) {
        //正则表达式解析想要的结果
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(responseResult);
        String result = "";
        if (m.find()) {
            result = m.group(1);
        }
        AutoLogger.log.info("提取后的结果是：" + result);
        if (result.equals(expected)) {
            AutoLogger.log.info("pass");
            return true;
        }
        return false;
    }

    public boolean jsonValueCheck(String jsonpath, String expected) {
        String jsonValue = JSONPath.read(responseResult, jsonpath).toString();
        if (jsonValue.equals(expected)) {
            AutoLogger.log.info("解析结果是：" + jsonValue);
            return true;
        }
        return false;
    }


}
