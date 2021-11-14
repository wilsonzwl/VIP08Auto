package com.testing.class18;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @Classname TpshopTest
 * @Description 类型说明
 * @Date 2021/8/23 17:46
 * @Created by zhangwenliang
 */
public class TpshopTest {

    public static WebDriver driver;

    @BeforeSuite
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "webDriverExe/chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions option = new ChromeOptions();
        option.setExperimentalOption("excludeSwitches", new String[] {"enable-automation","load-extension"});
        option.addArguments("--user-data-dir=D:\\IdeaProjects\\LoginTest8\\chromeData");
        option.addArguments("--start-maximized");

        driver = new ChromeDriver(option);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    //两条用例，一条访问百度
    @Parameters("qiantai")
    @Test(priority = 0)
    public void visitUrl(String qiantai) {
        driver.get(qiantai);
    }

    //一条访问电商，进行登录
    @Parameters({"password"})
    @Test(priority = 1)
    public void login(String password) {
        driver.get("http://www.testingedu.com.cn:8000");
        driver.findElement(By.xpath("//a[text()='登录']")).click();
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("13800138006");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='verify_code']")).sendKeys("1");
        driver.findElement(By.xpath("//a[@name='sbtbutton']")).click();
        driver.findElement(By.xpath("//a[text()='返回商城首页']")).click();

    }

    @AfterSuite
    public void closeBrowser() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

}