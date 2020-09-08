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
     * @param colspan 列数
     * @return
     */
    /**
     * 组合单元格 组合的列
     *
     * @param name
     * @param font
     * @param colspan 列数
     * @return
     */
    private PdfPCell mergeCol(String name, Font font, int colspan) {
        return mergeColWithAlign(name, font, colspan, -1, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE);
    }

    private PdfPCell mergeColWithAlign(String name, Font font, int colspan, int rowspan, int horizontalAlignment, int verticalAlignment) {
        PdfPCell pCell = new PdfPCell(new Paragraph(name, font));
        pCell.setHorizontalAlignment(horizontalAlignment);
        pCell.setVerticalAlignment(verticalAlignment);
        // 占多少行
        if (-1 != rowspan) {
            pCell.setRowspan(rowspan);
        }

        // 占多少列
        if (-1 != colspan) {
            pCell.setColspan(colspan);
        }
        pCell.setMinimumHeight(40);

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
    public void  print(HttpServletResponse response) throws Exception {
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

    @GetMapping(path = "/myJob")
    public void myJob(HttpServletResponse response) throws Exception {
        ByteArrayOutputStream ba = getMyJob();

        String fileNamePrefix = UUID.randomUUID().toString();
        String fileName = fileNamePrefix + ".pdf";

        response.setContentType("application/pdf;charset=UTF-8");
        response.setHeader("Content-Disposition", "filename=" + new String((fileName).getBytes(), "iso8859-1"));

        OutputStream out = response.getOutputStream();
        out.write(ba.toByteArray());

        out.flush();
        out.close();
    }

    private ByteArrayOutputStream getMyJob() throws IOException, DocumentException {

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
         *  添加12列表格
         */
        PdfPTable table = new PdfPTable(12);

        // 设置各列列宽
        table.setTotalWidth(new float[]{150, 150, 150, 150, 150, 150, 150, 150, 150, 150, 150, 150});

        // 第一行：表头
        PdfPCell pCell = new PdfPCell(new Paragraph("项目名称-物业维修单", font2));
        pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pCell.setRowspan(3);
        pCell.setColspan(12);
        pCell.setMinimumHeight(60);
        table.addCell(pCell);

        // 第二行：服务单编号  报修时间
        table.addCell(mergeColWithAlign("服务单编号:", font, 6, -1, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE));
        table.addCell(mergeColWithAlign("保修时间: ", font, 6, -1, Element.ALIGN_RIGHT, Element.ALIGN_MIDDLE));

        // 第三行 报修人，联系电话，维修种类
        table.addCell(mergeColWithAlign("报修人: ", font, 4, -1, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE));
        table.addCell(mergeColWithAlign("联系电话: ", font, 4, -1, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE));
        table.addCell(mergeColWithAlign("维修种类: ", font, 4, -1, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE));
        // 第四行 服务地址
        table.addCell(mergeColWithAlign("服务地址", font, 3, -1, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE));
        table.addCell(mergeColWithAlign("", font, 9, -1, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE));
        // 第五行 服务内容
        table.addCell(mergeColWithAlign("服务内容", font, 3, -1, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE));
        table.addCell(mergeColWithAlign("", font, 9, -1, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE));
        // 第六行 到达时间 完成时间
        table.addCell(mergeColWithAlign("到达时间：", font, 6, -1, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE));
        table.addCell(mergeColWithAlign("完成时间：", font, 6, -1, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE));

        // 第七行 维修完成内容
        table.addCell(mergeColWithAlign("维修完成内容", font, 3, -1, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE));
        table.addCell(mergeColWithAlign("", font, 9, -1, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE));

        // 第八行 费用，材料，数量
        PdfPCell eightCell = new PdfPCell(new Paragraph("费用/材料/数量", font));
        eightCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        eightCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        eightCell.setRowspan(2);
        eightCell.setColspan(3);
        table.addCell(eightCell);

        table.addCell(mergeColWithAlign("费用", font, 3, -1, Element.ALIGN_MIDDLE, Element.ALIGN_MIDDLE));
        table.addCell(mergeColWithAlign("材料", font, 3, -1, Element.ALIGN_MIDDLE, Element.ALIGN_MIDDLE));
        table.addCell(mergeColWithAlign("数量", font, 3, -1, Element.ALIGN_MIDDLE, Element.ALIGN_MIDDLE));
        table.addCell(mergeColWithAlign("", font, 3, -1, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE));
        table.addCell(mergeColWithAlign("", font, 3, -1, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE));
        table.addCell(mergeColWithAlign("", font, 3, -1, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE));

        // 第九行 服务评价
        table.addCell(mergeColWithAlign("服务评价", font, 3, -1, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE));
        table.addCell(mergeColWithAlign("口 满意          口 一般          口 不满意", font, 9, -1, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE));

        // 第十行 维修人，订单人，订单时间
        table.addCell(mergeColWithAlign("维修人：", font, 4, -1, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE));
        table.addCell(mergeColWithAlign("打单人：", font, 4, -1, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE));
        table.addCell(mergeColWithAlign("打单时间：", font, 4, -1, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE));

        // 第十一行 业主签字
        table.addCell(mergeColWithAlign("业主签字:                          ", font, 12, -1, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE));

        doc.open();
        doc.add(table);
        doc.close();
        return fos;
    }

}
