package com.halle.java.base.io.bio;

import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

public class BioClient {
    public static void main(String[] args) {
        int num = 100;
        int sleep_time = 10000000;
        ExecutorService executorService =  Executors.newFixedThreadPool(3);
        for (int i = 0; i < num; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Socket client = new Socket();
                        client.connect(new InetSocketAddress("localhost", 8000));
                        PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
                        writer.print("H");
                        Thread.sleep(5000);
                        //LockSupport.parkNanos(sleep_time);
                        writer.print("e");
                        Thread.sleep(1000);
                        writer.print("l");
                        LockSupport.parkNanos(sleep_time);
                        writer.print("l");
                        LockSupport.parkNanos(sleep_time);
                        writer.print("o");
                        LockSupport.parkNanos(sleep_time);
                        writer.print("!");
                        LockSupport.parkNanos(sleep_time);
                        writer.println();
                        writer.flush();
                    }catch(Exception e)
                    {
                    }
                }
            });

        }
    }
}
