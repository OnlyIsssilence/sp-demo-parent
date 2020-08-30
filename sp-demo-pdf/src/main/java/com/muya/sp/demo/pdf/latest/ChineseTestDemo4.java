package com.muya.sp.demo.pdf.latest;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-29
 * @Time: 10:55
 * @Description:
 */
public class ChineseTestDemo4 {

    public static void main(String[] args) throws DocumentException, IOException {
        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D://test6.pdf"));
        //打开文件
        document.open();

        //中文字体,解决中文不能显示问题
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);


        /********第一页*******/
        //蓝色字体
        Font blueFont = new Font(bfChinese);
        blueFont.setColor(BaseColor.BLUE);
        //段落文本
        Paragraph paragraphBlue = new Paragraph("cecece你好", blueFont);
        document.add(paragraphBlue);

        /*********第二页*********/
        //绿色字体
        Font greenFont = new Font(bfChinese);
        greenFont.setColor(BaseColor.GREEN);
        //创建章节
        Paragraph chapterTitle = new Paragraph("www段落标题xxxx", greenFont);
        Chapter chapter1 = new Chapter(chapterTitle, 1);
        chapter1.setNumberDepth(0);

        Paragraph sectionTitle1 = new Paragraph("xx部分标题", greenFont);
        chapter1.addSection(sectionTitle1);

        Paragraph sectionTitle2 = new Paragraph("xx部分标题", greenFont);
        chapter1.addSection(sectionTitle2);

        //将章节添加到文章中
        document.add(chapter1);

        //蓝色字体
        blueFont.setColor(BaseColor.BLUE);
        //段落文本
        Paragraph paragraphBlue1 = new Paragraph("paragraphOne blue front你好", blueFont);
        document.add(paragraphBlue1);


        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
    }
}
