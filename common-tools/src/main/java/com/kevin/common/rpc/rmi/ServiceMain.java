package com.kevin.common.rpc.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMISocketFactory;


public class ServiceMain {
    public static void main(String[] args) throws Exception{
        LocateRegistry.createRegistry(8801);
        //指定通讯端口，防止被防火墙拦截
        RMISocketFactory.setSocketFactory(new CustomerSocketFactory());

        HelloService helloService = new HelloServiceImpl();
        Naming.bind("rmi://localhost:8801/helloService",helloService);
        System.out.println("ServiceMain provide RPC service now.");
    }
}
