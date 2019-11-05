package com.kevin.common.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxyFactory extends AbstractProxyFactory implements MethodInterceptor {
    private Object targetObject;

    public Object createProxyIntance(Object targetObject) {
        this.targetObject = targetObject;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.targetObject.getClass());
        enhancer.setCallback((Callback)this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return invoke(method, this.targetObject, objects);
    }
}
