package com.testing.class4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Classname TestLog
 * @Description 类型说明
 * @Date 2021/8/6 23:14
 * @Created by zhangwenliang
 */
public class TestLog {
    public static void main(String[] args) {
        Logger log = LogManager.getLogger(TestLog.class);
        log.trace("这是trace信息");
        log.debug("debug信息来了");
        log.info("info比debug还要高");
        log.warn("警告警告，要11点了");
        log.error("这是个错误的选择");
        log.fatal("致命级别，要挂了");


    }
}
