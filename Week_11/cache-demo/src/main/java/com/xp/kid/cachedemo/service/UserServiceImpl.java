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

}
