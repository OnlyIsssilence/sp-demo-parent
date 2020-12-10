package com.muya.sp.demo.pdf.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.muya.sp.demo.pdf.constant.ReportConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-12-04
 * @Time: 14:27
 * @Description:
 */
@RestController
@RequestMapping("report")
public class TemplatePdfController {

    @GetMapping(path = "template")
    public void template(HttpServletResponse response) throws Exception {
        ByteArrayOutputStream ba = getYearData();

        String fileNamePrefix = UUID.randomUUID().toString();
        String fileName = fileNamePrefix + ".pdf";

        response.setContentType("application/pdf;charset=UTF-8");
        response.setHeader("Content-Disposition", "filename=" + new String((fileName).getBytes(), "iso8859-1"));

        OutputStream out = response.getOutputStream();
        out.write(ba.toByteArray());

        out.flush();
        out.close();
    }

    private ByteArrayOutputStream getYearData() {
        ClassPathResource classPathResource = new ClassPathResource("template/pdf-template.pdf");
        PdfReader reader;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            reader = new PdfReader(classPathResource.getStream());
            bos = new ByteArrayOutputStream();
            PdfStamper stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            Map<String, String> map = new HashMap<>();
            map.put("changeTag1", ReportConstant.CHOOSE_FLAG);
            map.put("changeTag2", ReportConstant.CHOOSE_FLAG);
            map.put("passport0", "88484695274732");
            map.put("passport1", "88484695274733");
            map.put("signDate0", DateUtil.format(new Date(), ReportConstant.DATE_FORMAT_YMS));
            map.put("signDate1", DateUtil.format(new Date(), ReportConstant.DATE_FORMAT_YMS));
            map.put("validDate0", DateUtil.format(new Date(), ReportConstant.DATE_FORMAT_YM));
            map.put("validDate1", DateUtil.format(new Date(), ReportConstant.DATE_FORMAT_YM));
            map.put("orgName0", "贤一坊科技有限公司");
            map.put("orgName1", "贤一坊科技有限公司");
            map.put("remark0", "这是一个备注，里面的内容并不是无限长");
            map.put("remark1", "这是一个备注，里面的内容并不是无限长");

            this.fillPdfCellForm(map, form);

            // true代表生成的PDF文件不可编辑
            stamper.setFormFlattening(true);
            stamper.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return bos;
    }

    private void fillPdfCellForm(Map<String, String> map, AcroFields form) throws IOException, DocumentException {
        for (Map.Entry entry : map.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            form.setField(key, value);
        }
    }
}
