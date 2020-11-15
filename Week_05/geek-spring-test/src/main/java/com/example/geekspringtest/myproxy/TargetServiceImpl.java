package com.example.geekspringtest.myproxy;

/**
 * <p>TODO
 * </p>
 *
 * @author xingpeng
 * @date 2020/11/15 8:35 下午
 **/

public class TargetServiceImpl implements TargetService{
    @Override
    public void tagetMethod() {
        System.out.println("jdk proxy target method");
    }
}
