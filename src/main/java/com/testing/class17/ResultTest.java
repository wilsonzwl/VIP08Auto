package com.testing.class17;

import com.testing.common.AutoTools;
import com.testing.common.ExcelReader;
import com.testing.common.ExcelResult;
import com.testing.common.Report;

import java.util.List;
import java.util.Map;

/**
 * @Classname ResultTest
 * @Description 类型说明
 * @Date 2021/8/22 15:47
 * @Created by zhangwenliang
 */
public class ResultTest {
    public static void main(String[] args) {
        ExcelResult re = new ExcelResult();
        String time = AutoTools.createTime("yyyy-MM-dd HH:mm:ss");
//        List<Map<String, String>> sumarry = re.Sumarry("Cases/Result/resultofInter-0821-193152.xlsx", time);
//        for (Map<String, String> stringStringMap : sumarry) {
//            System.out.println(stringStringMap);
//        }
//        String s = Report.makeReport(sumarry);
//        System.out.println(s);
        Report.sendreport("Cases/Result/resultofInter-0821-193152.xlsx", time);


    }
}
