package com.muya.sp.demo.aop.self;

import com.alibaba.fastjson.JSON;
import com.muya.sp.demo.aop.self.service.BookService;
import com.muya.sp.demo.aop.self.service.CombineService;
import com.muya.sp.demo.aop.self.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class SpDemoAopSelfApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CombineService combineService;

    @Test
    void dsAnnotationTest() {
        log.info("master db data:" + JSON.toJSONString(userService.getAll().get(0)));
        log.info("slave db data:" + JSON.toJSONString(bookService.getTotal().get(0)));
//        log.info("combine db data:" + combineService.getAll());
    }
}
