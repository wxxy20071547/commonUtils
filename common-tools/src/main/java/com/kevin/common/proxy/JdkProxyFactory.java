package com.kevin.common.proxy;

import com.kevin.common.proxy.service.AbstractProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyFactory extends AbstractProxyFactory implements InvocationHandler{

    private Object targetObject;

    public Object createProxyIntance(Object targetObject){
       this.targetObject = targetObject;
       return Proxy.newProxyInstance(this.targetObject.getClass().getClassLoader()
               ,this.targetObject.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return invoke(method,this.targetObject,args);
    }
}
