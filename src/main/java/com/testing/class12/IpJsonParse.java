package com.testing.class12;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.testing.class11.UnicodeDecoder;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname IpGetTest
 * @Description 类型说明
 * @Date 2021/8/15 11:02
 * @Created by zhangwenliang
 */
public class IpJsonParse {

    public static void main(String[] args) {
        CloseableHttpClient roy = HttpClients.createDefault();

        HttpGet ipget = new HttpGet("https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=3.3.3.3&co=&resource_id=5809&t=1629007127927&ie=utf8&oe=gbk&cb=op_aladdin_callback&format=json&tn=baidu&cb=jQuery110205064722294527677_1628995514459&_=1628995514469");
        try {
            CloseableHttpResponse ipResponse = roy.execute(ipget);
            System.out.println("返回包的结果是：" + ipResponse);
            //获取返回体中的实际内容
            HttpEntity ipEntity = ipResponse.getEntity();
            System.out.println("状态码是：" + ipResponse.getStatusLine().getStatusCode());
            System.out.println("返回体是：" + ipEntity);
            String ipResult = EntityUtils.toString(ipEntity, "UTF-8");
            System.out.println("返回结果是：" + ipResult);
            ipResult = UnicodeDecoder.unicodeDecoder(ipResult);
            System.out.println(ipResult);

            if (ipResult.contains("美国")) {
                System.out.println("测试成功");
            } else {
                System.out.println("测试失败");
            }


            //正则表达式，根据制定的规则提取需要的内容
            Pattern p = Pattern.compile("\\((.*?)\\)");
            Matcher m = p.matcher(ipResult);
            m.find();
            String ipjson = m.group(1);

            JSONObject jsonObject = JSON.parseObject(ipjson);
            JSONArray result = jsonObject.getJSONArray("Result");
            JSONObject resultjson = result.getJSONObject(0);
            System.out.println("result的内容是" + resultjson);
            String location = resultjson.getJSONObject("DisplayData").
                    getJSONObject("resultData").getJSONObject("tplData").getString("location");
            System.out.println("fastjson逐层解析的结果是：" + location);


            //jsonpath解析：也要基于字符串是个标准的json格式了。
            String jsonpathResult = JSONPath.read(ipjson, "$.Result[0].DisplayData.resultData.tplData.location").toString();
            System.out.println("jsonPath解析出来的结果是："+jsonpathResult);


            //正则表达式提取需要的内容
            Pattern locationPattern = Pattern.compile("\"location\":\"(.*?)\",");
            Matcher matcher = locationPattern.matcher(ipResult);
            matcher.find();
            String locationregex = matcher.group(1);
            System.out.println("通过正则表达式直接获取的结果是：" + locationregex);


//            if (ipResult.contains("\\u7f8e\\u56fd \\u4e9a\\u9a6c\\u900a\\u4e91")) {
//                System.out.println("测试成功");
//            } else {
//                System.out.println("测试失败");
//            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
