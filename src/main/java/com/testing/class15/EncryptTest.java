package com.testing.class15;

import com.testing.common.Encrypt;

/**
 * @Classname EncryptTest
 * @Description 类型说明
 * @Date 2021/8/19 22:52
 * @Created by zhangwenliang
 */
public class EncryptTest {
    public static void main(String[] args) {
        Encrypt enc = new Encrypt();
        String enPwd = enc.enCrypt("123456");
        System.out.println(enPwd);
        System.out.println(enc.deCrypt(enPwd));

    }
}
