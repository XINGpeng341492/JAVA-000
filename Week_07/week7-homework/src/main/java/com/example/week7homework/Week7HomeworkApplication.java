package com.example.week7homework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@SpringBootApplication()
@MapperScan("com.example.week7homework.dao")
public class Week7HomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week7HomeworkApplication.class, args);
    }

}
