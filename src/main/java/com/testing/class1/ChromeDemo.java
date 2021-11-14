package com.testing.class1;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.testing.webDriver.FFDriver;
import com.testing.webDriver.GoogleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Classname ChromeDemo
 * @Description 类型说明
 * @Date 2021/8/3 18:00
 * @Created by zhangwenliang
 */
public class ChromeDemo {

    public static WebDriver driver;

    public static void main(String[] args) {
        //chrome
//        System.setProperty("webdriver.chrome.driver", "webDriverExe\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();

        GoogleDriver googleDriver = new GoogleDriver("webDriverExe\\chromedriver.exe");
        driver = googleDriver.getdriver();

//        FFDriver ffDriver = new FFDriver("C:\\\\Program Files\\\\Mozilla Firefox\\\\firefox.exe","webDriverExe\\\\geckodriver.exe");
//        WebDriver driver = ffDriver.getdriver();

        //firefox
//        System.setProperty("webdriver.firefox.bin","C:\\Program Files\\Mozilla Firefox\\firefox.exe");
//        System.setProperty("webdriver.gecko.driver","webDriverExe\\geckodriver.exe");
//
//        FirefoxDriver driver = new FirefoxDriver();

        //IE
//        System.setProperty("webdriver.ie.driver","webDriverExe\\IEDriverServer.exe");
//        WebDriver driver=new InternetExplorerDriver();

        //Edge
//        System.setProperty("webdriver.edge.driver","webDriverExe\\msedgedriver.exe");
//        WebDriver driver=new EdgeDriver();

        searchBaidu("特斯汀", "特斯汀_百度搜索");
        driver.quit();


    }

    public static void searchBaidu(String content, String expect) {
        driver.get("https://www.baidu.com/");
        driver.navigate().to("https://www.51job.com/");
        driver.navigate().back();
        WebElement searchInput = driver.findElement(By.xpath("//input[@id='kw']"));
        searchInput.sendKeys(content);
        driver.findElement(By.cssSelector("#su")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String title = driver.getTitle();
        System.out.println("当前页面标题是：" + title);
        if (expect.equals(title)) {
            System.out.println("测试成功");
        } else {
            System.out.println("测试失败");
        }

        String currentUrl = driver.getCurrentUrl();
        System.out.println("当前url地址是" + currentUrl);
        try {
            String 特斯汀 = URLEncoder.encode("特斯汀", "UTF-8");
            System.out.println("转化后的特斯汀的编码是：" + 特斯汀);
            System.out.println(currentUrl.contains(特斯汀));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String pageSource = driver.getPageSource();
        System.out.println(pageSource);
        System.out.println(pageSource.contains("特斯汀"));

        //div[@id='content_left']/div[3]/h3/a
        String text = driver.findElement(By.xpath("//div[@id='content_left']/div[3]/h3/a")).getText();
        System.out.println(text);

        String href = driver.findElement(By.xpath("//div[@id='content_left']/div[3]/h3/a")).getAttribute("href");
        System.out.println(href);

        driver.findElement(By.xpath("//a[text()='百度首页']")).click();
    }
}
