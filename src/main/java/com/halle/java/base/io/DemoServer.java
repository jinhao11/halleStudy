package com.halle.java.base.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

public class DemoServer extends Thread {
    private ServerSocket serverSocket;
    public int getPort() {
    return serverSocket.getLocalPort();
    }
    public void run() {
        try {
            serverSocket = new ServerSocket(8000);
            while (true) {
                Socket socket = serverSocket.accept();
                RequestHandler requestHandler = new RequestHandler(socket);
                requestHandler.start();
                /*try (PrintWriter out = new PrintWriter(socket.getOutputStream())) {
                    out.println(socket.getRemoteSocketAddress()+" Hello world!");
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                     e.printStackTrace();
                }

            }
         }
    }
    public static void main(String[] args) throws IOException {
        int sleep_time = 100000;
        DemoServer server = new DemoServer();
        server.start();
        try (Socket client = new Socket(InetAddress.getLocalHost(), 8000)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            bufferedReader.lines().forEach(s -> System.out.println(s));
            PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
            writer.print("H");
            LockSupport.parkNanos(sleep_time);
            writer.print("e");
            LockSupport.parkNanos(sleep_time);
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
        }
        /*int num = 5;
        ExecutorService executorService =  Executors.newFixedThreadPool(num);
        for (int i = 0; i < num; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try (Socket client = new Socket(InetAddress.getLocalHost(), server.getPort())) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        bufferedReader.lines().forEach(s -> System.out.println(s));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }*/

      /*  for (int i = 0; i < 3; i++) {
            try (Socket client = new Socket(InetAddress.getLocalHost(), server.getPort())) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                bufferedReader.lines().forEach(s -> System.out.println(s));
            }catch (Exception e){
                e.printStackTrace();
            }
        }*/


    }
}


class RequestHandler extends Thread {
    private Socket socket;
    RequestHandler(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try(BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));){


            // 从InputStream当中读取客户端所发送的数据
            String inputLine = null;
            long b=System. currentTimeMillis ();
            while ((inputLine = is.readLine()) != null)
            {
                System.out.println(inputLine);
            }
            long e=System. currentTimeMillis ();
            System. out.println ("spend:"+(e - b)+" ms ");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}