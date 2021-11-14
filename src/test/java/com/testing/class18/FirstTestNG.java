package com.testing.class18;

import org.testng.annotations.*;

/**
 * @Classname FirstTestNG
 * @Description 类型说明
 * @Date 2021/8/23 12:37
 * @Created by zhangwenliang
 */
public class FirstTestNG {


    @BeforeTest
    public void beforeTest() {
        System.out.println("FirstTestNG类中@beforeTest方法被执行");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("FirstTestNG类中@afterTest方法被执行");
    }

    @BeforeClass
    public void beforeFirst() {
        System.out.println("FirstTestNG类中的BeforeClass方法被执行");
    }

    @AfterClass
    public void afterFirst() {
        System.out.println("FirstTestNG类中的AfterClass方法被执行");
    }



    @Test(groups = "roy")
    public void firstTestNG() {
        System.out.println("testng的第一个测试方法");
    }

    @Test
    public void secondTestNG() {
        System.out.println("testng的第二个测试方法");
    }
}
