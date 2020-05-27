package com.universe;

import entity.handler.SheetHandler;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


import javax.sql.rowset.spi.XmlReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class PoiTest {

    @Test
    public void test01() throws IOException {
        // 创建工作薄  HSSFWorkbook ---2003, XSSFWorkbook ---2007
        Workbook sheets = new XSSFWorkbook();
        // 创建表单
        Sheet sheet = sheets.createSheet("universe");
        // 创建行对象 参数：索引0开始
        Row row = sheet.createRow(2);
        // 创建单元格对象 参数：索引0开始
        Cell cell = row.createCell(2);
        // Cell cell = row.getCell(2);
        // 写入数据
        cell.setCellValue("universe");

        // 样式处理
        CellStyle style = sheets.createCellStyle();
        // style.setFillBackgroundColor();
        // 设置字体
        Font font = sheets.createFont();
        style.setFont(font);

        // 设置样式
        cell.setCellStyle(style);

        // 文件流
        FileOutputStream fileOutputStream = new FileOutputStream("test01.xlsx");
        // 写入文件
        sheets.write(fileOutputStream);
        // 关闭流
        fileOutputStream.close();
    }


    /**
     * 图片处理
     */
    @Test
    public void test02() throws IOException {
        XSSFWorkbook sheets = new XSSFWorkbook();
        XSSFSheet sheet = sheets.createSheet();

        // 图片输入流
        FileInputStream fileInputStream = new FileInputStream("logo.png");
        // POI提供工具转二进制
        byte[] bytes = IOUtils.toByteArray(fileInputStream);

        // 向POI内存中添加一张图片，返回图片在图片集合的索引
        int index = sheets.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
        // 绘制图片工具类
        XSSFCreationHelper helper = sheets.getCreationHelper();
        // 创建绘图对象
        XSSFDrawing xssfDrawing = sheet.createDrawingPatriarch();
        // 创建锚点，设置图片坐标
        XSSFClientAnchor anchor = helper.createClientAnchor();
        anchor.setRow1(0);  // 行
        anchor.setCol1(0);  // 列
        // 绘制图片
        XSSFPicture picture = xssfDrawing.createPicture(anchor, index);//excel图片位置，内存图片索引
        picture.resize(); // 图片自适应

        FileOutputStream fileOutputStream = new FileOutputStream("test02.xlsx");
        sheets.write(fileOutputStream);
        fileOutputStream.close();
        fileInputStream.close();

    }

    /**
     * 读取excl
     */
    @Test
    public void test03() throws IOException {
        XSSFWorkbook sheets = new XSSFWorkbook("demo.xlsx");
        // 获取Sheet
        XSSFSheet sheet = sheets.getSheetAt(0);
        // 获取sheet中的每行，和每一个单元格
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            // 获取所有行
            XSSFRow row = sheet.getRow(i);
            Object[] values = new Object[row.getLastCellNum()];
            // ArrayList<Object> list = new ArrayList<Object>();
            for (int j = 0; j < row.getLastCellNum(); j++) {
                // 获取每个单元格
                XSSFCell cell = row.getCell(j);
                // 获取每个单元格内容
                Object value = getValueType(cell);
                // list.add(value);
                values[j] = value;
            }
            // list.forEach(System.out::println);
            System.out.println(Arrays.toString(values));
        }

    }

    /**
     * 文件下载
     * 大数据生成表格
     * 不支持模板打印
     * SXSSFWorkbook
     * */


    /**
     * 大数据读取 写入操作
     * 事件迭代处理
     */
    @Test
    public void test04() throws Exception {
        // 根据excel获取OPCPackage, 参数1:路径 参数2:只读打开
        OPCPackage opcPackage = OPCPackage.open("demo.xlsx", PackageAccess.READ);
        // 创建XSSFReader
        XSSFReader reader = new XSSFReader(opcPackage);
        // 获取SharedStringTable
        SharedStringsTable stringsTable = reader.getSharedStringsTable();
        // 获取styleTable
        StylesTable stylesTable = reader.getStylesTable();
        // 创建Sax的xmlReader对象
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        // 注册事件处理器
        XSSFSheetXMLHandler xmlHandler = new XSSFSheetXMLHandler(stylesTable, stringsTable, new SheetHandler(), false);
        xmlReader.setContentHandler(xmlHandler);
        // 逐行读取

        XSSFReader.SheetIterator streamIterator = (XSSFReader.SheetIterator) reader.getSheetsData();
        while (streamIterator.hasNext()) {
            InputStream stream = streamIterator.next();  // 每一个sheet的流数据
            InputSource source = new InputSource(stream);
            xmlReader.parse(source);
        }
    }

    /**
     * 获取excel表格内容
     */
    public static Object getValueType(Cell cell) {
        CellType cellType = cell.getCellType();
        Object value = null;
        switch (cellType) {
            case STRING:
                value = cell.getStringCellValue(); // 字符串
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    // 日期格式
                    value = cell.getDateCellValue();
                    // 数字
                } else {
                    value = cell.getNumericCellValue();
                }
                break;
            case FORMULA: // 公式
                value = cell.getCellFormula();
                break;
            default:
                break;
        }
        return value;
    }


}
