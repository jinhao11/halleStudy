package com.halle.java.base.thread.pool;

import java.io.IOException;

public class SimpleHttpServerTest {
    public static void main(String[] args) {
        SimpleHttpServer.setBasePath("D://httpServer");
        SimpleHttpServer.setPort(8080);
        SimpleHttpServer.setPoolSize(50);
        try {
            SimpleHttpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
