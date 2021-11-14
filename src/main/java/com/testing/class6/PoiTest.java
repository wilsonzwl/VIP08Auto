package com.testing.class6;

import com.testing.web.WebKeyword;
import javafx.scene.input.KeyCode;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname PoiTest
 * @Description 类型说明
 * @Date 2021/8/8 0:26
 * @Created by zhangwenliang
 */
public class PoiTest {

    public static final int KEYCOL = 3;

    public static void main(String[] args) throws IOException, InvalidFormatException {

        WebKeyword web = new WebKeyword();

        File excel = new File("Cases/WebCases.xlsx");
        FileInputStream inputStream = new FileInputStream(excel);
//        Workbook workbook = new XSSFWorkbook(inputStream);
        Workbook workbook = new XSSFWorkbook(excel);
        System.out.println(workbook);
//        System.out.println(workbook.getSheetName(0));
//        System.out.println(workbook.getSheetName(1));
        //调用超出范围的sheet会报错
//        System.out.println(workbook.getSheetName(2));
        //读取excel中所有行的内容
        //1、遍历sheet页
        for (int sheetNo = 0; sheetNo < workbook.getNumberOfSheets(); sheetNo++) {
            System.out.println("当前打开的Sheet页是：" + workbook.getSheetName(sheetNo));
            Sheet nowSheet = workbook.getSheetAt(sheetNo);
            //读取一行的操作
//            Row row = nowSheet.getRow(3);
//            System.out.println(row);
//            Cell cell = row.getCell(3);
//            System.out.println(cell);
            //2、遍历sheet中的每一行,每一行存到一个list里面去，元素都是cell的内容
            for (int rowNo = 0; rowNo < nowSheet.getPhysicalNumberOfRows(); rowNo++) {
                List<String> rowContent = new ArrayList<>();
                Row nowRow = nowSheet.getRow(rowNo);
                //3、遍历一行中的所有单元格
                for (int colNo = 0; colNo < nowRow.getPhysicalNumberOfCells(); colNo++) {
                    Cell cell = nowRow.getCell(colNo);
                    String cellVall = "";

                    if (cell.getCellType().equals(CellType.NUMERIC)) {
                        cellVall = (long)(cell.getNumericCellValue()) + "";
                    } else {
                        cellVall = cell.getStringCellValue();
                    }

//                    rowContent.add(cell.toString() + "格式是" + cell.getCellType());
                    rowContent.add(cellVall);
                }
                System.out.println("第" + rowNo + "行的内容是" + rowContent);
                //调用用例执行
                switch (rowContent.get(KEYCOL)) {
                    case "打开浏览器":
                        web.openBrowser(rowContent.get(KEYCOL + 1));
                        break;
                    case "visitWeb":
                        web.visitURL(rowContent.get(KEYCOL + 1));
                        break;
                    case "input":
                        web.input(rowContent.get(KEYCOL + 1), rowContent.get(KEYCOL + 2));
                        break;
                    case "click":
                        web.click(rowContent.get(KEYCOL + 1));
                        break;
                    case "intoIframe":
                        web.switchIframe(rowContent.get(KEYCOL + 1));
                        break;
                    case "switchIframebyele":
                        web.switchIframe(rowContent.get(KEYCOL + 1));
                    case "selectByValue":
                        web.select(rowContent.get(KEYCOL +1), "value", rowContent.get(KEYCOL + 2));
                        break;
                    case "assertEleTextContains":
                        web.assertText(rowContent.get(KEYCOL + 1), rowContent.get(KEYCOL + 2), rowContent.get(KEYCOL + 3));
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


    }
}
