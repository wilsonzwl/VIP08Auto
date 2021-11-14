package com.testing.class11;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;

/**
 * @Classname ShopLoginPost
 * @Description 类型说明
 * @Date 2021/8/15 18:07
 * @Created by zhangwenliang
 */
public class ShopLoginPost {
    public static void main(String[] args) throws Exception {
        CloseableHttpClient roy = HttpClients.createDefault();

        HttpPost loginPost = new HttpPost("http://www.testingedu.com.cn:8000/index.php?m=Home&c=User&a=do_login&t=0.21076744785226076");
        //设置请求体的内容
        StringEntity loginParam = new StringEntity("username=13800138006&password=123456&verify_code=1", "UTF-8");
        //设置请求头内容
        loginParam.setContentType("application/x-www-form-urlencoded;charset=UTF-8");
        loginParam.setContentEncoding("UTF-8");
        //请求体封装到请求包里
        loginPost.setEntity(loginParam);

        CloseableHttpResponse loginResponse = roy.execute(loginPost);
        String loginResult = EntityUtils.toString(loginResponse.getEntity(), "UTF-8");

        String s = UnicodeDecoder.unicodeDecoder(loginResult);

        System.out.println(s);


    }
}
