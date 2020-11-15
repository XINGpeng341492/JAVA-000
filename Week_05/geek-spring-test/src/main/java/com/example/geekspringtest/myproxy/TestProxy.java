package com.example.geekspringtest.myproxy;

import java.lang.reflect.Proxy;

/**
 * <p>TODO
 * </p>
 *
 * @author xingpeng
 * @date 2020/11/15 8:52 下午
 **/
public class TestProxy {

    public static void main(String[] args) {
        TargetService targetService = new TargetServiceImpl();
        MyJDKProxy jdkProxy = new MyJDKProxy(targetService);
        TargetService targetProxyService =
                (TargetService)Proxy.newProxyInstance(targetService.getClass().getClassLoader(),
                        targetService.getClass().getInterfaces(), jdkProxy);
        targetProxyService.tagetMethod();

    }
}
