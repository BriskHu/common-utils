package com.briskhu.common.jgui.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 操作系统相关的工具方法<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-04
 **/
public class OsTools {
    private static final Logger LOGGER = LoggerFactory.getLogger(OsTools.class);

    /* ---------------------------------------- fileds ---------------------------------------- */
    private static final String WINDOW = "Windows";
    private static final String LINUX = "Linux";
    private static final String UNIX = "Unix";


    /* ---------------------------------------- methods ---------------------------------------- */
    /**
     * windows :
     *   [initFileDir] os.name=Windows 10,os.arch=amd64,os.version=10.0,user.name=Administrator,user.home=C:\Users\Administrator,
     *   user.dir=E:\Programs\2018\Git\common_utils_git\single-packages/util-aes-qr-single
     * Linux:
     *   [initFileDir] os.name=Linux,os.arch=amd64,os.version=4.4.169-1.el7.elrepo.x86_64,user.name=root,user.home=/root, user.dir=/data/tempTest
     */
    public static void printOsInfo() {
        String os = System.getProperty("os.name");
        String arch = System.getProperty("os.arch");
        String version = System.getProperty("os.version");
        String username = System.getProperty("user.name");
        String userhome = System.getProperty("user.home");
        String userdir = System.getProperty("user.dir");
        LOGGER.debug("[initFileDir] os.name={},os.arch={},os.version={},user.name={},user.home={}," +
                "user.dir={}", os, arch, version, username, userhome, userdir);
        System.out.println("[initFileDir] os.name=" + os);
        System.out.println("[initFileDir] os.arch=" + arch);
        System.out.println("[initFileDir] os.version=" + version);
        System.out.println("[initFileDir] user.name=" + username);
        System.out.println("[initFileDir] user.home=" + userhome);
        System.out.println("[initFileDir] user.dir=" + userdir);
    }

    /**
     * @return
     */
    public static String initDefaultDir() {
        String osName = System.getProperty("os.name");
        if (osName.indexOf(WINDOW)!=-1 || osName.indexOf(WINDOW.toLowerCase())!=-1){
            osName=WINDOW;
        }else if (osName.toLowerCase().indexOf(LINUX.toLowerCase()) != -1){
            osName = LINUX;
        }

        return initDefaultDir(osName);
    }

    /**
     * @param osName
     * @return
     */
    public static String initDefaultDir(String osName) {
        if (osName.equals(WINDOW)) {
            return "C:\\Users\\Administrator\\Desktop\\QrImages";
        } else {
            return System.getProperty("user.dir")+"/QrImages";
        }
    }
}


