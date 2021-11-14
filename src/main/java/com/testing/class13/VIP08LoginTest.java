package com.testing.class13;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;

/**
 * @Classname VIP08LoginTest
 * @Description 持有相同cookie的请求，服务端用同一个session处理其请求；持有不同cookie的请求，服务端用不同的session处理。
 * @Date 2021/8/18 13:28
 * @Created by zhangwenliang
 */
public class VIP08LoginTest {

    public static void main(String[] args) throws Exception {
        //创建一个公用的cookiestore, 类似于存储cookie的钱包
        BasicCookieStore purse = new BasicCookieStore();
        //第一次请求，roy带上purse去请求，把cookie装到purse里面
        CloseableHttpClient roy = HttpClients.custom().setDefaultCookieStore(purse).build();
        HttpPost login = new HttpPost("http://localhost:8080/VIPLogin1/Login");
        StringEntity loginParam = new StringEntity("username=Will&password=123456");
        loginParam.setContentType("application/x-www-form-urlencoded");
        loginParam.setContentEncoding("UTF-8");
        login.setEntity(loginParam);
        CloseableHttpResponse execute = roy.execute(login);
        String result = EntityUtils.toString(execute.getEntity(), "UTF-8");
        System.out.println("第一次登录的结果：" + result);

        //这里注意一点，purse.getCookies()取出来的cookie的key为JSESSIONID，value为E5687A80E56C23F58F609155F14783B6
        //Header[] headers = execute.getHeaders("Set-Cookie");
        //            cookies = headers;
        //            for (Header cookie : cookies) {
        //                AutoLogger.log.info("cookie的键是：" + cookie.getName() + ", cookie的值是：" + cookie.getValue());
        //            }
        //execute.getHeaders("Set-Cookie")取出来的cookie的key为Set-Cookie，value为JSESSIONID=E5687A80E56C23F58F609155F14783B6
        for (Cookie cookie : purse.getCookies()) {
            System.out.println(cookie.getName() + ":" + cookie.getValue() + ":" + cookie.getDomain());
        }

        //进行第二次请求
        //将cookiestore传递给第二个client来进行使用
        CloseableHttpClient royswife = HttpClients.custom().setDefaultCookieStore(purse).build();
        HttpPost login1 = new HttpPost("http://localhost:8080/VIPLogin1/Login");
        StringEntity loginParam1 = new StringEntity("username=Will&password=123456");
        loginParam1.setContentType("application/x-www-form-urlencoded");
        loginParam1.setContentEncoding("UTF-8");
        login1.setEntity(loginParam1);
        CloseableHttpResponse execute1 = royswife.execute(login1);
        String result1 = EntityUtils.toString(execute1.getEntity(), "UTF-8");
        System.out.println("第二次登录的结果：" + result1);

        //进行第三次请求
        //不使用已经保存的cookiestore
        CloseableHttpClient will = HttpClients.custom().build();
        HttpPost login2 = new HttpPost("http://localhost:8080/VIPLogin1/Login");
        StringEntity loginParam2 = new StringEntity("username=Will&password=123456");
        loginParam2.setContentType("application/x-www-form-urlencoded");
        loginParam2.setContentEncoding("UTF-8");
        login2.setEntity(loginParam1);
        CloseableHttpResponse execute2 = will.execute(login2);
        String result2 = EntityUtils.toString(execute2.getEntity(), "UTF-8");
        System.out.println("第三次登录的结果：" + result2);



    }

    /**
     * 手动添加cookie的操作方式
     *
     * @param args
     * @throws Exception
     */
    public static void main3(String[] args) throws Exception {
        //两个请求用不同的client进行请求，通过自己添加cookie的方式操作
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost login = new HttpPost("http://localhost:8080/VIPLogin1/Login");
        StringEntity loginParam = new StringEntity("username=Will&password=123456");
        loginParam.setContentType("application/x-www-form-urlencoded");
        loginParam.setContentEncoding("UTF-8");
        login.setEntity(loginParam);
        CloseableHttpResponse execute = client.execute(login);
        //获取返回头 Set-Cookie
        //格式是：Set-Cookie: JSESSIONID=E5687A80E56C23F58F609155F14783B6; Max-Age=120; Expires=Wed, 18-Aug-2021 08:36:18 GMT
        Header[] headers = execute.getHeaders("Set-Cookie");
        String value = null;
        for (Header header : headers) {
            System.out.println("头域的键值对是" + header.getName() + "值是" + header.getValue());
            value = header.getValue();
            value = value.substring(0, value.indexOf(";"));
            System.out.println("取出来的结果是：" + value);
        }
        String result = EntityUtils.toString(execute.getEntity(), "UTF-8");
        System.out.println("第一次登录的结果：" + result);

        CloseableHttpClient roy = HttpClients.createDefault();
        HttpPost login1 = new HttpPost("http://localhost:8080/VIPLogin1/Login");
        StringEntity loginParam1 = new StringEntity("username=Will&password=123456");
        loginParam1.setContentType("application/x-www-form-urlencoded");
        loginParam1.setContentEncoding("UTF-8");
        login1.setEntity(loginParam1);
        //添加cookie头域，携带cookie，格式是：Cookie: JSESSIONID=13497953817EB2F2EB0C388F9AD596DA
        login1.addHeader("Cookie", value);
        CloseableHttpResponse execute1 = roy.execute(login1);
        String result1 = EntityUtils.toString(execute1.getEntity(), "UTF-8");
        System.out.println("第二次登录的结果：" + result1);
    }


    public static void main2(String[] args) throws Exception {
        //两个请求用相同的client来进行执行
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost login = new HttpPost("http://localhost:8080/VIPLogin1/Login");
        StringEntity loginParam = new StringEntity("username=Will&password=123456");
        loginParam.setContentType("application/x-www-form-urlencoded");
        loginParam.setContentEncoding("UTF-8");
        login.setEntity(loginParam);
        CloseableHttpResponse execute = client.execute(login);
        String result = EntityUtils.toString(execute.getEntity(), "UTF-8");
        System.out.println("第一次登录的结果：" + result);

        //如果用相同的client进行执行，那么client会保存之前请求的cookie，并且在之后的请求中带上
        HttpPost login1 = new HttpPost("http://localhost:8080/VIPLogin1/Login");
        StringEntity loginParam1 = new StringEntity("username=Will&password=123456");
        loginParam1.setContentType("application/x-www-form-urlencoded");
        loginParam1.setContentEncoding("UTF-8");
        login1.setEntity(loginParam1);
        CloseableHttpResponse execute1 = client.execute(login1);
        String result1 = EntityUtils.toString(execute1.getEntity(), "UTF-8");
        System.out.println("第二次登录的结果：" + result1);
    }

    public static void main1(String[] args) throws Exception {
        //两个请求用两个不同的client来进行执行
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost login = new HttpPost("http://localhost:8080/VIPLogin1/Login");
        StringEntity loginParam = new StringEntity("username=Will&password=123456");
        loginParam.setContentType("application/x-www-form-urlencoded");
        loginParam.setContentEncoding("UTF-8");
        login.setEntity(loginParam);
        CloseableHttpResponse execute = client.execute(login);
        String result = EntityUtils.toString(execute.getEntity(), "UTF-8");
        System.out.println("第一次登录的结果：" + result);

        CloseableHttpClient roy = HttpClients.createDefault();
        HttpPost login1 = new HttpPost("http://localhost:8080/VIPLogin1/Login");
        StringEntity loginParam1 = new StringEntity("username=Will&password=123456");
        loginParam1.setContentType("application/x-www-form-urlencoded");
        loginParam1.setContentEncoding("UTF-8");
        login1.setEntity(loginParam1);
        CloseableHttpResponse execute1 = roy.execute(login1);
        String result1 = EntityUtils.toString(execute1.getEntity(), "UTF-8");
        System.out.println("第二次登录的结果：" + result1);
    }
}
