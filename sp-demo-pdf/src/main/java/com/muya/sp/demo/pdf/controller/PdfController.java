package com.muya.sp.demo.pdf.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-29
 * @Time: 11:27
 * @Description:
 */
@RestController
public class PdfController {

    private PdfPCell getPDFCell(String name, Font font) {
        Paragraph paragraph = new Paragraph(name, font);
        PdfPCell pdfPCell = new PdfPCell(paragraph);
        pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell.setRowspan(1);
        pdfPCell.setColspan(1);
        pdfPCell.setBorderWidth(1f);
        pdfPCell.setMinimumHeight(40);

        return pdfPCell;
    }

    /**
     * 组合单元格 组合的列
     *
     * @param name
     * @param font
     * @param count 列数
     * @return
     */
    private PdfPCell mergeCol(String name, Font font, int count) {
        PdfPCell pCell = new PdfPCell(new Paragraph(name, font));
        pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        // 占多少行
        pCell.setColspan(count);
        pCell.setMinimumHeight(25);

        return pCell;
    }

    private ByteArrayOutputStream getPrintPdfString() throws IOException, DocumentException {

        // 1.新建documnet对象
        Document doc = new Document(PageSize.A4, 0, 0, 50, 0);
        ByteArrayOutputStream fos = new ByteArrayOutputStream();
        PdfWriter.getInstance(doc, fos);

        // 字体设置
        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);


        // 创建字体对象
        Font font = new Font(baseFont, 14, Font.NORMAL);
        Font font2 = new Font(baseFont, 21, Font.BOLD);

        /**
         *  添加6列表格
         */
        PdfPTable table = new PdfPTable(6);

        // 设置各列列宽
        table.setTotalWidth(new float[]{130, 100, 100, 100, 100, 100});

        // 添加表格内容
        table.addCell(getPDFCell("户别", font));
        PdfPCell pCell = new PdfPCell(new Paragraph("常住人口登记表", font2));
        pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pCell.setRowspan(2);
        pCell.setColspan(3);
        table.addCell(pCell);
        table.addCell(getPDFCell("户主姓名", font));
        table.addCell(getPDFCell("与户主关系", font));

        // 户别
        table.addCell(getPDFCell("", font));
        // 户主姓名 待输入
        table.addCell(getPDFCell("", font));
        // 与户主关系 待输入
        table.addCell(getPDFCell("", font));

        table.addCell(getPDFCell("姓名", font));
        table.addCell(mergeCol("", font, 3));
        table.addCell(getPDFCell("性别", font));
        // 性别
        table.addCell("");

        table.addCell(getPDFCell("曾用名", font));
        table.addCell(mergeCol("", font, 3));
        table.addCell(getPDFCell("民族", font));
        // 民族
        table.addCell("");

        table.addCell(getPDFCell("出生日期", font));
        // 出生日期
        table.addCell(mergeCol("", font, 5));

        // 监护人
        table.addCell(getPDFCell("监护人", font));
        // 监护关系
        table.addCell("");
        table.addCell("");
        table.addCell(getPDFCell("出生地", font));
        table.addCell(mergeCol("", font, 2));

        table.addCell(getPDFCell("住址", font));
        table.addCell(mergeCol("", font, 5));

        table.addCell(getPDFCell("本市（县）其他住址", font));
        table.addCell(mergeCol("", font, 5));

        table.addCell(getPDFCell("籍贯", font));
        table.addCell(mergeCol("", font, 3));
        table.addCell(getPDFCell("宗教信仰", font));
        table.addCell(getPDFCell("", font));

        table.addCell(getPDFCell("公民身份证编号的长度不是一般的长，长得我都不想表达任何的观点了真的这个格子真的太长了", font));
        table.addCell(mergeCol("", font, 3));
        table.addCell(getPDFCell("居民身份证签发日期", font));
        table.addCell(getPDFCell("", font));

        table.addCell(getPDFCell("文化程度", font));
        table.addCell(getPDFCell("", font));
        table.addCell(getPDFCell("婚姻状况", font));
        table.addCell(getPDFCell("", font));
        table.addCell(getPDFCell("兵役状况", font));
        table.addCell(getPDFCell("", font));

        table.addCell(getPDFCell("身高", font));
        table.addCell(getPDFCell("", font));
        table.addCell(getPDFCell("血型", font));
        table.addCell(getPDFCell("", font));
        table.addCell(getPDFCell("职业", font));
        table.addCell(getPDFCell("", font));

        table.addCell(getPDFCell("服务处所", font));
        table.addCell(mergeCol("", font, 5));

        table.addCell(getPDFCell("何时何因何由何地迁来本市(县)", font));
        table.addCell(mergeCol("", font, 5));
        table.addCell(getPDFCell("何时何因由何地迁来本市(县)", font));
        table.addCell(mergeCol("", font, 5));
        table.addCell(getPDFCell("何时何因迁往何地", font));
        table.addCell(mergeCol("", font, 5));
        table.addCell(getPDFCell("何时何因注销户口", font));
        table.addCell(mergeCol("", font, 5));

        Paragraph end = new Paragraph("申报人签章");
        end.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph end2 = new Paragraph("申报人签章            加盖户口登记机关专用专用章", font);
        end2.setFirstLineIndent(90);

        Paragraph end3 = new Paragraph("申报人签章");
        end3.setPaddingTop(20);

        Paragraph end4 = new Paragraph("承办人签章            登记日期：  年  月  日", font);
        end4.setFirstLineIndent(90);

        //添加内容

        doc.open();
        doc.add(table);
        doc.add(end);
        doc.add(end2);
        doc.add(end3);
        doc.add(end4);
        doc.close();
        return fos;
    }

    @GetMapping(path = "/print")
    public void print(HttpServletResponse response) throws Exception {
        ByteArrayOutputStream ba = getPrintPdfString();

        String fileNamePrefix = UUID.randomUUID().toString();
        String fileName = fileNamePrefix + ".pdf";

        response.setContentType("application/pdf;charset=UTF-8");
        response.setHeader("Content-Disposition", "filename=" + new String((fileName).getBytes(), "iso8859-1"));

        OutputStream out = response.getOutputStream();
        out.write(ba.toByteArray());

        out.flush();
        out.close();
    }
}
