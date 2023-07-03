package com.sdu.springframework.factory;

import com.sdu.springframework.proxy.SmsServiceInvocationHandler;

import java.lang.reflect.Proxy;
import java.util.Arrays;

public class JdkProxyFactory {

    // 通过Proxy.newProxyInstance方法获取某个类的代理对象
    public static Object getProxy(Object target) {
        System.out.println("Class Loader: " + target.getClass().getClassLoader());
        System.out.println("Interfaces: " + Arrays.toString(target.getClass().getInterfaces()));
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new SmsServiceInvocationHandler(target)
        );
    }
}
