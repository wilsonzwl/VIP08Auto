package com.testing.class5;

import com.testing.web.AdminPage;
import com.testing.web.UserPage;

/**
 * @Classname AdminPageTest
 * @Description 类型说明
 * @Date 2021/8/7 19:50
 * @Created by zhangwenliang
 */
public class AdminPageTest {
    public static void main(String[] args) {
        AdminPage admin = new AdminPage();
        admin.openBrowser("chrome");
        admin.visitURL("http://www.testingedu.com.cn:8000/Admin/Admin/login");
        admin.LoginAdmin("admin", "123456", "1");
        admin.toAddMenu();
        admin.addRandomGoods();

        UserPage user = new UserPage();
        user.setDriver(admin);
        user.visitURL("http://www.testingedu.com.cn:8000/");
        user.loginUser();
        user.closeBrowser();
    }
}
