package com.kevin.common.rpc.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    protected HelloServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String someOne) throws RemoteException {
        System.out.println("server accpet " + someOne);
        return "I am service ," + someOne;
    }
}
