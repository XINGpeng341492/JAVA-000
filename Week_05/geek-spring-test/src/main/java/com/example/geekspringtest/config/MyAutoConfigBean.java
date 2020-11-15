package com.example.geekspringtest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>TODO
 * </p>
 *
 * @author xingpeng
 * @date 2020/11/15 10:08 下午
 **/
@Configuration
public class MyAutoConfigBean {

    @Bean
    public MyBean getMyBean() {
        MyBean myBean = new MyBean();
        myBean.setAge(18);
        myBean.setName("xptest");
        return myBean;
    }


}
