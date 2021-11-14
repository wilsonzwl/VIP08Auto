package com.testingedu.vip.samplers;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/**
 * @Classname HelloSample
 * @Description 类型说明
 * @Date 2021/10/27 16:16
 * @Created by zhangwenliang
 */
public class HelloSample extends AbstractJavaSamplerClient {


    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sr = new SampleResult();
        //表示开始计时
        sr.sampleStart();
        //设置请求数据，最终显示在查看结果树中的内容
        sr.setSamplerData("这是请求数据");
        //设置返回数据，最终显示在查看结果树中的内容
        sr.setResponseData("这是服务器返回的内容", SampleResult.TEXT);
        //设置请求的结束状态
        sr.setSuccessful(true);
        //表示结束计时
        sr.sampleEnd();
        
        return sr;
    }
}
