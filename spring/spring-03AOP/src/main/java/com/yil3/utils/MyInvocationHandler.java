package com.yil3.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {
    private Object obj = null;

    public Object bind(Object object){
        this.obj = object;
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object res = method.invoke(this.obj,args);
        System.out.println(method.getName()+"的结果是"+res);
        return res;
    }
}
