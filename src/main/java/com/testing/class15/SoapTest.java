package com.testing.class15;

import com.testing.inter.InterKw;

/**
 * @Classname SoapTest
 * @Description 类型说明
 * @Date 2021/8/20 16:20
 * @Created by zhangwenliang
 */
public class SoapTest {
    public static void main(String[] args) {
        InterKw roy = new InterKw();
        roy.doPostSoap("http://localhost:8080/Inter/SOAP?wsdl",
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                        "xmlns:soap=\"http://soap.testingedu.com/\"><soapenv:Header/><soapenv:Body><soap:auth>" +
                        "</soap:auth></soapenv:Body></soapenv:Envelope>");
        roy.saveParam("tokenV", "$.token");
        roy.addHeader("{\"token\":\"{tokenV}\"}");
        roy.saveEncPwd("enPwd", "123456");
//        roy.saveRandomParam("userR", "WenLiang");
        roy.doPostSoap("http://localhost:8080/Inter/SOAP?wsdl", "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.testingedu.com/\"><soapenv:Header/><soapenv:Body><soap:login><arg0>Will</arg0><arg1>{enPwd}</arg1></soap:login></soapenv:Body></soapenv:Envelope>");
    }
}
