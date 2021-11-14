package com.testing.class4;

import com.google.common.io.Files;
import com.testing.common.AutoLogger;
import com.testing.web.WebKeyword;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Classname ShopAdminTest
 * @Description 类型说明
 * @Date 2021/8/6 12:29
 * @Created by zhangwenliang
 */
public class ShopAdminTest {
    public static void main(String[] args) throws Exception {
        WebKeyword web = new WebKeyword();
        AutoLogger.log.info("*****************************自动化测试开始啦******************************");
        web.openBrowser("chrome");
        visitAdmin(web);
        adminLogin(web);
        adminAdd(web);

        web.halt("5");
        web.closeBrowser();


    }

    public static void visitAdmin(WebKeyword web) {
        web.visitURL("http://www.testingedu.com.cn:8000/Admin/Admin/login");
    }

    public static void adminAdd(WebKeyword web) {
        //2、进入商城菜单
        web.click("//a[text()='商城']");
        web.switchIframe("//iframe[@id='workspace']");
        web.click("//span[string()='添加商品']");
        //生成日期格式拼接商品名
        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        SimpleDateFormat goodsNum = new SimpleDateFormat("ddHHmm");
        String format = goodsNum.format(date);
        System.out.println(format);


        //3、填写必填选项
        //生成随机数拼接商品名
//        Random random = new Random();
//        String[] availStr = {"roy", "will", "土匪", "卡卡"};
//        int number = random.nextInt(availStr.length - 1);
//        System.out.println("获取到的随机值是：" + availStr[number]);
        String goodsName = "VIP08goods" + format;
        web.input("//input[@name='goods_name']", goodsName);
        web.select("//select[@name='cat_id']", "text", "服饰");
        web.halt("1");
        web.select("//select[@name='cat_id_2']", "value", "23");
        web.halt("1");
        web.select("//select[@name='cat_id_3']", "text", "牛仔裤");
        //尝试通过点击来选择下拉框 -- 尝试失败
//        web.click("//select[@name='cat_id_3']");
//        web.click("//option[text()='牛仔裤']");
//        web.halt("0.5");
//        web.runJsWithElement("click()", "//option[text()='牛仔裤']");
        web.input("//input[@name='shop_price']", "300");
        web.input("//input[@name='market_price']", "330");

        //点击选择上传
        web.click("//input[@id='button1']/following-sibling::input");
        //切换iframe
        web.switchIframe("//iframe[contains(@id, 'layui')]");
        //点击选择文件
//        web.click("//div[text()='点击选择文件']/following-sibling::div/label");
        //1.1 通过robot来进行点击完成选择
//        web.halt("2");
//        web.moveAndclickRB(863,257);
//        web.moveAndclickRB(896,975);
        //1.2 通过autoit,编译出exe文件，然后调用实现。web.halt("3");
//        web.halt("3");
//        Runtime runner = Runtime.getRuntime();
//        runner.exec("D:\\study\\software\\upload\\upload.exe");
        web.input("//div[text()='点击选择文件']/following-sibling::div/input", "D:\\study\\software\\upload\\computer2.jpg");
        web.click("//div[text()='确定使用']");
        //切换iframe，回到workspace
        web.switchUpIframe();
        web.click("//label[text()='是否包邮']/../following-sibling::dd//label[text()='是']");
        web.switchIframe("//iframe[@id='ueditor_0']");
//        web.input("//p", "VIP08test");
        web.runJs("document.getElementsByTagName('p')[0].innerText='test'");
        web.switchUpIframe();
        web.click("//a[@id='submit']");

        web.assertMysqlData("select goods_id, shop_price, goods_name from tp_goods where goods_name = '" + goodsName + "'", "goods_id");
    }

    public static void adminLogin(WebKeyword web) {
        //1、登录
        web.input("//input[@name='username']", "admin");
        web.input("//input[@name='password']", "123456");
        web.input("//input[@name='vertify']", "1");
        web.click("//input[@name='submit']");
    }
}
