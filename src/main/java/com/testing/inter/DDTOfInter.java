package com.testing.inter;

import com.alibaba.fastjson.JSONPath;
import com.testing.common.AutoLogger;
import com.testing.common.AutoTools;
import com.testing.common.Encrypt;
import com.testing.common.ExcelWriter;

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
public class DDTOfInter {
    //完成结果写入excel文件的writer对象
    ExcelWriter results;

    public Map<String, String> paramMap;

    public String responseResult;

    public HttpClientUtils httpClientUtils;

    //定义常量PASS和FAIL
    public static final String PASS = "pass";
    public static final String FAIL = "fail";
    public static final int RESULT_COL = 10;
    public static final int ACTUAL_COL = 11;

    public int nowline = 0;

    public void setNowline(int rowNo) {
        nowline = rowNo;
    }

    public DDTOfInter(ExcelWriter results) {
        paramMap = new HashMap<>();
        httpClientUtils = new HttpClientUtils();
        this.results = results;
    }

    //成功的时候的写入操作
    public void setPass() {
        results.writeCell(nowline, ACTUAL_COL, responseResult);
        results.writeCell(nowline, RESULT_COL, PASS);
    }

    //失败的时候的写入操作
    public void setFail(Exception e) {
        AutoLogger.log.info(e, e.fillInStackTrace());
        results.writeFailCell(nowline, ACTUAL_COL, responseResult);
        results.writeFailCell(nowline, RESULT_COL, FAIL );
    }

    //无异常的时候的写入操作
    //失败的时候的写入操作
    public void setFail() {
        results.writeFailCell(nowline, ACTUAL_COL, responseResult);
        results.writeFailCell(nowline, RESULT_COL, FAIL );
    }

    public void addHeader(String headerJson) {
        try {
            headerJson = useParam(headerJson);
            httpClientUtils.addHeader(headerJson);
            setPass();
        } catch (Exception e) {
            setFail(e);
        }
    }

    public void clearHeader() {
        httpClientUtils.clearHeader();
        setPass();
    }

    public void useCookie(){
        try {
            httpClientUtils.useCookie();
            setPass();
        } catch (Exception e) {
            setFail(e);
        }
    }

    public void notUseCookie(){
        try {
            httpClientUtils.noUseCookie();
            setPass();
        } catch (Exception e) {
            setFail(e);
        }
    }

    public void doGet(String url){
        try {
            url=useParam(url);
            responseResult= httpClientUtils.doget(url);
            AutoLogger.log.info(responseResult);
            setPass();
        } catch (Exception e) {
            setFail(e);
        }
    }

    public void doPostUrl(String url, String param) {
        try {
            url = useParam(url);
            param = useParam(param);
            responseResult = httpClientUtils.doPostUrl(url, param);
            AutoLogger.log.info(responseResult);
            setPass();
        } catch (Exception e) {
            setFail(e);
        }

    }

    public void doPostSoap(String url, String param) {
        try {
            url = useParam(url);
            param = useParam(param);
            responseResult = httpClientUtils.doPostSoap(url, param);
            AutoLogger.log.info(responseResult);
            setPass();
        } catch (Exception e) {
            setFail(e);
        }

    }



    public void saveParam(String paramKey, String jsonPath) {
        try {
            String paramValue = JSONPath.read(responseResult, jsonPath).toString();
            paramMap.put(paramKey, paramValue);
            setPass();
        } catch (Exception e) {
            setFail(e);
        }
    }

    //进行加密，得到加密后的密码字符串，存到paramMap供之后使用
    public void saveEncPwd(String paramKey, String originString) {
        try {
            Encrypt enc = new Encrypt();
            String paramValue = enc.enCrypt(useParam(originString));
            paramMap.put(paramKey, paramValue);
            setPass();
        } catch (Exception e) {
            setFail(e);
        }
    }

    //存储随机生产的用户名参数
    public void saveRandomParam(String paramKey, String originString) {
        try {
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
            String timeStamp = sdf.format(now);
            String paramValue = originString + timeStamp;
            //将参数存储到paramMap中
            paramMap.put(paramKey, paramValue);
            setPass();
        } catch (Exception e) {
            setFail(e);
        }
    }

    //存储固定的之后要用的参数
    public void saveConstant(String paramKey, String paramValue) {
        paramMap.put(paramKey, paramValue);
    }


    //将字符串中的变量名替换为它在parammap中存储的变量值
    public String useParam(String origin) {
        try {
            for (String key : paramMap.keySet()) {
                origin = origin.replaceAll("\\{" + key + "\\}", paramMap.get(key));
            }
            setPass();
            return origin;
        } catch (Exception e) {
            setFail(e);
            return "无法存储到paramMap";
        }
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
            setPass();
            return true;
        } else {
            setFail();
            return false;
        }
    }

    public boolean jsonValueCheck(String jsonpath, String expected) {
        String jsonValue = JSONPath.read(responseResult, jsonpath).toString();
        if (jsonValue.equals(expected)) {
            AutoLogger.log.info("解析结果是：" + jsonValue);
            setPass();
            return true;
        } else {
            setFail();
            return false;
        }
    }


}
