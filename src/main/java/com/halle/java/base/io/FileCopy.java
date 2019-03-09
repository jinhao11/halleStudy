package com.halle.java.base.io;

import org.junit.Test;

import java.io.*;
import java.nio.channels.FileChannel;

public class FileCopy {
    public  void copyFileByStream(File source, File des) throws
            IOException {
        try (InputStream is = new FileInputStream(source);
             OutputStream os = new FileOutputStream(des)){
            byte[] bufer = new byte[1024];
            int length;
            while ((length = is.read(bufer)) > 0) {
                os.write(bufer, 0, length);
            }
        }
    }


    public  void copyFileByChannel(File source, File des) throws
            IOException {
        try (FileChannel sourceChannel = new FileInputStream(source)
                .getChannel();
             FileChannel targetChannel = new FileOutputStream(des).getChannel()){
            for (long count = sourceChannel.size() ;count>0 ;) {
                long transferred = sourceChannel.transferTo(
                        sourceChannel.position(), count, targetChannel);
                sourceChannel.position(sourceChannel.position() + transferred);
                count -= transferred;
            }
        }
    }

    @Test
    public  void testStream() {
       try {
           for (int i = 0; i < 100; i++) {
               copyFileByStream(new File("E:\\apache-tomcat-9.0.10-windows-x64.zip"),new File("F:\\apache-tomcat-9.0.10-windows-x64.zip"));
           }
       }catch (Exception e ){

       }

    }


    @Test
    public  void testChannel() {
        try {
            for (int i = 0; i < 100; i++) {
                copyFileByChannel(new File("E:\\apache-tomcat-9.0.10-windows-x64.zip"),new File("F:\\apache-tomcat-9.0.10-windows-x64.zip"));
            }
        }catch (Exception e ){

        }

    }

}
