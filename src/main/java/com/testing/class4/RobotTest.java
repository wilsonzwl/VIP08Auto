package com.testing.class4;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @Classname RobotTest
 * @Description 类型说明
 * @Date 2021/8/6 18:22
 * @Created by zhangwenliang
 */
public class RobotTest {
    public static void main(String[] args) throws AWTException {
        Robot rb = new Robot();
        rb.mouseMove(489, 539);
        rb.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
        rb.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
        rb.keyPress(KeyEvent.VK_4);
        rb.keyRelease(KeyEvent.VK_4);
        rb.delay(2000);
        rb.keyPress(KeyEvent.VK_BACK_SPACE);
        rb.keyRelease(KeyEvent.VK_BACK_SPACE);

    }
}
