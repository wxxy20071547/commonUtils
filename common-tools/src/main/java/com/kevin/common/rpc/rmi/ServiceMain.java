package com.kevin.common.rpc.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMISocketFactory;


public class ServiceMain {
    public static void main(String[] args) throws Exception{
        // 本地主机上的远程对象注册表Registry的实例，并指定端口为8801，这一步必不可少（Java默认端口是1099），必不可缺的一步，缺少注册表创建，则无法绑定对象到远程注册表上
        LocateRegistry.createRegistry(8801);
        //指定通讯端口，防止被防火墙拦截
        RMISocketFactory.setSocketFactory(new CustomerSocketFactory());

        HelloService helloService = new HelloServiceImpl();

        // 把远程对象注册到RMI注册服务器上，并命名为helloService
        //绑定的URL标准格式为：rmi://host:port/name
        Naming.bind("rmi://localhost:8801/helloService",helloService);
        System.out.println("ServiceMain provide RPC service now.");
    }
}
