package com.kevin.common.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyFactory extends AbstractProxyFactory implements InvocationHandler {

    private Object targetObject;

    public Object createProxyIntance(Object targetObject) {
        this.targetObject = targetObject;
        return Proxy.newProxyInstance(this.targetObject.getClass().getClassLoader(),
            this.targetObject.getClass().getInterfaces(), this);
    }

    /**
     * @param proxy
     *            指代理的类 如：com.kevin.common.proxy.impl.PeopleServiceImpl
     * @param method
     *            指代理类的方法 如：public abstract void com.kevin.common.proxy.service.PeopleService.say(java.lang.String)
     * @param args
     *            指参数 如：jdk proxy is ok
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return invoke(method, this.targetObject, args);
    }
}
