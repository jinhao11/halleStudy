package com.halle.java.base.thread.pool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SimpleHttpServer {
    static ThreadPool<HttpRequestHandler> pool;
    static String basePath ;
    static ServerSocket serverSocket;
    static int port = 9999;
    static final Set<String> STATIC_RESOURCE ;
    static{
        STATIC_RESOURCE = new HashSet<String>();
        STATIC_RESOURCE.addAll(Arrays.asList(new String[]{"jpg","png","ico"}));
    }

    public static void setBasePath(String basePath) {
        if(null != basePath && new File(basePath).exists() && new File(basePath).isDirectory())
             SimpleHttpServer.basePath = basePath;
    }

    public static void setPort(int port) {
        if(port > 0)
            SimpleHttpServer.port = port;
    }

    public static void setPoolSize(int poolSize) {
        pool = new MyThreadPool<HttpRequestHandler>(poolSize);
    }

    public static void start() throws IOException {
        serverSocket = new ServerSocket(port);
        Socket socket = null;
        while(null != (socket = serverSocket.accept() ) ){
            pool.execute(new HttpRequestHandler(socket));
        }
    }

    static class  HttpRequestHandler implements Runnable{
        Socket socket;


        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null;
            InputStream in = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();


                String filePath = basePath + header.split(" ")[1];
                out = new PrintWriter(socket.getOutputStream());

                //如果读取资源为静态资源，则读取并输出
                String suffix = filePath.substring(filePath.indexOf(".")+1);
                if( STATIC_RESOURCE.contains( suffix ) ){
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    int i;
                    while( ( i = in.read() ) != -1){
                        byteArrayOutputStream.write(i);
                    }

                    byte[] array = byteArrayOutputStream.toByteArray();

                    /*StringBuilder builder = new StringBuilder();
                    builder.append("HTTP/1.1 200 OK\n");
                    builder.append("Server: Molly\n");
                    builder.append("Content-Type: image/jpeg\n");
                    builder.append("Content-Length: "+array.length+"\n");
                    builder.append("\n");
                    byte[] headByte = builder.toString().getBytes();
                    byte[] returnByte = new byte[headByte.length+array.length];
                    System.arraycopy(headByte,0,returnByte,0,headByte.length);
                    System.arraycopy(array,0,returnByte,headByte.length,array.length);
                    socket.getOutputStream().write(returnByte,0,returnByte.length);*/


                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: image/jpeg");
                    out.println("Content-Length: "+array.length);
                    out.println("");
                    out.flush();
                    socket.getOutputStream().write(array, 0, array.length);
                    socket.close();

                }else{
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out = new PrintWriter(socket.getOutputStream());
                    out.println("HTTP/1.1 200 OK");
                    out.println("Content-Type: text/html; charset=UTF-8");
                    out.println("");

                    while((line = br.readLine()) != null){
                        out.println(line);
                    }

                    out.flush();


                }

            } catch (IOException e) {
               out.println("HTTP/1.1 500");
               out.println("");
               out.flush();
            }finally {
                close(br,in,reader,out,socket);
            }
        }
    }


    private static void close(Closeable... closeables){
        if(closeables!=null){
            for (Closeable closeable : closeables) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        String line = null;
        BufferedReader br = null;
        BufferedReader reader = null;
        PrintWriter out = null;
        InputStream in = null;
        FileOutputStream fileOutputStream;
        try {


            //如果读取资源为静态资源，则读取并输出


                in = new FileInputStream("D://httpServer/1.jpg");
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                int i;
                while( ( i = in.read() ) != -1){
                    byteArrayOutputStream.write(i);
                }

                byte[] array = byteArrayOutputStream.toByteArray();

                fileOutputStream = new FileOutputStream(new File("D://httpServer/test.jpg"));
                fileOutputStream.write(array);


        } catch (IOException e) {
            out.println("HTTP/1.1 500");
            out.println("");
            out.flush();
        }finally {

        }
    }

}
