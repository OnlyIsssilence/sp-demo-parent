package com.muya.demo.excel.service;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.boot.configurationprocessor.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;

import static ch.qos.logback.core.rolling.helper.DateTokenConverter.DEFAULT_DATE_PATTERN;
import static org.apache.poi.hssf.record.DefaultColWidthRecord.DEFAULT_COLUMN_WIDTH;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-11
 * @Time: 14:14
 * @Description:
 */
public class ExcelService {

    /*public static void exportExcel(List<List<LinkedHashMap>> titleList, List<JSONArray> dataArray,
                                   HttpServletResponse response, HttpServletRequest request, String name) {

        String datePattern = DEFAULT_DATE_PATTERN;
        int minBytes = DEFAULT_COLUMN_WIDTH;

        //声明一个工作薄
        SXSSFWorkbook workbook = new SXSSFWorkbook(5000);
        workbook.setCompressTempFiles(true);

        // 表头1样式
        CellStyle title1Style = workbook.createCellStyle();
        title1Style.setAlignment(HorizontalAlignment.CENTER);
        title1Style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font titleFont = workbook.createFont();
        titleFont.setFontHeightInPoints((short) 20);
        titleFont.setBoldweight((short) 200);
        title1Style.setFont(titleFont);

        // head样式
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        headerStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
        headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 15);
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerStyle.setFont(headerFont);

        // 单元格样式
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        Font cellFont = workbook.createFont();
        cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        cellStyle.setFont(cellFont);


        for (int i = 0; i < titleList.size(); i++) {
            List<LinkedHashMap> list = titleList.get(i);
            //标题
            String title1 = (String) list.get(0).get(TITLE);
            //表头
            LinkedHashMap<String, String> headMap = list.get(1);
            JSONArray objects = dataArray.get(i);
            excleSheet(objects, datePattern, minBytes, workbook, title1Style, headerStyle, cellStyle, title1, headMap, i);
        }

        OutputStream output;
        try {
            String agent = request.getHeader("USER-AGENT").toLowerCase();
            response.setContentType("application/json;charset=UTF-8");
            String fileName = name + EXCEL_XLSX;
            String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            if (agent.contains("firefox")) {
                response.setCharacterEncoding("utf-8");
                response.setHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1"));
            } else {
                response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
            }
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("FileName", codedFileName);
            response.setHeader("Access-Control-Expose-Headers", "*");
            output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            bufferedOutPut.flush();
            workbook.write(bufferedOutPut);
            bufferedOutPut.close();
            // 释放workbook所占用的所有windows资源
            workbook.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}
