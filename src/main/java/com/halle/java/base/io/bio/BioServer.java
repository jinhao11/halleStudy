package com.halle.java.base.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServer {
  public static void main(String[] args) throws Exception {
        ServerSocket echoServer = null;
        Socket clientSocket = null;
      ExecutorService tp = Executors.newFixedThreadPool(1);
        try {
            echoServer = new ServerSocket(8000);
        } catch (IOException e) {
            System.out.println(e);
        }
        while (true) {
            try {
                clientSocket = echoServer.accept();
                System.out.println(clientSocket.getRemoteSocketAddress()
                        + " connect!");
                tp.execute(new HandleMsg(clientSocket));
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    static class HandleMsg implements Runnable{
        Socket clientSocket = null;
        public HandleMsg( Socket clientSocket) {
            this.clientSocket=clientSocket;
        }

        public void run(){
            try {
                BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter os = new PrintWriter(clientSocket.getOutputStream(), true);
                // 从InputStream当中读取客户端所发送的数据
                String inputLine = null;
                long b=System. currentTimeMillis ();
                while ((inputLine = is.readLine()) != null)
                {
                    System.out.println(inputLine);
                }
                long e=System. currentTimeMillis ();
                System. out.println ("spend:"+(e - b)+" ms ");
            } catch (IOException e) {
                e.printStackTrace();
            }finally
            {
            }
        }
    }
}
