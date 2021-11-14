package com.testing.class12;

import com.alibaba.fastjson.JSONPath;
import com.testing.common.AutoLogger;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

/**
 * @Classname UploadPost
 * @Description 类型说明
 * @Date 2021/8/17 13:02
 * @Created by zhangwenliang
 */
public class UploadPost {
    public static void main(String[] args) {
        CloseableHttpClient client = HttpClients.createDefault();
        //设置请求行
        HttpPost uploadPost = new HttpPost("http://www.testingedu.com.cn:8000/index.php/home/Uploadify/imageUp/savepath/head_pic/pictitle/banner/dir/images.html");
        //multipart请求体的设置，通过multipartEntityBuilder来设置
        MultipartEntityBuilder meb = MultipartEntityBuilder.create();
        meb.addTextBody("id", "WU_FILE_0");
        meb.addTextBody("name", "微信截图_20210817114841.png");
        meb.addTextBody("type", "image/png");
        meb.addTextBody("lastModifiedDate", "Tue Aug 17 2021 11:48:44 GMT+0800 (中国标准时间)");
        meb.addTextBody("size", "374661");
        meb.addBinaryBody("file", new File("D:\\微信截图_20210817114841.png"));
        //完成multipart请求体的构建
        HttpEntity fileContent = meb.build();

        //将创建好的请求体，加到请求包（行）里面
        uploadPost.setEntity(fileContent);
        //由于boundry是httpclient自动生成的，会自己带上content-type，所以，千万不要自己再指定一次。
//        uploadPost.setHeader("Content-Type", "multipart/form-data");

        try {
            CloseableHttpResponse execute = client.execute(uploadPost);
            String result = EntityUtils.toString(execute.getEntity(), "UTF-8");
            System.out.println("返回的结果是：" + result);
            String state = JSONPath.read(result, "$.state").toString();
            if ("SUCCESS".equals(state)) {
                AutoLogger.log.info("pass");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
