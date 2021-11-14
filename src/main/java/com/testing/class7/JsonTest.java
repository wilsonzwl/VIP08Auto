package com.testing.class7;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname JsonTest
 * @Description 类型说明
 * @Date 2021/8/16 13:38
 * @Created by zhangwenliang
 */
public class JsonTest {
    public static void main(String[] args) {
        String ipResult="jQuery1102029793080973590014_1607261163703({\"ResultCode\":\"0\",\"ResultNum\":\"1\",\"Result\":[{\"DisplayData\":{\"strategy\":{\"tempName\":\"ip\",\"precharge\":\"0\",\"ctplOrPhp\":\"1\"},\"resultData\":{\"tplData\":{\"srcid\":\"5809\",\"resourceid\":\"5809\",\"OriginQuery\":\"3.3.3.3\",\"origipquery\":\"3.3.3.3\",\"query\":\"3.3.3.3\",\"origip\":\"3.3.3.3\",\"location\":\"\\u7f8e\\u56fd \\u4e9a\\u9a6c\\u900a\\u4e91\",\"userip\":\"\",\"showlamp\":\"1\",\"tplt\":\"ip\",\"titlecont\":\"IP\\u5730\\u5740\\u67e5\\u8be2\",\"realurl\":\"http:\\/\\/www.ip138.com\\/\",\"showLikeShare\":\"1\",\"shareImage\":\"1\",\"data_source\":\"AE\"},\"extData\":{\"tplt\":\"ip\",\"resourceid\":\"5809\",\"OriginQuery\":\"3.3.3.3\"}}},\"ResultURL\":\"http:\\/\\/www.ip138.com\\/\",\"Weight\":\"2\",\"Sort\":\"1\",\"SrcID\":\"5809\",\"ClickNeed\":\"0\",\"SubResult\":[],\"SubResNum\":\"0\",\"ar_passthrough\":[],\"RecoverCacheTime\":\"0\"}],\"QueryID\":\"172091755\",\"Srcid\":\"5809\",\"status\":\"0\",\"data\":[{\"srcid\":\"5809\",\"resourceid\":\"5809\",\"OriginQuery\":\"3.3.3.3\",\"origipquery\":\"3.3.3.3\",\"query\":\"3.3.3.3\",\"origip\":\"3.3.3.3\",\"location\":\"\\u7f8e\\u56fd \\u4e9a\\u9a6c\\u900a\\u4e91\",\"userip\":\"\",\"showlamp\":\"1\",\"tplt\":\"ip\",\"titlecont\":\"IP\\u5730\\u5740\\u67e5\\u8be2\",\"realurl\":\"http:\\/\\/www.ip138.com\\/\",\"showLikeShare\":\"1\",\"shareImage\":\"1\"}]})";
        //提取符合条件的以{开头，}]}结尾的字符串，用正则表达式表示这个规则
        String regex = "\\{(.*?)\\}\\]\\}";
        //基于正则表达式创建模板
        Pattern ipPattern = Pattern.compile(regex);
        //基于模板创建匹配器
        Matcher ipMatcher = ipPattern.matcher(ipResult);
        //进行查找
        ipMatcher.find();
//        System.out.println(ipMatcher);
        //查找结果分成两部分，放在匹配结果数组里，一个是包含{ }]}的内容,group(0)
        String ipJson = ipMatcher.group(0);
        System.out.println(ipMatcher.group(0));
        //一个是只包含(.*?)里面的内容,group(1)也就是通过正则表达式里的()进行区分，一个小括号就是一个group。
        System.out.println(ipMatcher.group(1));

        //解析json
//        //把json中的内容，存储到Map里面
//        Map<String,String> ipMap = new HashMap<>();
//        //将一个字符串，解析成为jsonObject对象
//        JSONObject ipJsonObject = JSON.parseObject(ipJson);
//        //遍历jsonobject
//        for (String key : ipJsonObject.keySet()) {
////            System.out.println(key + "的值是" + ipJsonObject.get(key));
//            ipMap.put(key, ipJsonObject.get(key).toString());
//        }
//        System.out.println("转换成map格式的结果是：" + ipMap);
//
//        //已知data存储的内容是一个数据，那么解析的时候，可以把data获取为一个JsonArray对象
//        JSONArray resultArray = ipJsonObject.getJSONArray("Result");
////        System.out.println("Result数组的值是" + resultArray);
//        JSONObject resultJson = resultArray.getJSONObject(0);
//        System.out.println("resultJson的结果是：" + resultJson);
//        //对于子json的解析
//        Map<String, Object> resultMap = new HashMap<>();
//        for (String resultKey : resultJson.keySet()) {
//            resultMap.put(resultKey, resultJson.get(resultKey));
//
//        }
//        System.out.println("子Json的值是：" + resultMap);


    }
}
