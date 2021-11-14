package com.testing.class9;

import com.testing.app.AppKeyword;

/**
 * @Classname QQkeyWordTest
 * @Description 类型说明
 * @Date 2021/8/11 17:48
 * @Created by zhangwenliang
 */
public class QQkeyWordTest {
    public static void main(String[] args) {
        AppKeyword app = new AppKeyword();
        app.startAppiumServer("50111", "10");
        app.runAPP("127.0.0.1:62001", "5.1.1", "com.tencent.mobileqq", ".activity.SplashActivity", "50111", "5");
        app.halt("15");
        app.click("//android.widget.EditText[@content-desc=\"搜索\"]");
        app.click("//android.widget.TextView[contains(@text, '风中叶')]");
        app.click("//android.widget.ImageView[@content-desc=\"聊天设置\"]");
        app.halt("10");
        app.click("//android.widget.TextView[@text=\"风中叶\"]");
        app.click("//android.widget.TextView[@text=\"我的QQ空间\"]");

        //等待空间加载
        app.halt("15");

        app.adbSwipe("351", "1474", "344", "50", "1000");

        //点赞
        app.multiByclick("id", "com.tencent.mobileqq:id/c7p");
        app.halt("10");

        //点击评论
        app.multiByclick("xpath", "//*[@resource-id='com.tencent.mobileqq:id/c6e']");

        app.halt("10");
        //发送评论
        app.input("//android.widget.EditText[@resource-id='com.tencent.mobileqq:id/h_0']", "棒棒哒");
        app.halt("10");
        app.click("//android.widget.Button[@text='发送']");
        app.halt("10");

        app.quitApp();
        app.killAppium();


    }
}
