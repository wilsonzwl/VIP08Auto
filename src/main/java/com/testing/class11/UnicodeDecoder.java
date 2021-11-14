package com.testing.class11;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname UnicodeDecoder
 * @Description 类型说明
 * @Date 2021/8/15 14:46
 * @Created by zhangwenliang
 */
public class UnicodeDecoder {

    public static void main(String[] args) {
        int i = Integer.parseInt("7f8e", 16);
//        System.out.println((char) i);

        String s = unicodeDecoder("jQuery110205064722294527677_1628995514459({\"ResultCode\":\"0\",\"ResultNum\":\"1\",\"Result\":[{\"DisplayData\":{\"strategy\":{\"tempName\":\"ip\",\"precharge\":\"0\",\"ctplOrPhp\":\"1\"},\"resultData\":{\"tplData\":{\"srcid\":\"5809\",\"resourceid\":\"5809\",\"OriginQuery\":\"3.3.3.3\",\"origipquery\":\"3.3.3.3\",\"query\":\"3.3.3.3\",\"origip\":\"3.3.3.3\",\"location\":\"\\u7f8e\\u56fd \\u4e9a\\u9a6c\\u900a\\u4e91\",\"userip\":\"\",\"showlamp\":\"1\",\"tplt\":\"ip\",\"titlecont\":\"IP\\u5730\\u5740\\u67e5\\u8be2\",\"realurl\":\"http:\\/\\/www.ip138.com\\/\",\"showLikeShare\":\"1\",\"shareImage\":\"1\",\"data_source\":\"AE\"},\"extData\":{\"tplt\":\"ip\",\"resourceid\":\"5809\",\"OriginQuery\":\"3.3.3.3\"}}},\"ResultURL\":\"http:\\/\\/www.ip138.com\\/\",\"Weight\":\"2\",\"Sort\":\"1\",\"SrcID\":\"5809\",\"ClickNeed\":\"0\",\"SubResult\":[],\"SubResNum\":\"0\",\"ar_passthrough\":[],\"RecoverCacheTime\":\"0\"}],\"QueryID\":\"3188447816\",\"Srcid\":\"5809\",\"status\":\"0\",\"data\":[{\"srcid\":\"5809\",\"resourceid\":\"5809\",\"OriginQuery\":\"3.3.3.3\",\"origipquery\":\"3.3.3.3\",\"query\":\"3.3.3.3\",\"origip\":\"3.3.3.3\",\"location\":\"\\u7f8e\\u56fd \\u4e9a\\u9a6c\\u900a\\u4e91\",\"userip\":\"\",\"showlamp\":\"1\",\"tplt\":\"ip\",\"titlecont\":\"IP\\u5730\\u5740\\u67e5\\u8be2\",\"realurl\":\"http:\\/\\/www.ip138.com\\/\",\"showLikeShare\":\"1\",\"shareImage\":\"1\"}]})");
        System.out.println(s);

    }



    public static String unicodeDecoder(String origin) {
        Pattern uni=Pattern.compile("\\\\u([0-9a-fA-F]{4})");
        Matcher m=uni.matcher(origin);
        StringBuffer stringBuffer = new StringBuffer(origin.length());
        while (m.find()) {
            m.appendReplacement(stringBuffer, Character.toString((char)Integer.parseInt(m.group(1), 16)));
        }
        m.appendTail(stringBuffer);
        return stringBuffer.toString();
    }
}
