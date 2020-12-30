package com.xp.kid.cachedemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.xp.kid.cachedemo.entity.User;
import com.xp.kid.cachedemo.mapper.UserMapper;
import com.xp.kid.cachedemo.redis.XpRedisTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private XpRedisTemplate redisTemplate;

    // 开启spring cache
    //@Cacheable(key="#id",value="userCache")
    public User find(int id) {
        String key = String.format("xptest:id:%s",id);
        log.info("locKey===:{}",key);
        boolean lockFlag = redisTemplate.setNX(key,1,10);
        if (lockFlag) {
            log.info("lock success");
        } else {
            log.info("lock fail");
        }



        redisTemplate.set("aaaa","testcache");
        String str =  (String)redisTemplate.get("aaaa");
        log.info("cache===:{}",str);
        log.info(" ==> find " + id);
        return userMapper.find(id);
    }

    // 开启spring cache
    //@Cacheable //(key="methodName",value="userCache")
    public List<User> list(){
        return userMapper.list();
    }

    @Override
    public String buy(Integer userId ,Integer num) {
        String key  = "key:" + userId;
        log.info("locKey===:{}",key);
        boolean lockFlag = redisTemplate.setNX(key,1,5);
        if (lockFlag) {
            Object stock =  redisTemplate.get("stock");
            if (null != stock) {
                Integer stockNum = (Integer) stock;
                log.info("stockNum===:{}",stockNum);
                if(stockNum.intValue() <= 0) {
                    return "库存不足";
                } else {
                    redisTemplate.decr("stock",num);
                    return "success";
                }
            }
        } else {
            return "too many request";
        }
        return "fail";
    }

}
