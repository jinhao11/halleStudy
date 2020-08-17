package com.halle.java.base.jsch;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.io.CharStreams;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JarReplaceHandler {
    private static final Logger log = LoggerFactory.getLogger(JarReplaceHandler.class);
    private static final String PATTERN = "([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])";


    public static String dstPathCovert(String componentName, String serviceName, String pathPrefix) {
        return pathPrefix + "/" + componentName + ".1/" + "bin/" + serviceName + "/lib";
    }


    public static int uploadFiles(ChannelSftp channelSftp, String dstDir, File[] files) throws Exception {
        InputStream inputStream = null;
        int finishSize = 0;
        try {
            if (files != null) {
                for (File file : files) {
                    inputStream = new FileInputStream(file);
                    channelSftp.put(inputStream, dstDir);
                    ++finishSize;
                }
            }
        } finally {
            if (channelSftp != null) {
                channelSftp.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            return finishSize;
        }
    }


    public static boolean uploadFile(ChannelSftp channelSftp, String dstDir, File file) throws Exception {
        InputStream inputStream = null;
        int finishSize = 0;
        try {
            if (file != null) {
                inputStream = new FileInputStream(file);
                channelSftp.put(inputStream, dstDir+"/"+file.getName());
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }


    public static int backupOldFile(Session session, String dstDir, File[] files) {
        ChannelExec channelExec = null;
        ChannelSftp channelSftp = null;
        InputStream in = null;
        try {
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            for (File file : files) {
                channelExec = (ChannelExec) session.openChannel("exec");
                String fileName = file.getName();
                if (!Strings.isNullOrEmpty(fileName)) {
                    Pattern word = Pattern.compile(PATTERN);
                    Matcher match = word.matcher(fileName);
                    match.find();
                    channelExec.setCommand("find " + dstDir + " -name \"*" + fileName.substring(0, match.start()) + "*.jar\"");
                    in = channelExec.getInputStream();
                    channelExec.connect();
                    List<String> list = CharStreams.readLines(new InputStreamReader(in, Charsets.UTF_8));
                    if (list != null && list.size() > 0) {
                        String str =  list.get(0);
                        channelExec.setCommand("cp -f " +str+ " " +str+ ".bak");
                        channelExec.connect();
                        if(list.size()>1){
                            log.error("find jar more than 1,the list is {}",list.toArray());
                        }
                    }
                    channelSftp.connect();
                    uploadFile(channelSftp, dstDir, file);
                    in.close();
                    channelExec.disconnect();
                    //channelSftp.disconnect();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channelExec != null) {
                channelExec.disconnect();
            }
            if (channelSftp != null) {
                channelSftp.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
            if(in != null){
                try {
                    in.close();
                }catch (Exception e){
                    log.error("channelExec's inputstream close error",e);
                }

            }
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        String reg = "([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])";
        String str = "server-1.1.1.jar";
        Pattern word = Pattern.compile(reg);
        Matcher match = word.matcher(str);
        System.out.println(match.find());
        System.out.println(match.start());
        System.out.println(str.substring(0, str.lastIndexOf("-")));
        JSch jsch = new JSch();
        Session session = jsch.getSession("root", "192.168.1.113", 22);
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.setPassword("super");
        session.connect();
        File f = new File("E:\\test\\xxx-1.0.0-SNAPSHOT.jar");
        File f1 = new File("E:\\test\\ccc-1.0.0-SNAPSHOT.jar");

        backupOldFile(session, "/opt/test", new File[]{f,f1});
    }

    /**
     * 通过sftp上传文件到指定路径
     *
     * @param session 会话
     * @param dstDir  指定路径
     * @param files   要替换的文件
     * @return
     * @throws Exception
     */
    public static int upload(Session session, String dstDir, File[] files) throws Exception {
        InputStream inputStream = null;
        int finishSize = 0;
        if (session == null) {
            return 0;
        }
        ChannelSftp channelSftp = null;
        try {
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            if (files != null) {
                for (File file : files) {
                    inputStream = new FileInputStream(file);
                    channelSftp.put(inputStream, dstDir);
                    ++finishSize;
                }
            }
        } finally {
            if (channelSftp != null) {
                channelSftp.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            return finishSize;
        }
    }


}