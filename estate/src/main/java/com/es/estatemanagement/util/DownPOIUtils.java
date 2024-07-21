package com.es.estatemanagement.util;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

public class DownPOIUtils {

    public void export(HttpServletResponse response) {
        // 创建一个xlsx
        XSSFWorkbook xk = new XSSFWorkbook();
        // 创建sheetname第一页页名
        XSSFSheet sheet = xk.createSheet("测试");
        // 字体样式
        XSSFFont xssfFont = xk.createFont();
        xssfFont.setBold(true);
        xssfFont.setFontName("楷体");
        xssfFont.setFontHeight(11);
        // 表头样式
        XSSFCellStyle headStyle = xk.createCellStyle();
        // 继承字体样式
        headStyle.setFont(xssfFont);
        // 竖向居中，横向居中
        headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headStyle.setAlignment(HorizontalAlignment.CENTER);
        // 设置标题一行的单元格的填充颜色为灰色
        headStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headStyle.setWrapText(true); // 设置自动换行
        headStyle.setHidden(true); // 高度自动
        // 创建第一行标题
        XSSFRow row1 = sheet.createRow(0);
        // 设置宽度
        sheet.setDefaultColumnWidth(30);
        // 第一行第一列
        XSSFCell Cell1 = row1.createCell(0);
        Cell1.setCellValue("姓名");
        Cell1.setCellStyle(headStyle);
        // 第二列
        XSSFCell Cell2 = row1.createCell(1);
        Cell2.setCellValue("班级");
        Cell2.setCellStyle(headStyle);
        // 第三列
        XSSFCell Cell3 = row1.createCell(2);
        Cell3.setCellValue("年龄");
        Cell3.setCellStyle(headStyle);
        // 第四列
        XSSFCell Cell4 = row1.createCell(3);
        Cell4.setCellValue("性别");
        Cell4.setCellStyle(headStyle);
        // 创建第二行内容
        XSSFRow row2 = sheet.createRow(1);
        XSSFCell Cell11 = row2.createCell(0);
        Cell11.setCellValue("小明");
        XSSFCell Cell22 = row2.createCell(1);
        Cell22.setCellValue("终极一班");
        XSSFCell Cell33 = row2.createCell(2);
        Cell33.setCellValue("33");
        XSSFCell Cell44 = row2.createCell(3);
        Cell44.setCellValue("男");
        // 每次创建一行一列都需要调用方法可自己加工成工具类使用时只需要赋值即可
        try {
            // 附件名称和格式
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("学生信息.xlsx", "UTF-8"));
            response.setContentType("application/vnd.ms-excel");
            // 转为二进制流进行吐出
            ServletOutputStream os = response.getOutputStream();
            xk.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            System.out.println("Excel error:" + e);
        }
    }


}
