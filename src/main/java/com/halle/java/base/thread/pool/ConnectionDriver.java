package com.halle.java.base.thread.pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public class ConnectionDriver implements InvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if( "commit".equals( method.getName() ) ){
            Thread.sleep(500);
        }
        return null;
    }



    public static Connection newConnection(){
        return  (Connection)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                                                    new Class[]{Connection.class} , new ConnectionDriver());
    }
}
