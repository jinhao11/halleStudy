package com.halle.java.base.proxy.cglib;

public class CglibProxyTest {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                while (true){
                    System.out.println(1);
                }
            }
        }).start();
    }
}
