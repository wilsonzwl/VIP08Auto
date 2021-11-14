package com.testing.class18;

import com.testing.web.WebKeyword;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @Classname TpAdminTest
 * @Description 类型说明
 * @Date 2021/8/23 19:59
 * @Created by zhangwenliang
 */
public class TpAdminTest {

    public WebKeyword web;

    @BeforeClass
    public void openBrowser() {
        web = new WebKeyword();
        web.driver = TpshopTest.driver;
//        web.openBrowser("chrome");

    }

    @Test(priority = 0)
    @Parameters("houtai")
    public void visitAdmin(String houtai) {
        web.visitURL(houtai);
    }

    @Test(priority = 1)
    @Parameters({"password"})
    public void adminLogin(String password) {
        web.input("//input[@name='username']","admin");
        web.input("//input[@name='password']",password);
        web.input("//input[@name='vertify']","1");
        web.click("//input[@name='submit']");
        Assert.assertTrue(web.assertPageContains("商城"));
    }

    @AfterClass
    public void closeBrowser() {
//        web.closeBrowser();
    }
}
