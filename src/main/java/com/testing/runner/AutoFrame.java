package com.testing.runner;

import com.testing.app.DDTOfAPP;
import com.testing.common.*;
import com.testing.inter.DDTOfInter;
import com.testing.web.DDTofWeb;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @Classname AutoFrame
 * @Description 将web、app、接口测试集成到一个类中，通过输入参数来进行切
 * @Date 2021/8/21 17:14
 * @Created by zhangwenliang
 */
public class AutoFrame {

    //需要用到的关键字对象
    public static DDTofWeb web;
    public static DDTOfAPP app;
    public static DDTOfInter inter;
    public static Object keyword;

    //读取用例文件和写入的结果文件
    public static ExcelReader casefile;
    public static ExcelWriter resultfile;


    public static void main(String[] args) {

        String type = args[0];
        //获取当前运行所在的路径
        String runPath = System.getProperty("user.dir");
        //文件的名称前缀文件夹路径
        String casefilePath = runPath + "/Cases/";
        String resultfilePath = runPath + "/Cases/Result/";
        String time = AutoTools.createTime("MMdd-HHmmss");
        String starttime = AutoTools.createTime("yyyy-MM-dd HH:mm:ss");

        //基于类型完成excel文件的实例化操作，以及对应类型关键字的实例化
        switch (type) {
            case "http":
                casefilePath += "InterCases.xlsx";
                resultfilePath += "resultofInter-" + time;
                casefile = new ExcelReader(casefilePath);
                resultfile = new ExcelWriter(casefilePath, resultfilePath);
                keyword = new DDTOfInter(resultfile);
                break;

            case "app":
                casefilePath += "APPCases.xlsx";
                resultfilePath += "resultofAPP-" + time;
                casefile = new ExcelReader(casefilePath);
                resultfile = new ExcelWriter(casefilePath, resultfilePath);
                keyword = new DDTOfAPP(resultfile);
                break;

            case "web":
                casefilePath += "WebCases.xlsx";
                resultfilePath += "resultofWeb-" + time;
                casefile = new ExcelReader(casefilePath);
                resultfile = new ExcelWriter(casefilePath, resultfilePath);
                keyword = new DDTofWeb(resultfile);
                break;
            default:
                AutoLogger.log.error("输入自动化测试类型错误");
                return;
        }
        execCase();
        Report.sendreport(resultfilePath + ".xlsx", starttime);


    }

    public static void execCase() {
        for (int sheetNo = 0; sheetNo < casefile.getTotalSheetNo(); sheetNo++) {
            casefile.useSheetByIndex(sheetNo);
            resultfile.useSheetByIndex(sheetNo);
            AutoLogger.log.info("当前sheet页的名字是：" + casefile.getSheetName(sheetNo));

            //遍历每一行，第一行表头就不遍历了
            for (int rowNo = 1; rowNo < casefile.getRowNo(); rowNo++) {
                try {
                    Method setNowline = keyword.getClass().getDeclaredMethod("setNowline", int.class);
                    setNowline.invoke(keyword, rowNo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //读取每一行的内容
                List<String> rowContent = casefile.readLine(rowNo);
                //根据每一行的关键字列和断言列来进行关键字的调用和参数的传入
                //通过反射，执行代码
                AutoTools.invokeI(keyword, rowContent, 3);
                //进行断言
                AutoTools.invokeI(keyword, rowContent, 7);
            }
        }

        casefile.close();
        resultfile.save();


    }


}
