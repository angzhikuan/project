/*******************************************************************************
 * Package: com.edu
 * Type:    ExcelReadTest
 * Date:    2021/11/28 20:45
 *
 * Copyright (c) 2021 HUANENG GUICHENG TRUST CORP.,LTD All Rights Reserved.
 *
 * You may not use this file except in compliance with the License.
 *******************************************************************************/
package com.edu;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.Date;


/**
 * TODO your comment
 *
 * @author Wangzhikuan
 * @date 2021/11/28 20:45
 */
public class ExcelReadTest {

    String PATH = "D:\\pms\\project\\excel-poi\\";

    @Test
    public void testRead03() throws Exception {
        //获取输入流
        FileInputStream fileInputStream = new FileInputStream(PATH + "狂神关注统计表03.xls");
        //创建工作薄
        Workbook workbook = new HSSFWorkbook(fileInputStream);
        //获取到表
        Sheet sheet = workbook.getSheetAt(0);
        //获取行
        Row row = sheet.getRow(0);
        // 获取列
        Cell cell = row.getCell(1);
        //注意获取类型
        System.out.println(cell.getNumericCellValue());
        fileInputStream.close();
    }

    @Test
    public void testRead07() throws Exception {
        //获取输入流
        FileInputStream fileInputStream = new FileInputStream(PATH + "狂神关注统计表07.xlsx");
        //创建工作薄
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        //获取到表
        Sheet sheet = workbook.getSheetAt(0);
        //获取行
        Row row = sheet.getRow(0);
        // 获取列
        Cell cell = row.getCell(0);
        //注意获取类型
        System.out.println(cell.getStringCellValue());
        fileInputStream.close();
    }

    @Test
    public void testCellType(FileInputStream fileInputStream) throws Exception {
        //获取输入流
//        FileInputStream fileInputStream = new FileInputStream(PATH + "参数表.xlsx");
        //创建工作薄
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        //获取表
        Sheet sheet = workbook.getSheetAt(0);
        //获取标题内容
        Row rowTitle = sheet.getRow(0);
        if (rowTitle != null) {
            int cellCount = rowTitle.getPhysicalNumberOfCells();
            for (int cellNow = 0; cellNow < cellCount; cellNow++) {
                Cell cell = rowTitle.getCell(cellNow);
                if (cell != null) {
                    CellType cellType = cell.getCellType();
                    String cellValue = cell.getStringCellValue();
                    System.out.print(cellValue + " | ");
                }
            }
            System.out.println();
        }
        //获取表中的内容
        int rowCount = sheet.getPhysicalNumberOfRows();
        for (int rowNum = 1; rowNum < rowCount; rowNum++) {
            Row rowData = sheet.getRow(rowNum);
            if (rowData != null) {
                // 读取列
                int cellCount = rowTitle.getPhysicalNumberOfCells();
                for (int cellNum = 0; cellNum < cellCount; cellNum++) {
                    System.out.print("[" + (rowNum + 1) + "-" + (cellNum + 1) + "]");

                    Cell cell = rowData.getCell(cellNum);
                    // 匹配列的数据类型
                    if (cell != null) {
                        CellType cellType = cell.getCellType();
                        String cellValue = "";

                        switch (cellType) {
                            case STRING:
                                System.out.print("【String】");
                                cellValue = cell.getStringCellValue();
                                break;
                            case BOOLEAN:
                                System.out.print("【Boolean】");
                                cellValue = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case BLANK:
                                System.out.print("【BLANK】");
                                break;
                            case NUMERIC:
                                if (HSSFDateUtil.isCellDateFormatted(cell)) { //日期
                                    System.out.print("【NUMERIC】");
                                    Date date = cell.getDateCellValue();
                                    cellValue = new DateTime(date).toString("yyyy-MM-dd");
                                } else {
                                    System.out.print("【转换字符串输出】");
                                    cell.setCellType(CellType.STRING);
                                    cellValue = cell.toString();
                                }
                                break;
                            case ERROR:
                                System.out.print("【数据类型错误】");
                                break;
                        }
                        System.out.println(cellValue);
                    }
                }
            }
        }
        fileInputStream.close();
    }
}
