package com.testing.class6;

import com.testing.common.ExcelReader;
import com.testing.common.ExcelWriter;
import com.testing.web.DDTofWeb;
import com.testing.web.WebKeyword;

import java.util.List;

/**
 * @Classname DDTwithWriter
 * @Description 类型说明
 * @Date 2021/8/9 1:53
 * @Created by zhangwenliang
 */
public class DDTwithWriter {

    public static final int KEYCOL = 3;

    public static void main(String[] args) {
        WebKeyword tool = new WebKeyword();
        ExcelReader casefile = new ExcelReader("Cases/WebCases.xlsx");
        ExcelWriter resultfile = new ExcelWriter("Cases/WebCases.xlsx", "Cases/Result/resultOfWeb" + tool.createTime("MMdd+HH：mm：ss"));
        DDTofWeb web = new DDTofWeb(resultfile);

        for (int sheetNo = 0; sheetNo < casefile.getTotalSheetNo(); sheetNo++) {
            casefile.useSheetByIndex(sheetNo);
            resultfile.useSheetByIndex(sheetNo);
            System.out.println("当前的sheet页是" + casefile.getSheetName(sheetNo));
            for (int rowNo = 0; rowNo < casefile.getRowNo(); rowNo++) {
                //读取每一行的内容，然后执行每一行的用例
                List<String> rowContent = casefile.readLine(rowNo);
                web.setNowline(rowNo);
                switch (rowContent.get(KEYCOL)) {
                    case "打开浏览器":
                        web.openBrowser(rowContent.get(KEYCOL + 1));
                        break;
                    case "visitWeb":
                        web.visitURL(rowContent.get(KEYCOL + 1));
                        break;
                    case "input":
                        boolean input = web.input(rowContent.get(KEYCOL + 1), rowContent.get(KEYCOL + 2));
                        break;
                    case "click":
                        boolean click = web.click(rowContent.get(KEYCOL + 1));
                        break;
                    case "switchIframebyele":
                    case "intoIframe":
                        boolean iframe = web.switchIframe(rowContent.get(KEYCOL + 1));
                        break;
                    case "selectByValue":
                        web.selectByValue(rowContent.get(KEYCOL + 1), rowContent.get(KEYCOL + 2));
                        break;
                    case "assertEleTextContains":
                        boolean result = web.assertText(rowContent.get(KEYCOL + 1), rowContent.get(KEYCOL + 2), rowContent.get(KEYCOL + 3));
                        break;
                    case "halt":
                        web.halt(rowContent.get(KEYCOL + 1));
                        break;
                    case "closeBrowser":
                        web.closeBrowser();
                        break;


                }
            }

        }

        resultfile.save();
        casefile.close();



    }
}
