package com.example.geekspringtest.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.geekspringtest.config.MyBean;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>TODO
 * </p>
 *
 * @author xingpeng
 * @date 2020/11/15 8:22 下午
 **/
@RestController
@RequestMapping(value = "/index")
@Slf4j
public class IndexController {

    @Autowired
    private MyBean myBean;

    @RequestMapping("/test")
    public String testIndex(){
        String name = myBean.getName();
        Integer age = myBean.getAge();
        log.info("age:{},name{}",age,name);
        return "indextest";
    }


}
