package com.kevin.common.proxy.service;

import java.lang.reflect.Method;

public abstract class AbstractProxyFactory {
    public Object invoke(Method method, Object targetObject,Object[] objects){
        Object object = null;
        //advice() 前置通知
        System.out.println("before advice");
        try {
            object = method.invoke(targetObject,objects);
            //afteradvice() 后置通知
            System.out.println("after advice");
        }catch (Exception e){
            //exceptionadvice() 异常列外通知
            System.out.println("exception advice");
        }finally {
            //finallyadvice()  最终通知
            System.out.println("finally advice");
        }
        return object;
    }
}

