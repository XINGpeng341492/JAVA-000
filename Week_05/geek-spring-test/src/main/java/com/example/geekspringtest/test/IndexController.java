package com.example.geekspringtest.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>TODO
 * </p>
 *
 * @author xingpeng
 * @date 2020/11/15 8:22 下午
 **/
@RestController
@RequestMapping(value = "/index")
public class IndexController {

    @RequestMapping("/test")
    public String testIndex(){
        return "indextest";
    }


}
