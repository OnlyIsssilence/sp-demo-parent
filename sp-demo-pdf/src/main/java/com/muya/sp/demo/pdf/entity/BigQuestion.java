package com.muya.sp.demo.pdf.entity;

import lombok.Data;

import java.util.List;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-28
 * @Time: 23:07
 * @Description:
 */
@Data
public class BigQuestion {
    private List<ExamPaperQuestion> questions;
    private String bigQuestionName;

}
