package com.muya.sp.demo.pdf.latest;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-29
 * @Time: 10:43
 * @Description:
 */
public class ImageTestDemo3 {
    public static void main(String[] args) throws DocumentException, IOException {

        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D://test3.pdf"));
        //打开文件
        document.open();
        //添加内容
        document.add(new Paragraph("HD content here"));

        //图片1 本地图片
        Image image1 = Image.getInstance("D://1.JPG");
        //设置图片位置的x轴和y周
        image1.setAbsolutePosition(100f, 550f);
        //设置图片的宽度和高度
        image1.scaleAbsolute(200, 200);
        //将图片1添加到pdf文件中
        document.add(image1);

        //图片2 网络图片
        Image image2 = Image.getInstance(new URL("https://cdn.v2ex.com/gravatar/87654df3f72b12f5eb83908180df6f70?s=48&d=retro"));
        //将图片2添加到pdf文件中
        document.add(image2);

        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
    }
}
