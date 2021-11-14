package com.testing.common;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelReader {

    /*
     * 完成对excel文件的读取
     *
     * @method ExcelReader构造函数，读取excel文件内容到workbook中 useSheet读取指定sheet页
     * close完成文件读取，释放资源 readNextLine读取当前行，并将焦点移动到下一行 readLine读取指定行
     * getCellValue针对单元格内容不同格式进行读取
     */
    // xlsx格式的工作簿
    private Workbook workbook;
    // 工作的sheet页
    private Sheet sheet;
    // 最大行数
    public int rows = 0;

    // 构造函数，用来打开Excel
    public ExcelReader(String path) {
        // 截取后缀名
        String type = path.substring(path.lastIndexOf("."));
        // 初始化文件流
        FileInputStream in = null;
        // 判断是xls还是xlsx格式，读取文件内容到workbook对象中。
        try {
            // 通过文件流打开excel文件
            in = new FileInputStream(new File(path));
            if (type.equals(".xlsx")) {
                workbook = new XSSFWorkbook(in);
                sheet =workbook.getSheetAt(0);
                rows = sheet.getPhysicalNumberOfRows();
            } else if (type.equals(".xls")) {
                workbook = new HSSFWorkbook(in);
                sheet =workbook.getSheetAt(0);
                rows = sheet.getPhysicalNumberOfRows();
            }
        } catch (Exception e) {
            // 读取失败则给出Excel读取失败的提示，并停止
            e.printStackTrace();
            System.out.println("文件读取失败");
            return;
        }

        // 关闭文件输入流
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 如果没有sheet，则输出打开Excel失败的提示
        if (sheet == null)
            System.out.println("Excel文件打开失败！");
    }

    // 获取当前Excel的所有sheet页的个数
    public int getTotalSheetNo() {
        int sheets = 0;
        if (workbook != null) {
            sheets = workbook.getNumberOfSheets();
        }
        return sheets;
    }
    //获取当前sheet页中最大行数。
    public int getRowNo() {
        return rows;
    }

    // 获取当前sheet页面的名字
    public String getSheetName(int sheetIndex) {
        String sheetname = "";
        if (workbook != null)
            sheetname = workbook.getSheetName(sheetIndex);
        return sheetname;
    }

    // 根据sheet名使用sheet页。
    public void useSheet(String sheetName) {
        if (workbook != null) {
            // 根据打开的工作簿类型，在其中初始化sheet页
            sheet = workbook.getSheet(sheetName);
            rows = sheet.getPhysicalNumberOfRows();
        } else {
            System.out.println("未打开Excel文件！");
        }
    }

    //根据sheet序号指定使用的sheet
    public void useSheetByIndex(int sheetIndex) {
        if (workbook != null) {
            sheet = workbook.getSheetAt(sheetIndex);
            rows = sheet.getPhysicalNumberOfRows();
        } else {
            System.out.println("error::未打开Excel文件！");
        }
    }

    // 读取参数中指定的行
    public List<String> readLine(int rowNo) {
        List<String> line = new ArrayList<String>();
        Row row = sheet.getRow(rowNo);
        int cellCount = row.getPhysicalNumberOfCells();
        for (int c = 0; c < cellCount; c++) {
            line.add(getCellValue(row.getCell(c)));
        }
        return line;
    }

    // 读取指定列
    public List<String> readColumn(int colNo) {
        List<String> column = new ArrayList<String>();
        for (int i = 0; i < rows; i++) {
            Row row = sheet.getRow(i);
            column.add(getCellValue(row.getCell(colNo)));
        }
        return column;
    }

    // 读取指定单元格
    public String readCell(int rowNo, int column) {
        String content;
        Row row = sheet.getRow(rowNo);
        content = getCellValue(row.getCell(column));
        return content;
    }

    //以二维数组形式读取excel文件内容
    public Object[][] readAsMatrix() {
        //获取当前sheet页中第一行的最大单元格数。
        int cellcount = sheet.getRow(0).getPhysicalNumberOfCells();
        //二维数组的下标，由excel的最大行数决定，以及最大列数决定。
        Object[][] matrix = new Object[rows - 1][cellcount];
        //用例从excel中的第2行开始读取，遍历到最后一行
        for (int rowNo = 1; rowNo < rows; rowNo++) {
            //遍历行中所有的单元格
            for (int colNo = 0; colNo < cellcount; colNo++) {
                matrix[rowNo - 1][colNo] = readCell(rowNo, colNo);
            }
        }
        //完成循环之后，二维数组已经存储好了对应的值，返回该二维数组。
        return matrix;
    }

    // 针对单元格内容不同格式进行读取
    @SuppressWarnings("deprecation")
    private String getCellValue(Cell cell) {
        String cellValue = "";
        //如果单元格对象为null，则可能是xls文件转xlsx文件格式问题导致读取空单元格时，读到null
        if (cell == null)
            return cellValue;
        //基于不同格式，读取单元格内容并处理。
        try {
            //获取单元格类型。
            CellType cellType = cell.getCellType();
            // 将所有格式转为字符串读取到cellValue
            switch (cellType) {
                case STRING: // 文本
                    cellValue = cell.getStringCellValue();
                    break;
                case NUMERIC: // 数字、日期
                    if (DateUtil.isCellDateFormatted(cell)) {
                        //日期型以年-月-日格式存储
                        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                        cellValue = fmt.format(cell.getDateCellValue());
                    } else {
                        //数字保留两位小数
                        Double d = cell.getNumericCellValue();
                        DecimalFormat df = new DecimalFormat("#.##");
                        cellValue = df.format(d);
                    }
                    break;
                case BOOLEAN: // 布尔型
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case BLANK: // 空白
                    cellValue = cell.getStringCellValue();
                    break;
                case ERROR: // 错误
                    cellValue = "错误";
                    break;
                case FORMULA: // 公式
                    FormulaEvaluator eval;
                    eval = workbook.getCreationHelper().createFormulaEvaluator();
                    cellValue = getCellValue(eval.evaluateInCell(cell));
                    break;
                case _NONE:
                    cellValue = "";
                default:
                    cellValue = "错误";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cellValue;
    }

    // 读取完成，关闭Excel
    public void close() {
        try {
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @Classname MysqlUtils
     * @Description 类型说明
     * @Date 2020/12/29 20:30
     * @Created by 特斯汀Roy
     */
    public static class MysqlUtils {

        //连接的成员变量，后面的方法都会使用这个创建的连接
        private Connection myconnector;

        //建立连接
        public void createConnector(){
            //1、使用mysql连接驱动jdbc
            try {
                Properties royp=new Properties();
                //读取resources中的文件内容
                royp.load(MysqlUtils.class.getResourceAsStream("/shopdb.properties"));
                String jdbcurl=royp.getProperty("jdbcUrl");
                String username=royp.getProperty("mysqlu");
                String classname=royp.getProperty("driverClass");
                String password=royp.getProperty("mysqlp");
                Class.forName(classname);
                //2、输入ip、端口、数据库名、用户名密码 创建连接
                //url格式： 协议jdbc:mysql:// IP : 端口/连接的数据库名 ？参数配置
                myconnector= DriverManager.getConnection(jdbcurl,username,password);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("mysql数据库连接失败");
            }
        }

        /**
         * 通过输入用户名和密码进行查询，返回查询到的所有结果。
         * @return   返回查询到的所有结果 ，格式为List<Map<String,String>>  每个map就是一行数据。
         * @throws SQLException
         */
        public List<Map<String,String>> queryResult(String sql)  {
            try {
                //3、创建查询
                Statement roystatement = myconnector.createStatement();
                //5、执行查询
                ResultSet resultSet = roystatement.executeQuery(sql);
                ResultSetMetaData thead = resultSet.getMetaData();
                //查询到的数据不止一条?怎么办？
                //使用一个list，把所有的map都存起来。
                List<Map<String,String>> resultlist=new ArrayList<>();
                //使用循环，读取resultSet中的每一行，通过next()取值。
                while(resultSet.next()){
                    //循环中，每获取一次数据，创建一个新的map来存这一行的值
                    //一行信息存储到map中
                    Map<String,String> resultmap=new HashMap<>();
                    //遍历表头信息的每一列，获取列名和值。
                    for(int column=1;column<=thead.getColumnCount();column++){
                        resultmap.put(thead.getColumnName(column),resultSet.getString(column));
                    }
                    //将每一行的数据map存到list里面
                    resultlist.add(resultmap);
                }
                return resultlist;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return null;
            }
        }

    }
}
