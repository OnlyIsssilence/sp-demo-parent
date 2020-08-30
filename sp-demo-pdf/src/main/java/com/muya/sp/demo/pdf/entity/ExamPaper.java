package com.muya.sp.demo.pdf.entity;

import lombok.Data;

import java.util.List;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-28
 * @Time: 23:05
 * @Description:
 */
@Data
public class ExamPaper {
    private String title;

    private List<BigQuestion> bigQuestions;
}
