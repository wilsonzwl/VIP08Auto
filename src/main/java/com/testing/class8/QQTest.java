package com.testing.class8;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Classname QQTest
 * @Description 类型说明
 * @Date 2021/8/10 22:21
 * @Created by zhangwenliang
 */
public class QQTest {
    public static void main(String[] args) {
        //1、启动appium服务端
        String rootPath = System.getProperty("user.dir");
        System.out.println(rootPath);
        String logPath = rootPath + "/logs/appium.log";
        System.out.println(logPath);

        //在脚本中执行cmd命令
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("cmd /c start appium -p 50111 -g " + logPath + " --local-timezone --log-timestamp");
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //2、客户端连接服务端完成应用启动和测试
        DesiredCapabilities qqcap = new DesiredCapabilities();
        qqcap.setCapability("platformName", "Android");
        qqcap.setCapability("platformVersion", "5.1.1");
        qqcap.setCapability("deviceName", "127.0.0.1:62001");
        qqcap.setCapability("appPackage", "com.tencent.mobileqq");
        qqcap.setCapability("appActivity", ".activity.SplashActivity");
        qqcap.setCapability("noReset", true);

        try {
            URL appiumServerurl = new URL("http://127.0.0.1:50111/wd/hub");
//            AndroidDriver driver = new AndroidDriver<>(appiumServerurl, qqcap);
//            MobileElement el1 = (MobileElement) driver.findElementById("com.tencent.mobileqq:id/kag");
//            el1.click();
//            MobileElement el4 = (MobileElement) driver.findElementById("com.tencent.mobileqq:id/et_search_keyword");
//            el4.sendKeys("roy");

            WebDriver driver = new AndroidDriver<>(appiumServerurl, qqcap);

            Thread.sleep(5000);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

//            List<WebElement> elements = driver.findElements(By.cssSelector("[content-desc='搜索']"));
//            for (WebElement element : elements) {
//                System.out.println(element.getAttribute("content-desc"));
//            }

//            WebElement searchinput = driver.findElement(MobileBy.AccessibilityId("搜索"));
            driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().className(\"android.widget.TextView\").textContains(\"搜索\").resourceId(\"com.tencent.mobileqq:id/kag\")")).click();



//            MobileElement el1 = (MobileElement) driver.findElement(By.id("com.tencent.mobileqq:id/kag"));
//            el1.click();
            Thread.sleep(5000);
            MobileElement el4 = (MobileElement) driver.findElement(By.id("com.tencent.mobileqq:id/et_search_keyword"));
            el4.clear();
            el4.sendKeys("roy");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
