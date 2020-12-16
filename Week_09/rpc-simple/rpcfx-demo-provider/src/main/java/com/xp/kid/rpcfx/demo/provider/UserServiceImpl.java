package com.xp.kid.rpcfx.demo.provider;

import com.xp.kid.rpcfx.demo.api.User;
import com.xp.kid.rpcfx.demo.api.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "xptest" + System.currentTimeMillis());
    }
}
