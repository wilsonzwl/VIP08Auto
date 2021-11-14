package com.testing.class13;

import com.testing.common.AutoLogger;
import com.testing.inter.HttpClientUtils;

/**
 * @Classname TestCookie
 * @Description 类型说明
 * @Date 2021/8/18 19:51
 * @Created by zhangwenliang
 */
public class TestCookie {
    public static void main(String[] args) {
        HttpClientUtils roy = new HttpClientUtils();
        String result1 = roy.doPost("http://localhost:8080/VIPLogin1/Login", "urlencoded", "username=Will&password=123456");
        AutoLogger.log.info(result1);
        String result2 = roy.doPost("http://localhost:8080/VIPLogin1/Login", "urlencoded", "username=Will&password=123456");
        AutoLogger.log.info(result2);
        roy.noUseCookie();
        String result3 = roy.doPost("http://localhost:8080/VIPLogin1/Login", "urlencoded", "username=Will&password=123456");
        AutoLogger.log.info(result3);

        HttpClientUtils will = new HttpClientUtils();
        String loginResut = will.doPost("http://www.testingedu.com.cn:8000/index.php?m=Home&c=User&a=do_login&t=0.21076744785226076",
                "urlencoded", "username=13800138006&password=123456&verify_code=1");
        AutoLogger.log.info(loginResut);
        String cartResult = will.doget("http://www.testingedu.com.cn:8000/index.php?m=Home&c=Cart&a=header_cart_list", "use");
        AutoLogger.log.info(cartResult);

        String cartresult2=will.doget("http://www.testingedu.com.cn:8000/index.php?m=Home&c=Cart&a=header_cart_list","not");
        AutoLogger.log.info(cartresult2);

        String cartresult3=will.doget("http://www.testingedu.com.cn:8000/index.php?m=Home&c=Cart&a=header_cart_list","use");
        AutoLogger.log.info(cartresult3);


    }
}
