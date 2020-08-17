package com.halle.java.base.jsch;

import com.jcraft.jsch.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
 
public class ChannelShellExample {
   static  String dstDir = "/usr/local/share/";
   static public void main(String[] args) throws JSchException, IOException, InterruptedException {
 
      java.util.Properties config = new java.util.Properties();
      config.put("StrictHostKeyChecking", "no");
 
          JSch jsch = new JSch();
       Session session = jsch.getSession("root", "192.168.1.113", 22);
       session.setConfig(config);
       session.setPassword("super");

       session.connect();
 
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
 
      ChannelShell channel = (ChannelShell) session.openChannel("shell");
      channel.setOutputStream(outputStream);
      PrintStream stream = new PrintStream(channel.getOutputStream());
      channel.connect();

      stream.println("find  "+dstDir+" -name \"*tar*\"");
      stream.flush();
      String ret = waitForPrompt(outputStream);
      System.out.println(waitForPrompt(outputStream));
 
      stream.println("pwd");
      stream.flush();
      waitForPrompt(outputStream);
 
      stream.println("ls");
      stream.flush();
      waitForPrompt(outputStream);
 
      channel.disconnect();
      session.disconnect();
 
   }

    public static String waitForPrompt(ByteArrayOutputStream outputStream) throws InterruptedException {
      int retries = 5;
      for (int x = 1; x < retries; x++) {
         Thread.sleep(1000);
         if (outputStream.toString().indexOf("$") > 0
         || outputStream.toString().indexOf("#") > 0) {
             String ret = outputStream.toString();
            System.out.print(outputStream.toString());
            outputStream.reset();
            return ret;
         }
      }
      return "";
   }
}
 