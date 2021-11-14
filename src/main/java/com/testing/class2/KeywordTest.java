package com.testing.class2;

import com.testing.web.WebKeyword;

import java.util.List;
import java.util.Map;

/**
 * @Classname KeywordTest
 * @Description 类型说明
 * @Date 2021/8/4 15:53
 * @Created by zhangwenliang
 */
public class KeywordTest {
    public static void main(String[] args) {
        WebKeyword web = new WebKeyword();
        web.openBrowser("chrome");
        web.visitURL("https://www.baidu.com");
        web.input("//input[@id='kw']", "特斯汀");
        web.halt("1");
//        web.click("#su");
        web.click("id", "su");
        web.halt("1");
        web.exWaitTextToContains();
        Map<String, Boolean> textandresult = web.getText("//div[@id='content_left']/div[3]//h3/a", "哔哩哔哩", "百度一下", "深圳");
        System.out.println(textandresult);
        List<String> allText = web.getAllText("//div[@id='content_left']/div//h3/a");
        System.out.println("获取到所有的搜索结果是：" + allText);
        web.halt("3");
        web.closeBrowser();

    }
}
