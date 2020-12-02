package com.example.week7homework.dao;

import org.springframework.stereotype.Repository;

import com.example.week7homework.entity.Order;
@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}