package com.xp.kid.cachedemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xp.kid.cachedemo.entity.User;
import com.xp.kid.cachedemo.service.UserService;

@RestController
@EnableAutoConfiguration
public class UserController {

    @Autowired
    UserService userService;
    
    @RequestMapping("/user/find")
    User find(Integer id) {
        return userService.find(id);
        //return new User(1,"KK", 28);
    }

    @RequestMapping("/user/list")
    List<User> list() {
        return userService.list();
//        return Arrays.asList(new User(1,"KK", 28),
//                             new User(2,"CC", 18));
    }

    @RequestMapping("/user/buy")
    String buy (@RequestParam("userId") Integer userId,
                @RequestParam("num") Integer num) {
        return userService.buy(userId,num);
    }


}