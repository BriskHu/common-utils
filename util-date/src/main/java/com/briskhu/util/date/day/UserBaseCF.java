//package com.briskhu.util.date.day;
//
//import java.io.*;
//import java.text.ParseException;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * <p/>
// * <p>
// * created on 2019-05-28
// **/
//public class UserBaseCF {
//
//    /* ---------------------------------------- fileds ---------------------------------------- */
////    public static final int USERSIZE = 2513;
//    public static final int USERSIZE = 2500;
//    //    public static final int ITEMSIZE = 6592;
//    public static final int ITEMSIZE = 19000;
//    public static final int UN = 5;//某一user的最近邻居数
//    //public static final int IN=10;//某一item的最近邻居数
//
//    public int[] num = new int[USERSIZE + 1];//每个用户为几部评了分
//    public int[] num1 = new int[ITEMSIZE + 1];//每个item有多少用户评分
//    public double[] average = new double[USERSIZE + 1];//每个user的平均打分
//    public double[][] rate = new double[USERSIZE + 1][ITEMSIZE + 1];//评分矩阵
//    public double[][] timeWeight = new double[USERSIZE + 1][ITEMSIZE + 1];//某一user对于某一item评分的时间加权
//    public double[][] DealedOfRate = new double[USERSIZE + 1][ITEMSIZE + 1];//针对稀疏问题处理后的评分矩阵
//    public double[][] similarity = new double[USERSIZE + 1][USERSIZE + 1];//用户相识度矩阵
//    public double[] H = new double[USERSIZE + 1];//用户价值度
//    Neighbor[][] NofUser = new Neighbor[USERSIZE + 1][UN + 1];//每个用户的最近的UN个邻居
//    int maxUserItem = 0;//找出对商品评价个数最多的用户
//    List<Double> x = new LinkedList<Double>();//LinkedList按照对象加入的顺序存储
//    List<Double> y = new LinkedList<Double>();
//    public double[] averageItem = new double[ITEMSIZE + 1];//每个item的平均分值
//    public double[] A = new double[USERSIZE + 1];//用户评价权威度
//    public double[] Trust = new double[USERSIZE + 1];//用户综合信任值
//    public double[][] MTrust = new double[USERSIZE + 1][USERSIZE + 1];//用户直接信任度矩阵
//    public double[][] ITrust = new double[USERSIZE + 1][USERSIZE + 1];//用户间接信任度矩阵
//    public double[][] ST = new double[USERSIZE + 1][USERSIZE + 1];//合并用户相似矩阵和信任矩阵，得到信任相似矩阵
//
//
//    /* ---------------------------------------- methods ---------------------------------------- */
//    public static void main(String[] args) throws Exception {
//        UserBaseCF cf = new UserBaseCF();
//        if (cf.readFile(args[0])) {
//            System.out.println("请等待，正在分析");
//            cf.getAvr();//得到average[]
//            cf.dealRate();//得到DealedOfRate
//            // cf.dealTimeWeight();//得到时间加权
//
//            cf.getSimilarity();//得到用户相似性
//            cf.getNeighbor();//得到邻居
//            for (int i = 1; i <= UN; i++) {
//                System.out.println(cf.NofUser[2][i].getID() + ":" + cf.NofUser[2][i].getValue());
//            }
//
//            cf.test(args);
//        } else {
//            System.out.println("失败");
//        }
//
//    }
//
//    //Chapter1:准备工作
//    //1-1:读取文件内容，得到评分矩阵     1:读取成功       -1：读取失败
//    public boolean readFile(String filePath) throws ParseException {
//        File inputFile = new File(filePath);
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new FileReader(inputFile));
//        } catch (FileNotFoundException e) {
//            System.out.println("文件不存在" + e.getMessage());
//            return false;
//        }
//
//        String sentence = "";
//        String[] part = new String[4];
//        HashMap map = new HashMap();//建立一个map用来存储用户对歌手打标签的时间，以用户歌手为key，标签时间为value
//        long startDate = Long.MAX_VALUE;
//        long endDate = Long.MIN_VALUE;
//        try {
//            while ((sentence = reader.readLine()) != null) {
//                part = sentence.split("::");
//                int userID = Integer.parseInt(part[0]);
//                if (userID > USERSIZE) {
//                    continue;
//                }
//                int itemID = Integer.parseInt(part[1]);
//                if (itemID > ITEMSIZE) {
//                    continue;
//                }
//                double Rate = Double.parseDouble(part[2]);
//                long time = Long.valueOf(part[3]);
//                long tempDate = time;
//                System.out.println("userID" + userID + "itemID" + itemID + "Rate" + Rate + "tempDate" + tempDate);
//                if (tempDate < startDate) {
//                    startDate = tempDate;
//                }
//                if (endDate < tempDate) {
//                    endDate = tempDate;
//                }
//                String tempKey = String.valueOf(userID) + "-" + String.valueOf(itemID);
//                if (!map.containsKey(tempKey)) {
//                    map.put(tempKey, tempDate);
//                }
//                H[userID] += 1.0;
//                if (H[userID] > (double) maxUserItem) {
//                    maxUserItem = (int) H[userID];
//                }
//                //System.out.println(userID+" "+itemID+" "+Rate);
//                //构造矩阵
//                rate[userID][itemID] = Rate;
//
//
//            }
//            for (int m = 1; m <= USERSIZE; m++) {
//                for (int n = 1; n <= ITEMSIZE; n++) {
//                    String findKey = String.valueOf(m) + "-" + String.valueOf(n);
//                    if (!map.containsKey(findKey)) {
//                        timeWeight[m][n] = 0.5;
//                    } else {
//                        timeWeight[m][n] = computeTimeWeight(startDate, (Long) map.get(findKey), endDate);
//                    }
//                }
//            }
//        } catch (NumberFormatException | IOException e) {
//            System.out.println("读文件发生错误" + e.getMessage());
//            return false;
//        }
//        for (int i = 1; i <= USERSIZE; i++) {
//            H[i] = H[i] / maxUserItem;//生成用户价值度
//        }
//        return true;
//    }
//
//
//
//    public void getAvr() {
//        getLen();
//        getLen1();
//        int i, j;
//        for (i = 1; i <= USERSIZE; i++) {
//            double sum = 0.0;
//            for (j = 1; j < rate[i].length; j++) {//每个length都是ITEMSIZE=1682
//                sum += rate[i][j];
//            }
//            if (num[i] != 0) {
//                average[i] = sum / num[i];
//            }
//        }
//        for (i = 1; i <= ITEMSIZE; i++) {
//            double sum = 0.0;
//            for (j = 1; j <= USERSIZE; j++) {
//                sum += rate[j][i];
//            }
//            if (num1[i] != 0) {
//                averageItem[i] = sum / num1[i];
//            }
//        }
//    }
//
//
//
//    public void test(String[] args) throws Exception {
//
//        //测试
//        //读文件
//        File inputFile = new File(args[1]);
//        BufferedReader reader = null;
//        if (!inputFile.exists() || inputFile.isDirectory()) {
//            throw new FileNotFoundException();
//        }
//        reader = new BufferedReader(new FileReader(inputFile));
//
//        //写文件
//        File outputFile = new File(args[2]);
//        FileWriter writer = null;
//        if (!outputFile.exists()) {
//            if (!outputFile.createNewFile()) {
//                System.out.println("输出文件创建失败");
//            }
//        }
//        writer = new FileWriter(outputFile);
//        String title = "UserID" + "\t" + "ItemID" + "\t" + "OriginalRate" + "\t" + "PredictRate" + "\r\n";
//        writer.write(title);
//        writer.flush();
//        String[] part = new String[4];
//        String tmpToRead = "";
//        String tmpToWrite = "";
//        while ((tmpToRead = reader.readLine()) != null) {
//            part = tmpToRead.split("::");
//            int userID = Integer.parseInt(part[0]);
//            if (userID > USERSIZE) {
//                continue;
//            }
//            int itemID = Integer.parseInt(part[1]);
//            if (itemID > ITEMSIZE) {
//                continue;
//            }
//            double originalRate = Double.parseDouble(part[2]);
//            double predictRate = cf.predict(userID, itemID);
//            cf.x.add(originalRate);
//            cf.y.add(predictRate);
//            tmpToWrite = userID + "\t" + itemID + "\t" + originalRate + "\t" + predictRate + "\r\n";
//            writer.write(tmpToWrite);
//            writer.flush();
//        }
//        System.out.println("分析完成，请打开工程目录下bin文件夹中的testResult.txt");
//        cf.analyse(cf.x, cf.y);
//        //System.out.println("利用RMSE分析结果为"+);
//    }
//
//
//
//}
//
//
