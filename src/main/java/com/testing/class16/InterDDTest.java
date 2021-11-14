package com.testing.class16;

import com.testing.common.AutoLogger;
import com.testing.common.AutoTools;
import com.testing.common.ExcelReader;
import com.testing.common.ExcelWriter;
import com.testing.inter.DDTOfInter;
import com.testing.inter.InterKw;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Classname InterDDTest
 * @Description 类型说明
 * @Date 2021/8/20 20:52
 * @Created by zhangwenliang
 */
public class InterDDTest {
    public static void main(String[] args) {
        ExcelReader cases = new ExcelReader("Cases/InterCases.xlsx");
        ExcelWriter results = new ExcelWriter("Cases/InterCases.xlsx", "Cases/Result/resultofInter" + AutoTools.createTime("MMdd+HH：mm：ss"));
        DDTOfInter inter = new DDTOfInter(results);

        for (int sheetNo = 0; sheetNo < cases.getTotalSheetNo(); sheetNo++) {
            cases.useSheetByIndex(sheetNo);
            results.useSheetByIndex(sheetNo);
            AutoLogger.log.info("当前sheet页的名字是：" + cases.getSheetName(sheetNo));

            //遍历每一行，第一行表头就不遍历了
            for (int rowNo = 1; rowNo < cases.getRowNo(); rowNo++) {
                inter.setNowline(rowNo);
                //读取每一行的内容
                List<String> rowContent = cases.readLine(rowNo);
                //根据每一行的关键字列和断言列来进行关键字的调用和参数的传入
                //通过反射，执行代码
                AutoTools.invokeI(inter, rowContent, 3);
                //进行断言
                AutoTools.invokeI(inter, rowContent, 7);
            }
        }

        cases.close();
        results.save();

    }



}
