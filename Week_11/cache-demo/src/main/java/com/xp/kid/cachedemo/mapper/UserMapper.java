package com.xp.kid.cachedemo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xp.kid.cachedemo.entity.User;

@Mapper
public interface UserMapper {

    User find(int id);

    List<User> list();

}
