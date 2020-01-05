package com.briskhu.common.jgui.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

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
     * [initFileDir] os.name=Windows 10,os.arch=amd64,os.version=10.0,user.name=Administrator,user.home=C:\Users\Administrator,
     * user.dir=E:\Programs\2018\Git\common_utils_git\single-packages/util-aes-qr-single
     * Linux:
     * [initFileDir] os.name=Linux,os.arch=amd64,os.version=4.4.169-1.el7.elrepo.x86_64,user.name=root,user.home=/root, user.dir=/data/tempTest
     */
    public static void printOsInfo() {
        String os = System.getProperty("os.name");
        String arch = System.getProperty("os.arch");
        String version = System.getProperty("os.version");
        String username = System.getProperty("user.name");
        String userhome = System.getProperty("user.home");
        String userdir = System.getProperty("user.dir");
        LOGGER.debug("[initFileDir] \nos.name={},\nos.arch={},\nos.version={},\nuser.name={},\n" +
                "user.home={},\nuser.dir={}", os, arch, version, username, userhome, userdir);
    }

    /**
     * 获取用户默认的home路径
     *
     * @return
     */
    public static String getUserHome() {
        String osShortName = getOsShortName();

        if (osShortName.equals(WINDOW)) {
            return System.getProperty("user.dir") + "\\";
        } else {
            return System.getProperty("user.dir") + "/";
        }
    }

    /**
     * 获取当前操作系统的短名称
     *
     * @return
     */
    public static String getOsShortName() {
        String osName = System.getProperty("os.name");
        if (osName.indexOf(WINDOW) != -1 || osName.indexOf(WINDOW.toLowerCase()) != -1) {
            osName = WINDOW;
        } else if (osName.toLowerCase().indexOf(LINUX.toLowerCase()) != -1) {
            osName = LINUX;
        }

        return osName;
    }

    /**
     * 获取指定默认路径
     * 默认路径是程序当前运行(安装)路径 + 指定的根路径名称
     *
     * @param pathname 路径名不需要以“/”开头。
     * @return
     */
    public static String getDefaultDir(String pathname) {
        return getUserHome() + pathname;
    }

    /**
     * 获取默认路径
     * 默认路径是程序当前运行(安装)路径
     *
     * @return
     */
    public static String getDefaultDir() {
        return getUserHome();
    }

    /**
     * 创建目录
     * 如果首次创建失败，会重试三次。如果三次重试均失败则退出程序
     *
     * @param pathname
     * @return
     */
    public static String createDir(String pathname) {
        String result = null;
        File defaultDir = new File(pathname);
        if (defaultDir.exists()) {
            result = pathname;
        } else {
            int tryTimes = 0;
            while (!defaultDir.mkdir() && tryTimes < 3) {
                defaultDir.mkdir();
                tryTimes++;
            }
            if (tryTimes == 3) {
                LOGGER.error("[static code] 3次尝试创建文件夹均失败，退出程序");
                System.exit(-1);
            } else {
                result = pathname;
            }
        }

        return result;
    }
}


