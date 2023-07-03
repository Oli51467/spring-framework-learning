package com.sdu.springframework.factory;

import com.sdu.springframework.interceptor.AliSmsInterceptor;
import net.sf.cglib.proxy.Enhancer;

public class CglibProxyFactory {

    public static Object getProxy(Class<?> clazz) {
        // 创建动态代理增强类子类
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
        // 设置被代理类
        enhancer.setSuperclass(clazz);
        // 设置方法拦截器
        enhancer.setCallback(new AliSmsInterceptor());
        // 创建代理类
        return enhancer.create();
    }
}

