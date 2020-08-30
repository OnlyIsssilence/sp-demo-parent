package com.muya.sp.demo.pdf.entity;

import lombok.Data;

import java.util.List;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-28
 * @Time: 23:08
 * @Description:
 */
@Data
public class ExamPaperQuestion {

    private String questionQuence;
    private String questionContent;
    private List<ExamPaperOption> options;

}
