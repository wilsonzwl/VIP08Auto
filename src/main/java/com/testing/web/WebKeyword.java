package com.testing.web;

import com.google.common.io.Files;
import com.testing.common.AutoLogger;
import com.testing.common.ExcelReader;
import com.testing.webDriver.FFDriver;
import com.testing.webDriver.GoogleDriver;
import com.testing.webDriver.IEDriver;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Classname WebKeyword
 * @Description web自动化常用关键字
 * @Date 2021/8/4 13:02
 * @Created by zhangwenliang
 */
public class WebKeyword {

    //所有方法都要用到，driver对象
    public WebDriver driver = null;

    public void halt(String seconds) {
        try {
            int time = Integer.parseInt(seconds);
            Thread.sleep(time * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐式等待
     *
     * @param seconds
     */
    public void imWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    /**
     * 显式等待
     */
    public void exWaitTextToBe() {

        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.textToBe(By.xpath("//div[@id='content_left']/div[3]/h3/a"), "特斯汀豪斯科技（深圳）有限公司"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显式等待
     */
    public void exWaitTextToContains() {

        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(new ExpectedCondition<Boolean>() {
                @NullableDecl
                @Override
                public Boolean apply(@NullableDecl WebDriver webDriver) {
                    try {
                        String text = driver.findElement(By.xpath("//div[@id='content_left']/div[3]//h3/a")).getText();
                        return text.contains("豪斯科技");
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开浏览器的方法
     */
    public void openBrowser(String browser) {

        switch (browser) {
            case "chrome":
                GoogleDriver gg = new GoogleDriver("webDriverExe\\chromedriver.exe");
                driver = gg.getdriver();
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                AutoLogger.log.info("***************chrome浏览器启动了****************");
                break;
            case "ff":
                FFDriver ff = new FFDriver("", "webDriverExe\\geckodriver.exe");
                driver = ff.getdriver();
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                break;
            case "ie":
                IEDriver ie = new IEDriver("webDriverExe\\IEdriver");
                driver = ie.getdriver();
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", "webDriverExe\\msedgedriver.exe");
                driver = new EdgeDriver();
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                break;
            default:
                GoogleDriver gdefault = new GoogleDriver("webDriverExe\\chromedriver.exe");
                driver = gdefault.getdriver();
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                break;
        }
    }

    public void visitURL(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("url访问失败，请检查输入url是否带了协议。");
        }

    }

    public boolean input(String xpath, String content) {
        try {
            WebElement input = driver.findElement(By.xpath(xpath));
            input.clear();
            input.sendKeys(content);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("向" + xpath + "输入内容的时候失败。");
            takeScreen("input");
            return false;
        }

    }

    public void clickcss(String cssSelector) {
        try {
            driver.findElement(By.cssSelector(cssSelector)).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean click(String xpath) {
        try {
            driver.findElement(By.xpath(xpath)).click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            takeScreen("click");
            return false;
        }
    }

    //常用的定位方法，能够基于输入，进行选择，用对应的方法调用定位表达式。
    public void click(String by, String Locator) {
        try {
            switch (by) {
                case "id":
                    driver.findElement(By.id(Locator)).click();
                    break;
                case "name":
                    driver.findElement(By.name(Locator)).click();
                    break;
                case "tag":
                    driver.findElement(By.tagName(Locator)).click();
                    break;
                case "classname":
                    driver.findElement(By.className(Locator)).click();
                    break;
                case "xpath":
                    driver.findElement(By.xpath(Locator)).click();
                    break;
                case "css":
                    driver.findElement(By.xpath(Locator)).click();
                    break;
                default:
                    driver.findElement(By.xpath(Locator)).click();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //悬停方法
    public void hover(String xpath) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(By.xpath(xpath))).perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过via参数来选择select选择选项的方式
     *
     * @param via
     * @param xpath
     * @param content 选择的参数内容。
     */
    public void select(String xpath, String via, String content) {
        try {
            WebElement selele = driver.findElement(By.xpath(xpath));
            //通过实例化select对象，将webelement元素，转换为select
            Select sel = new Select(selele);
            switch (via) {
                case "value":
                    sel.selectByValue(content);
                    break;
                case "text":
                    sel.selectByVisibleText(content);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 基于value值来进行下拉框选择。
     *
     * @param xpath
     * @param Value
     */
    public void selectByValue(String xpath, String Value) {
        try {
            WebElement selele = driver.findElement(By.xpath(xpath));
            Select sel = new Select(selele);
            sel.selectByValue(Value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*******************************************窗口与iframe切换*******************************/

    /**
     * 通过浏览器页面的标题来进行窗口的切换
     *
     * @param expectedTitle
     */
    public void switchWindowByTitle(String expectedTitle) {
        try {
            //先获取所有的句柄
            Set<String> windowHandles = driver.getWindowHandles();
            //遍历所有的句柄，尝试切换窗口获取它的标题，如果标题和预期一致就是需要的窗口
            for (String windowHandle : windowHandles) {
                //先切换到当前句柄所代表的窗口
                driver.switchTo().window(windowHandle);
                //判断当前窗口的标题是否等于预期标题，如果相等，则break退出循环，不再遍历后续句柄。
                if (driver.getTitle().equals(expectedTitle)) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 基于窗口顺序进行切换
     *
     * @param index 想要切换到的窗口的顺序。
     */
    public void switchWindowByindex(String index) {
        try {
            int targetIndex = Integer.parseInt(index);
            Set<String> windowHandles = driver.getWindowHandles();
            int count = 0;
            for (String window : windowHandles) {
                count++;
                if (count == targetIndex) {
                    System.out.println("这是第" + targetIndex + "个句柄" + window);
                    driver.switchTo().window(window);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 基于xpath切换Iframe
     *
     * @param xpath
     */
    public boolean switchIframe(String xpath) {
        try {
            driver.switchTo().frame(driver.findElement(By.xpath(xpath)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 切换到父级iframe
     */
    public void switchUpIframe() {
        try {
            driver.switchTo().parentFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 切换到html根层级
     */
    public void switchOutIframe() {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //执行js
    public void runJs(String jsScript) {
        try {
            JavascriptExecutor runner = (JavascriptExecutor) driver;
            runner.executeScript(jsScript);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runJsWithElement(String method, String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        JavascriptExecutor runner = (JavascriptExecutor) driver;
        runner.executeScript("arguments[0]." + method, element);
    }


    public Map<String, Boolean> getText(String xpath, String... expect) {
        try {
            String text = driver.findElement(By.xpath(xpath)).getText();
            Boolean result = false;
            if (expect.length > 0) {
                for (String ex : expect) {
                    if (text.contains(ex)) {
                        result = true;
                    }
                }
            }
            Map<String, Boolean> textAndresult = new HashMap<>();
            textAndresult.put(text, result);
            return textAndresult;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Boolean> noresult = new HashMap<>();
            noresult.put("没有结果", false);
            return noresult;
        }
    }

//
//    public String getText(String xpath) {
//        try {
//            String text = driver.findElement(By.xpath(xpath)).getText();
//            return text;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "获取元素文本内容失败。";
//        }
//    }

    /**
     * 断言某个元素文本内容是否包含或者等于预期值
     */
    public boolean assertText(String xpath, String method, String expect) {
        String text = "";
        boolean result = false;
        try {
            text = driver.findElement(By.xpath(xpath)).getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (method) {
            case "=":
                if (text.equals(expect)) {
                    System.out.println("测试成功");
                    result = true;
                } else {
                    System.out.println("测试失败");
                    result = false;
                }
                break;
            case "包含":
                if (text.contains(expect)) {
                    System.out.println("测试成功");
                    result = true;
                } else {
                    System.out.println("测试失败");
                    result = false;
                }
                break;
        }

        return result;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    //获取多个元素的文本内容
    public List<String> getAllText(String xpath) {
        List<WebElement> elements = driver.findElements(By.xpath(xpath));
        List<String> results = new ArrayList<>();
        for (WebElement element : elements) {
            results.add(element.getText());
            element.click();
        }
        return results;
    }

    public boolean assertPageContains(String expect) {
        if (driver.getPageSource().contains(expect)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 输入sql语句进行查询，判断结果正确性
     */
    public boolean assertMysqlData(String sql, String expectData) {
        //先查询数据库获取数据
        ExcelReader.MysqlUtils mysql = new ExcelReader.MysqlUtils();
        mysql.createConnector();
        List<Map<String, String>> results = mysql.queryResult(sql);
        System.out.println("完整查询结果是" + results);
        //判断结果是否符合用例预期
        try {
            String s = results.get(0).get(expectData);
            if (s != null && !s.equals("")) {
                System.out.println("获取到" + expectData + "的值是：" + s);
                System.out.println("测试校验通过");
                return true;
            } else {
                System.out.println("测试失败");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("测试失败");
            return false;
        }
    }

    /**
     * 将浏览器截图保存在指定的目录下，文件名格式为指定的时间+报错方法
     *
     * @param method 报错方法
     */
    public void takeScreen(String method) {
        //截图操作
        TakesScreenshot screenshot = (TakesScreenshot) (driver);
        File screenPic = screenshot.getScreenshotAs(OutputType.FILE);
        File savePic = new File("logs/Screenshot/" + method + createTime("MMdd+HH-mm") + ".png");
        try {
            Files.copy(screenPic, savePic);
        } catch (IOException e) {
            System.out.println("截图失败，检查一下文件格式。");
            e.printStackTrace();
        }

    }

    /**
     * 用于生成指定格式的字符串
     *
     * @param format
     * @return
     */
    public String createTime(String format) {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String result = sdf.format(now);
//        System.out.println(format);
        return result;
    }


    public void closeBrowser() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebKeyword web) {
        driver = web.getDriver();
    }

    public void moveAndclickRB(int x, int y) {
        RobotUtils rb = new RobotUtils();
        rb.moveToclick(x, y);
    }


}
