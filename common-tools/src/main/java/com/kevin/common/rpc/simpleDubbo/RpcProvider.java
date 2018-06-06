package com.kevin.common.rpc.simpleDubbo;


import com.kevin.common.rpc.simpleDubbo.service.HelloService;
import com.kevin.common.rpc.simpleDubbo.service.impl.HelloServiceImpl;

/**
 * Created by kevin on 2018/4/10.
 */
public class RpcProvider {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service, 1234);
    }
}
