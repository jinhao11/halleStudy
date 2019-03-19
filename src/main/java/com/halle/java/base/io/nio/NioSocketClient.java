package com.halle.java.base.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioSocketClient {
    public static final int CLIENT_NUM = 3;
    public static final int TALK_NUM = 1000000;
    public static final String SOCKET_IP = "127.0.0.1";


    //需要一个Selector
    public static void main(String[] args) {
        ExecutorService executorService =  Executors.newFixedThreadPool(CLIENT_NUM);
        for (int i = 0; i < CLIENT_NUM; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    //创建连接的地址
                    InetSocketAddress address = new InetSocketAddress(SOCKET_IP, 8888);

                    //声明连接通道
                    SocketChannel sc = null;


                    String no = UUID.randomUUID().toString();

                    try {
                        //打开通道
                        sc = SocketChannel.open();
                        //进行连接
                        sc.connect(address);
                        for (int j = 0; j < TALK_NUM; j++) {
                            //把数据放到缓冲区中
                            //建立缓冲区
                            ByteBuffer writeBuffer = ByteBuffer.allocate(512);
                            writeBuffer.put(String.format("i am NO.%s client,this is my No.%d message",no,j).getBytes());
                            //对缓冲区进行复位
                            writeBuffer.flip();
                            //写出数据
                            sc.write(writeBuffer);
                            //清空缓冲区数据
                            writeBuffer.clear();

                            Thread.sleep(2000);
                        }

                    } catch (Exception e) {
                        System.out.println(no +" is error");
                        System.out.println(e.getMessage());
                    }
                    finally {
                        if(sc != null){
                            try {
                                sc.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });

        }



    }
}
