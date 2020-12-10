package com.muya.sp.demo.pdf.test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-12-07
 * @Time: 15:39
 * @Description:
 */
public class PdfTemplate {

    public static void fillTemplate() {
        ClassPathResource classPathResource = new ClassPathResource("template/pdf-template.pdf");


        // 模板路径
        String templatePath = "D:\\work\\2.personal\\1.zhiliao\\3.项目\\14.领导合同\\1.汇报材料模板\\年度pdf\\4.第四页001-demo.pdf";
        // 生成的新文件路径
        String newPDFPath = "测试.pdf";
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            out = new FileOutputStream(newPDFPath);
            reader = new PdfReader(templatePath);
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            Map<String, String> map = new HashMap<>();
            map.put("passport", "张三李四");
            map.put("signDate", DateUtil.format(new Date(), "yyyy-MM-dd"));
            map.put("validDate", DateUtil.format(new Date(), "yyyy-MM-dd"));
            map.put("orgName", "宁波国际物流责任有限公司");
            map.put("remark", "Java高级开发工程师工作单位应填写全称或规范简称，同时担任多个职务,的工作单位应填写全称或规范简称，同时担任多个职务的");
            int i = 0;
            Iterator<String> it = form.getFields().keySet().iterator();
            while (it.hasNext()) {
                String name = it.next();
                form.setField(name, map.get(name));
            }
            //true代表生成的PDF文件不可编辑
            stamper.setFormFlattening(true);
            stamper.close();
            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        fillTemplate();


    }
}
