package com.halle.java.base.jsch;

import com.google.common.io.CharStreams;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import kotlin.text.Charsets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ChannelExecExample {
    static  String dstDir = "/usr/local/share/";

    static public void main(String[] args) throws JSchException, IOException, InterruptedException {
 
      java.util.Properties config = new java.util.Properties();
      config.put("StrictHostKeyChecking", "no");
 
       JSch jsch = new JSch();
       Session session = jsch.getSession("root", "192.168.1.113", 22);
       session.setConfig(config);
       session.setPassword("super");

       session.connect();
 
      ChannelExec ce = (ChannelExec) session.openChannel("exec");
      ce.setCommand("find  "+dstDir+" -name \"*tar*\"");
 
      InputStream in = ce.getInputStream();
      ce.connect();
      List<String> line =  CharStreams.readLines(new InputStreamReader(in,Charsets.UTF_8));
        for (String s : line) {
            if(s != null && !s.isEmpty()){
                //String dstFileName = s.substring(s.lastIndexOf(dstDir));
                System.out.println(s);
                ce.setCommand("\\cp "+s+" "+s+".bak");
                ce.setCommand("rm -rf "+s);
            }
        }



      ce.disconnect();
      session.disconnect();
   }
}