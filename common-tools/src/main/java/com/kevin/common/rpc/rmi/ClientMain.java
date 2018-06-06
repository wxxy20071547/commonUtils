package com.kevin.common.rpc.rmi;

import java.rmi.Naming;

public class ClientMain {
    public static void main(String[] args)throws Exception {
        //服务引入
        HelloService helloService = (HelloService) Naming.lookup("rmi://localhost:8801/helloService");
        //调用远程方法
        for (int i = 0; i < 20; i++) {
            System.out.println("RMI服务器返回的结果是 " + helloService.sayHello("call num:"+ i));
            Thread.sleep(1000);
        }

    }
}
