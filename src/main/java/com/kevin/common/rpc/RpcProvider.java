package com.kevin.common.rpc;

import com.kevin.common.rpc.service.HelloService;
import com.kevin.common.rpc.service.impl.HelloServiceImpl;

/**
 * Created by kevin on 2018/4/10.
 */
public class RpcProvider {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service, 1234);
    }
}
