/*******************************************************************************
 * Package: com.edu
 * Type:    ExcelWriteTest
 * Date:    2021/11/28 19:29
 *
 * Copyright (c) 2021 HUANENG GUICHENG TRUST CORP.,LTD All Rights Reserved.
 *
 * You may not use this file except in compliance with the License.
 *******************************************************************************/
package com.edu;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * TODO your comment
 *
 * @author Wangzhikuan
 * @date 2021/11/28 19:29
 */
public class ExcelWriteTest {

    String PATH = "D:\\pms\\project\\excel-poi\\";

    @Test
    public void testWrite() throws Exception {
        //1.创建工作薄2003xls
        Workbook workbook = new HSSFWorkbook();
        //2.创建工作表
        Sheet sheet = workbook.createSheet("狂神关注统计表");
        //3.创建一个行(1,1)
        Row row1 = sheet.createRow(0);
        //4.创建一个单元格
        Cell cell1 = row1.createCell(0);
        cell1.setCellValue("今日新增关注");
        Cell cell2 = row1.createCell(1);
        cell2.setCellValue(888);

        //3.创建一个行(2,1)
        Row row2 = sheet.createRow(1);
        Cell cell21 = row2.createCell(0);
        cell21.setCellValue("统计时间");
        //(2,2)
        Cell cell122 = row2.createCell(1);
        String time = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        cell122.setCellValue(time);

        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "\\狂神关注统计表03.xls");

        workbook.write(fileOutputStream);
        fileOutputStream.close();
        System.out.println("文件生成完毕");
    }

    @Test
    public void testWrite07() throws Exception {
        //1.创建工作薄2007xls
        Workbook workbook = new XSSFWorkbook();
        //2.创建工作表
        Sheet sheet = workbook.createSheet("狂神关注统计表");
        //3.创建一个行(1,1)
        Row row1 = sheet.createRow(0);
        //4.创建一个单元格
        Cell cell1 = row1.createCell(0);
        cell1.setCellValue("今日新增关注");
        Cell cell2 = row1.createCell(1);
        cell2.setCellValue(888);

        //3.创建一个行(2,1)
        Row row2 = sheet.createRow(1);
        Cell cell21 = row2.createCell(0);
        cell21.setCellValue("统计时间");
        //(2,2)
        Cell cell122 = row2.createCell(1);
        String time = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        cell122.setCellValue(time);

        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "\\狂神关注统计表07.xlsx");

        workbook.write(fileOutputStream);
        fileOutputStream.close();
        System.out.println("文件生成完毕");
    }

    @Test
    public void testWrite03BigData() throws Exception {

        long begin = System.currentTimeMillis();
        //07 XSSFWorkbook耗时长
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        for (int rowNum = 0; rowNum < 65536; rowNum++) {
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 10; cellNum++) {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        System.out.println("over");
        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "testWrite07BigData.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        long end = System.currentTimeMillis();

        System.out.println((double) (end - begin)/1000);

    }

    @Test
    public void testWrite07BigDataS() throws Exception {

        long begin = System.currentTimeMillis();
        //07 SXSSFWorkbook 优化写入速度
        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        for (int rowNum = 0; rowNum < 65536; rowNum++) {
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 10; cellNum++) {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        System.out.println("over");
        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "testWrite07BigDataS.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        // 清除临时文件
        ((SXSSFWorkbook) workbook).dispose();
        long end = System.currentTimeMillis();

        System.out.println((double) (end - begin)/1000);

    }
}
