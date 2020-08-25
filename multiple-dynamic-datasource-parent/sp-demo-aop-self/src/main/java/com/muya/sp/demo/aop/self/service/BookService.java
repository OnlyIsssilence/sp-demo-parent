package com.muya.sp.demo.aop.self.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.muya.sp.demo.aop.self.entity.BookEntity;
import com.muya.sp.demo.aop.self.entity.UserEntity;
import com.muya.sp.demo.aop.self.mapper.db2.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright 2020 OnlySilence, Inc. All rights reserved.
 *
 * @Author: MuYa
 * @Date: 2020-08-24
 * @Time: 15:24
 * @Description:
 */
@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    public List<UserEntity> getTotal(){
        return bookMapper.getTotal();
    }


    public List<BookEntity> getAll() {
        QueryWrapper<BookEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("update_time");
        return bookMapper.selectList(queryWrapper);
    }
}
