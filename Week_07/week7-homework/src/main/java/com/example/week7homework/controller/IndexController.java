package com.example.week7homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.example.week7homework.config.Master;
import com.example.week7homework.config.Slave;
import com.example.week7homework.dao.AccountMapper;
import com.example.week7homework.dao.GoodsMapper;
import com.example.week7homework.entity.Account;
import com.example.week7homework.entity.Goods;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>TODO
 * </p>
 *
 * @author xingpeng
 * @date 2020/12/1 2:27 下午
 **/
@RestController
@RequestMapping("/index")
@Slf4j
public class IndexController {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Master
    @RequestMapping(value = "/testMaster" , method = {RequestMethod.GET , RequestMethod.POST})
    public String testMaster(@RequestParam(name = "id" , required = false) Long id) {
        Account account = accountMapper.selectByPrimaryKey(id);
        log.info("account:{}", JSONObject.toJSONString(account));
        return "testMaster success";
    }

    @Slave
    @RequestMapping(value = "/testSlave" , method = {RequestMethod.GET , RequestMethod.POST})
    public String testSlave(@RequestParam(name = "id" , required = false) Long id) {
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        log.info("goods:{}", JSONObject.toJSONString(goods));
        return "testSlave success";
    }

}
