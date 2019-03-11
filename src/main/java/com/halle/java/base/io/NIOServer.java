package com.halle.java.base.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer extends Thread {
    //1 多路复用器（管理所有的通道）
    private Selector selector;
    //2 建立缓冲区
    private ByteBuffer readBuf = ByteBuffer.allocate(1024);
    //3
    private ByteBuffer writeBuf = ByteBuffer.allocate(1024);

    public NIOServer(int port){

        try {
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            selector = Selector.open();
            serverSocket.bind(new InetSocketAddress( port));
            serverSocket.configureBlocking(false);
            serverSocket.register(selector, SelectionKey.OP_ACCEPT );

        }catch (Exception e){
            try {
                selector.close();
            }catch (Exception e1){
                e.printStackTrace();
            }
        }



    }

    public void run() {
        while (true) {
            try {// 创建Selector和Channel
                selector.select();// 阻塞等待就绪的Channel，这是关键点之一
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    iter.remove();
                    if(key.isValid()){
                        if(key.isReadable()){
                            read(key);
                        }
                        if(key.isAcceptable()){
                            accept(key);
                        }
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void accept(SelectionKey key) {
        try {
            //1 获取服务通道
            ServerSocketChannel ssc =  (ServerSocketChannel) key.channel();
            //2 执行阻塞方法
            SocketChannel sc = ssc.accept();
            //3 设置阻塞模式
            sc.configureBlocking(false);
            //4 注册到多路复用器上，并设置读取标识
            sc.register(this.selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read(SelectionKey key) {
        try {
            //1 清空缓冲区旧的数据
            this.readBuf.clear();
            //2 获取之前注册的socket通道对象
            SocketChannel sc = (SocketChannel) key.channel();
            //3 读取数据
            int count = sc.read(this.readBuf);
            //4 如果没有数据
            if(count == -1){
                key.channel().close();
                key.cancel();
                return;
            }
            //5 有数据则进行读取 读取之前需要进行复位方法(把position 和limit进行复位)
            this.readBuf.flip();
            //6 根据缓冲区的数据长度创建相应大小的byte数组，接收缓冲区的数据
            byte[] bytes = new byte[this.readBuf.remaining()];
            //7 接收缓冲区数据
            this.readBuf.get(bytes);
            //8 打印结果
            String body = new String(bytes).trim();
            System.out.println("Server : " + body);

            // 9..可以写回给客户端数据

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        NIOServer server = new NIOServer(8888);
        server.run();
    }

}