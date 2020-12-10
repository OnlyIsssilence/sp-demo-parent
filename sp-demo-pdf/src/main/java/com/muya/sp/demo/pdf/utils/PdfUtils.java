package com.muya.sp.demo.pdf.utils;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-28
 * @Time: 22:55
 * @Description: 参考文档：
 * https://blog.csdn.net/qq_41367983/article/details/99169003
 * https://www.jianshu.com/p/20d4905383b4
 */
public class PdfUtils {
    public static PdfPCell mergeColWithAlign(String name, Font font, int colspan, int rowspan, int horizontalAlignment, int verticalAlignment) {
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

    public static PdfPCell getPDFCell(String name, Font font) {
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
    public static PdfPCell mergeCol(String name, Font font, int colspan) {
        return mergeColWithAlign(name, font, colspan, -1, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE);
    }

    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream("Helloworld.PDF"));

        document.open();

        document.add(new Paragraph("Hello World"));

        document.close();

    }


    /**
     * 创建pdf
     *
     * @param examjson
     * @param paperid
     * @param path
     * @param pathimg
     * @return
     */
    public static String createPdfAdd1(String examjson, Integer paperid, String path, String pathimg) {
        //Map<String, Object> exam = JSONObject.parseObject(examjson, new TypeReference<Map<String, Object>>() { });
        String examPath = "";
        try {
            //创建文件
            Document document = new Document();
            //设置字体
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            //文件路径
            examPath = path + "/exam-" + paperid;
            File files = new File(examPath);
            if (!files.exists() && !files.isDirectory()) {                    //判断目录是否存在
                files.mkdir();
            }
            //创建PDF
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(examPath + "/test.pdf"));
            // 设置页面布局
            writer.setViewerPreferences(PdfWriter.PageLayoutOneColumn);
            // 页码
            writer.setPageEvent(new PageXofYTest());
            //打开文件
            document.open();
            //图片1
            Image image1 = Image.getInstance(pathimg + "/zy3.jpg");
            //设置图片的宽度和高度
            //image1.scaleAbsolute(140, 40);
            //将图片1添加到pdf文件中
            document.add(image1);
            //标题
            Paragraph paragraph = new Paragraph(150);//段落的间距
            //1 2 3  中右左
            paragraph.setAlignment(1);  //对齐方式
            Font font = new Font(bfChinese);//字体
            font.setSize(22);//字体大小
            paragraph.setFont(font);//设置段落字体
            Chunk chunk = new Chunk("Test");
            paragraph.add(chunk);
            document.add(paragraph);

            Paragraph paragraph1 = new Paragraph(40);
            //1 2 3  中右左
            paragraph1.setAlignment(1);  //对齐方式
            Font font1 = new Font(bfChinese);//字体
            font1.setSize(20);
            paragraph1.setFont(font1);
            Chunk chunk1 = new Chunk("Test1");
            paragraph1.add(chunk1);
            //paragraph1.setSpacingBefore(-15);
            //paragraph1.setSpacingAfter(-50);//往下距离200
            document.add(paragraph1);

            //点线
            DottedLineSeparator line = new DottedLineSeparator();
            //下移5个单位
            line.setOffset(-15);
            //设置点之间的距离
            //line.setGap(20);
            document.add(line);

            Paragraph paragraph3 = new Paragraph(150);
            //1 2 3  中右左
            paragraph3.setAlignment(1);  //对齐方式
            Font font3 = new Font(bfChinese);//字体
            font3.setSize(12);
            paragraph3.setFont(font3);
            Chunk chunk3 = new Chunk("编号：" + getRandom(17));
            paragraph3.add(chunk3);
            paragraph3.setSpacingAfter(5);
            document.add(paragraph3);

            Paragraph paragraph4 = new Paragraph(15);
            //1 2 3  中右左
            paragraph4.setAlignment(1);  //对齐方式
            Font font4 = new Font(bfChinese);//字体
            font4.setSize(12);
            paragraph4.setFont(font4);
            SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
            Chunk chunk4 = new Chunk("文档生成日期：" + df.format(System.currentTimeMillis()));
            paragraph4.add(chunk4);
            paragraph4.setSpacingAfter(5);
            document.add(paragraph4);

            document.newPage(); //换页

            Paragraph answerPar = new Paragraph(20);                //标准答案
            answerPar.setAlignment(3);  //对齐方式
            Font answerfont = new Font(bfChinese);//字体
            answerfont.setSize(12);
            answerPar.setFont(answerfont);
            Chunk answerchunk = new Chunk(examjson);
            answerPar.add(answerchunk);
            answerPar.setSpacingAfter(5);
            document.add(answerPar);
            //关闭文档
            document.close();
            //关闭书写器
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ZIPUtil.compress(examPath, path + "/exam-" + paperid + ".zip");        //压缩
        return "ok";
    }

    /**
     * 生成指定位数的随机数
     *
     * @param length
     * @return
     */
    public static String getRandom(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }


    //页码事件
    private static class PageXofYTest extends PdfPageEventHelper {
        public PdfTemplate total;

        public BaseFont bfChinese;

        /**
         * 重写PdfPageEventHelper中的onOpenDocument方法
         */
        @Override
        public void onOpenDocument(PdfWriter writer, Document document) {
            // 得到文档的内容并为该内容新建一个模板
            total = writer.getDirectContent().createTemplate(500, 500);
            try {

                String prefixFont = "";
                String os = System.getProperties().getProperty("os.name");
                if (os.startsWith("win") || os.startsWith("Win")) {
                    prefixFont = "C:\\Windows\\Fonts" + File.separator;
                } else {
                    prefixFont = "/usr/share/fonts/chinese" + File.separator;
                }

                // 设置字体对象为Windows系统默认的字体
                //bfChinese = BaseFont.createFont(prefixFont + "simsun.ttc,0", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
                bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            } catch (Exception e) {
                throw new ExceptionConverter(e);
            }
        }

        /**
         * 重写PdfPageEventHelper中的onEndPage方法
         */
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            // 新建获得用户页面文本和图片内容位置的对象
            PdfContentByte pdfContentByte = writer.getDirectContent();
            // 保存图形状态
            pdfContentByte.saveState();
            String text = "www.rkpass.cn   " + writer.getPageNumber();
            // 获取点字符串的宽度
            float textSize = bfChinese.getWidthPoint(text, 15);
            pdfContentByte.beginText();
            // 设置随后的文本内容写作的字体和字号
            pdfContentByte.setFontAndSize(bfChinese, 15);

            // 定位'X/'
            //System.out.println(document.right() +"...."+ document.left());
            // float x = (document.right() + document.left())/2;
            float x = (document.right() - 150f);
            float y = 20f;
            pdfContentByte.setTextMatrix(x, y);
            pdfContentByte.showText(text);
            pdfContentByte.endText();

            // 将模板加入到内容（content）中- // 定位'Y'
            pdfContentByte.addTemplate(total, x + textSize, y);

            pdfContentByte.restoreState();
        }

        /**
         * 重写PdfPageEventHelper中的onCloseDocument方法
         */
       /* @Override
        public void onCloseDocument(PdfWriter writer, Document document) {
            total.beginText();
            try {
                String prefixFont = "";
                String os = System.getProperties().getProperty("os.name");
                if(os.startsWith("win") || os.startsWith("Win")){
                    prefixFont = "C:\\Windows\\Fonts" + File.separator;
                }else {
                    prefixFont = "/usr/share/fonts/chinese" + File.separator;
                }

                bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                total.setFontAndSize(bfChinese, 15);
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            total.setTextMatrix(0, 0);
            // 设置总页数的值到模板上，并应用到每个界面
            total.showText(String.valueOf(writer.getPageNumber() - 1));
            total.endText();
        }*/
    }


}
