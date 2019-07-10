package com.briskhu.util.date.day;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
 * 基于用户的协同过滤推荐算法
 * 度量用户间相似性的方法选用：带修正的余弦相似性
 * 输入：UserID  ，     ItemID
 * 输出1：预测评分值
 * 输出2：RMSE（推荐质量）
 * */
public class UserBaseCF1 {

//    public static final int USERSIZE = 2513;
    public static final int USERSIZE = 3000;
//    public static final int ITEMSIZE = 6592;
    public static final int ITEMSIZE = 200000;
    public static final int UN = 5;//某一user的最近邻居数
    //public static final int IN=10;//某一item的最近邻居数

    public int[] num = new int[USERSIZE + 1];//每个用户为几部评了分
    public int[] num1 = new int[ITEMSIZE + 1];//每个item有多少用户评分
    public double[] average = new double[USERSIZE + 1];//每个user的平均打分
    public double[][] rate = new double[USERSIZE + 1][ITEMSIZE + 1];//评分矩阵
    public double[][] timeWeight = new double[USERSIZE + 1][ITEMSIZE + 1];//某一user对于某一item评分的时间加权
    public double[][] DealedOfRate = new double[USERSIZE + 1][ITEMSIZE + 1];//针对稀疏问题处理后的评分矩阵
    public double[][] similarity = new double[USERSIZE + 1][USERSIZE + 1];//用户相识度矩阵
    public double[] H = new double[USERSIZE + 1];//用户价值度
    Neighbor[][] NofUser = new Neighbor[USERSIZE + 1][UN + 1];//每个用户的最近的UN个邻居
    int maxUserItem = 0;//找出对商品评价个数最多的用户
    List<Double> x = new LinkedList<Double>();//LinkedList按照对象加入的顺序存储
    List<Double> y = new LinkedList<Double>();
    public double[] averageItem = new double[ITEMSIZE + 1];//每个item的平均分值
    public double[] A = new double[USERSIZE + 1];//用户评价权威度
    public double[] Trust = new double[USERSIZE + 1];//用户综合信任值
    public double[][] MTrust = new double[USERSIZE + 1][USERSIZE + 1];//用户直接信任度矩阵
    public double[][] ITrust = new double[USERSIZE + 1][USERSIZE + 1];//用户间接信任度矩阵
    public double[][] ST = new double[USERSIZE + 1][USERSIZE + 1];//合并用户相似矩阵和信任矩阵，得到信任相似矩阵

    public static void main(String args[]) throws Exception {

        UserBaseCF1 cf = new UserBaseCF1();
//        if (cf.readFile("D:\\TTCode\\TempFiles\\ratings.dat")) {
        if (cf.readFile("D:\\TTCode\\TempFiles\\trainingData.txt")) {
            System.out.println("请等待，正在分析");
            cf.getAvr();//得到average[]
            cf.dealRate();//得到DealedOfRate
//            cf.dealTimeWeight();//得到时间加权

            cf.getSimilarity();//得到用户相似性
            cf.getNeighbor();//得到邻居
			/* test
			System.out.println(cf.rate[1][11]);
			System.out.println(cf.DealedOfRate[1][11]);
			System.out.println(cf.num[1]);
			System.out.println(cf.average[1]);
			System.out.println(cf.rate[1][10]);
			System.out.println(cf.DealedOfRate[1][10]);
		*/
            //System.out.println("................."+cf.NofUser.length);
            //System.out.println("-----------"+cf.NofUser[2].length);
            for (int i = 1; i <= UN; i++) {
//                System.out.println(cf.NofUser[2][i].getID() + ":" + cf.NofUser[2][i].getValue());
            }


            //测试
            //读文件
            File inputFile = new File("D:\\TTCode\\TempFiles\\testData.txt");
            BufferedReader reader = null;
            if (!inputFile.exists() || inputFile.isDirectory()) {
                throw new FileNotFoundException();
            }
            reader = new BufferedReader(new FileReader(inputFile));

            //写文件
            File outputFile = new File("D:\\TTCode\\TempFiles\\testResult.txt");
            FileWriter writer = null;
            if (!outputFile.exists()) {
                if (!outputFile.createNewFile()) {
                    System.out.println("输出文件创建失败");
                }
            }
            writer = new FileWriter(outputFile);
            String title = "UserID" + "\t" + "ItemID" + "\t" + "OriginalRate" + "\t" + "PredictRate" + "\r\n";
            writer.write(title);
            writer.flush();
            String[] part = new String[4];
            String tmpToRead = "";
            String tmpToWrite = "";
            while ((tmpToRead = reader.readLine()) != null) {
                part = tmpToRead.split("::");
                int userID = Integer.parseInt(part[0]);
//                if (userID > USERSIZE)
//                {
//                    continue;
//                }
                int itemID = Integer.parseInt(part[1]);
//                if (itemID>ITEMSIZE){
//                    continue;
//                }
                double originalRate = Double.parseDouble(part[2]);
                double predictRate = cf.predict(userID, itemID);
                cf.x.add(originalRate);
                cf.y.add(predictRate);
                tmpToWrite = userID + "\t" + itemID + "\t" + originalRate + "\t" + predictRate + "\r\n";
                writer.write(tmpToWrite);
                writer.flush();
            }
            System.out.println("分析完成，请打开工程目录下bin文件夹中的testResult.txt");
            cf.analyse(cf.x, cf.y);
            //System.out.println("利用RMSE分析结果为"+);

        } else {
            System.out.println("失败");
        }

    }

    //Chapter1:准备工作
    //1-1:读取文件内容，得到评分矩阵     1:读取成功       -1：读取失败
    public boolean readFile(String filePath) throws ParseException {
        File inputFile = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在" + e.getMessage());
            return false;
        }

        String sentence = "";
        String[] part = new String[4];
        HashMap map = new HashMap();//建立一个map用来存储用户对歌手打标签的时间，以用户歌手为key，标签时间为value
        long startDate = Long.MAX_VALUE;
        long endDate = Long.MIN_VALUE;
        try {
            while ((sentence = reader.readLine()) != null) {
                part = sentence.split("::");
                int userID = Integer.parseInt(part[0]);
                if (userID > USERSIZE) {
                    continue;
                }
                int itemID = Integer.parseInt(part[1]);
                if (itemID > ITEMSIZE) {
                    continue;
                }
                double Rate = Double.parseDouble(part[2]);
                long time = Long.valueOf(part[3]);
                long tempDate = time;
                System.out.println("userID" + userID + "itemID" + itemID + "Rate" + Rate + "tempDate" + tempDate);
                if (tempDate < startDate) {
                    startDate = tempDate;
                }
                if (endDate < tempDate) {
                    endDate = tempDate;
                }
                String tempKey = String.valueOf(userID) + "-" + String.valueOf(itemID);
                if (!map.containsKey(tempKey)) {
                    map.put(tempKey, tempDate);
                }
                H[userID] += 1.0;
                if (H[userID] > (double) maxUserItem) {
                    maxUserItem = (int) H[userID];
                }
                //System.out.println(userID+" "+itemID+" "+Rate);
                //构造矩阵
                rate[userID][itemID] = Rate;


            }
            for (int m = 1; m <= USERSIZE; m++) {
                for (int n = 1; n <= ITEMSIZE; n++) {
                    String findKey = String.valueOf(m) + "-" + String.valueOf(n);
                    if (!map.containsKey(findKey)) {
                        timeWeight[m][n] = 0.5;
                    } else {
                        timeWeight[m][n] = computeTimeWeight(startDate, (Long) map.get(findKey), endDate);
                    }
                }
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("读文件发生错误" + e.getMessage());
            return false;
        }
        for (int i = 1; i <= USERSIZE; i++) {
            H[i] = H[i] / maxUserItem;//生成用户价值度
        }
        return true;
    }

    //1-2计算每个用户的平均分
    public void getLen() {//计算每个用户为几部电影打分
        for (int i = 1; i <= USERSIZE; i++) {
            int n = 0;
            for (int j = 1; j <= ITEMSIZE; j++) {
                if (rate[i][j] != 0)
                    n++;
            }
            //System.out.println("n" +"is"+ n);
            num[i] = n;
        }

    }

    public void getLen1() {//计算每个item有多少人评分
        for (int i = 1; i <= ITEMSIZE; i++) {
            int n = 0;
            for (int j = 1; j <= USERSIZE; j++) {
                if (rate[j][i] != 0)
                    n++;
            }
            //System.out.println("n" +"is"+ n);
            num1[i] = n;
        }

    }

    //生成用户评价权威度
    public void getA() {
        for (int i = 1; i <= USERSIZE; i++) {
            int Su = 0;// 用户u评价的项目中，评分偏差小于一定阈值的项目个数总和
            for (int j = 1; j <= ITEMSIZE; j++) {
                if (rate[i][j] != 0 && Math.abs((rate[i][j] - averageItem[j]) / averageItem[j]) <= 1) {
                    Su++;
                }
            }
            if (num[i] != 0) {
                A[i] = Su / num[i];
                double a = H[i] / (H[i] + A[i]);//得到协调因子
                Trust[i] = a * H[i] + (1 - a) * A[i];
                //System.out.println("Trust[i]"+Trust[i]);
            }
        }
    }

    public void getAvr() {
        getLen();
        getLen1();
        int i, j;
        for (i = 1; i <= USERSIZE; i++) {
            double sum = 0.0;
            for (j = 1; j < rate[i].length; j++) {//每个length都是ITEMSIZE=1682
                sum += rate[i][j];
            }
            if (num[i] != 0) {
                average[i] = sum / num[i];
            }
        }
        for (i = 1; i <= ITEMSIZE; i++) {
            double sum = 0.0;
            for (j = 1; j <= USERSIZE; j++) {
                sum += rate[j][i];
            }
            if (num1[i] != 0) {
                averageItem[i] = sum / num1[i];
            }
        }
    }


    public void dealRate() {
        int i, j;
        for (i = 1; i <= USERSIZE; i++) {
            for (j = 1; j <= ITEMSIZE; j++) {
				/*if(rate[i][j]==0){
					DealedOfRate[i][j]=average[i];
					if(Double.isNaN(average[i])) System.out.println("average[i]has a problem.........");
				}
				else{*/
                DealedOfRate[i][j] = rate[i][j];
                //if(Double.isNaN(rate[i][j])) System.out.println("rate[i][j]has a problem.........");
                /*}*/
            }
        }
    }

    //Chapter2：聚类，找和某一用户有相同喜好的一类用户
    //2-1：:Pearson计算向量的相似度
    public double Sum(double[] arr) {
        double total = (double) 0.0;
        for (double ele : arr)
            if (Double.isNaN(ele)) {
                //System.out.println("it is here......................................");
            } else {
                total += ele;
            }

        return total;
    }

    public double Mutipl(double[] arr1, double[] arr2, int len) {
        double total = (double) 0.0;
        for (int i = 0; i < len; i++)
            total += arr1[i] * arr2[i];
        return total;
    }

    public double Pearson(int userId, double[] x, int id, double[] y) {
        int lenx = x.length;
        int leny = y.length;
        int len = lenx;//小容错
        if (lenx < leny) len = lenx;
        else len = leny;
        //对评分进行时间加权
        for (int i1 = 1; i1 <= ITEMSIZE; i1++) {
            x[i1] = x[i1] * timeWeight[userId][i1];
        }
        for (int i1 = 1; i1 <= ITEMSIZE; i1++) {
            y[i1] = y[i1] * timeWeight[id][i1];
        }
		/*double sumX=Sum(x);
		double sumY=Sum(y);
		double sumXX=Mutipl(x,x,len);
		double sumYY=Mutipl(y,y,len);
		double sumXY=Mutipl(x,y,len);
		double upside=sumXY-sumX*sumY/len;
		//double downside=(double) Math.sqrt((sumXX-(Math.pow(sumX, 2))/len)*(sumYY-(Math.pow(sumY, 2))/len));
		double downside=(double) Math.sqrt(Math.abs((sumXX-Math.pow(sumX, 2)/len)*(sumYY-Math.pow(sumY, 2)/len)));
		if(downside==0) return -1.0;
		System.out.println(len+" "+sumX+" "+sumY+" "+sumXX+" "+sumYY+" "+sumXY+" "+downside+"000000000");
		return upside/downside;*/
        double upside = 0;
        double downLeft = 0;
        double downRight = 0;
        for (int i = 0; i < len; i++) {
            if (x[i] != 0 && y[i] != 0) {
                upside += (x[i] - average[userId]) * (y[i] - average[id]);
                downLeft += Math.pow((x[i] - average[userId]), 2);
                downRight += Math.pow((y[i] - average[id]), 2);
            }
        }
        if (downLeft == 0 || downRight == 0) return 0.0;
        double result = (upside / ((Math.pow(downLeft, 2)) * (Math.pow(downRight, 2)))) * Math.pow(Math.E, 5) / 2 * 1000 * 4;
        return result;
    }

    //2-2将Pearson算法用在求user的近邻上，求NofUser数组
    public void getSimilarity() {
        int id, userID;
        for (userID = 1; userID <= USERSIZE; userID++) {
            Set<Neighbor> neighborList = new TreeSet();//会将压入的Neighbor排好序存放
            Neighbor[] tmpNeighbor = new Neighbor[USERSIZE + 1];
            for (id = 1; id <= USERSIZE; id++) {
                if (id != userID) {
                    double sim = Pearson(userID, DealedOfRate[userID], id, DealedOfRate[id]);
                    similarity[userID][id] = sim;
                    if (sim != 0) {
//                        System.out.println("sim" + sim);
                    }

                    tmpNeighbor[id] = new Neighbor(id, sim);
//                    System.out.println("id" + id + "sim" + sim);
                    neighborList.add(tmpNeighbor[id]);
                } else {
                    similarity[userID][id] = 1;
                }
            }

            int k = 1;
            Iterator it = neighborList.iterator();
            if (neighborList.size() <= 10) {
//                System.out.println(neighborList.size() + "............");
            }
            while (k <= UN && it.hasNext()) {
                Neighbor tmp = (Neighbor) it.next();
                NofUser[userID][k] = tmp;
                k++;
            }
        }
    }

    //Chapter3:根据最近邻居给出预测评分
    public double predict(int userID, int itemID) {
        double sum1 = 0;
        double sum2 = 0;
        for (int i = 1; i <= UN; i++) {//对最近的UN个邻居进行处理
            if (NofUser[userID][i] == null || NofUser[userID][i].getID() == 0) {
                continue;
            }
            int neighborID = NofUser[userID][i].getID();
            double neib_sim = NofUser[userID][i].getValue();
            if (neib_sim == 0) {
                continue;
            }
            sum1 += neib_sim * (DealedOfRate[neighborID][itemID] - average[neighborID]);
            sum2 += Math.abs(neib_sim);
        }
        if (sum2 == 0) {
            return average[userID];
        }
        return average[userID] + sum1 / sum2;
    }

    //Chapter4:测试
    //以u1.test的userID，itemID为输入，用以上运算再给出一组打分，与u1.test中进行比较
    //部分测试已在main函数中做好，这里实现均方差公式RMSE
    //它是观测值与真值偏差的平方和 与 观测次数n比值的平方根
    public double RMSE(double[] x, double[] y) {
        double rmse = 0;
        int lenx = x.length;
        int leny = y.length;
        int len = lenx;//小容错
        if (lenx < leny) len = lenx;
        else len = leny;

        double diffSum = 0;
        double diffMutipl;
        for (int i = 0; i < len; i++) {
            diffMutipl = Math.pow((x[i] - y[i]), 2);
            diffSum += diffMutipl;
        }
        rmse = Math.sqrt(diffSum / len);
        //System.out.println(len);
        //System.out.println(diff);
        return rmse;
    }

    public double MAE(double[] x, double[] y) {
        double mae = 0;
        int lenx = x.length;
        int leny = y.length;
        int len = lenx;//小容错
        if (lenx < leny) len = lenx;
        else len = leny;
        double diffSum = 0;
        for (int i = 0; i < len; i++) {
            diffSum += Math.abs((x[i] - y[i]));
        }
        mae = diffSum / len;
        return mae;
    }

    public void analyse(List<Double> x, List<Double> y) {
        int lenx = x.size();
        int leny = y.size();
        int len = lenx;//小容错
        if (lenx < leny) len = lenx;
        else len = leny;
        //System.out.println(len);
        double[] tmpX = new double[len];
        double[] tmpY = new double[len];
        for (int i = 0; i < len; i++) {
            tmpX[i] = x.get(i);
            tmpY[i] = y.get(i);
        }
        System.out.println("RMSE为：" + RMSE(tmpX, tmpY));
        System.out.println("MAE为：" + MAE(tmpX, tmpY));
        //System.out.println(tmpY[1]);
    }
	
	
	/*//此方法用于生成时间加权矩阵，若没有某用户贴标签的时间则加权为0.5
	public void dealTimeWeight(){
		HashMap map = new HashMap();//建立一个map用来存储用户对歌手打标签的时间，以用户歌手为key，标签时间为value
		String filePath = "bin/user_taggedartists.dat";
		File inputFile=new File(filePath);
		BufferedReader reader=null;
        try {
			reader=new BufferedReader(new FileReader(inputFile));
		} catch (FileNotFoundException e) {
			System.out.println("文件不存在"+e.getMessage());
		}
		
        String sentence="";
        String[] part=new String[6];
        int i=0;
        Date startDate = new Date(2008,1,1);
        Date endDate = new Date(2008,1,1);
        try {
			while((sentence=reader.readLine())!=null){
				if(i==0){
					i++;
					continue;
				}
				part=sentence.split("::");
				int userID=Integer.parseInt(part[0]);
				int itemID=Integer.parseInt(part[1]);
				int tagID=Integer.parseInt(part[2]);
				int day=Integer.parseInt(part[3]);
				int month=Integer.parseInt(part[4]);
				int year=Integer.parseInt(part[5]);
				//System.out.println(userID+" "+itemID+" ");
				Date tempDate = new Date(year, month, day);
				if(tempDate.before(startDate)) startDate = tempDate;
				if(endDate.before(tempDate)) endDate = tempDate;
				String tempKey = String.valueOf(userID)+"-"+String.valueOf(itemID);
				if(!map.containsKey(tempKey)){
					map.put(tempKey, tempDate);
				}else{
					if(((Date) map.get(tempKey)).before(tempDate)){
						map.put(tempKey, tempDate);
					}
				}
			}
		} catch (NumberFormatException|IOException e) {
			System.out.println("读文件发生错误"+e.getMessage());
		}
        for(int m=1;m<=USERSIZE;m++){
        	for(int n=1;n<=ITEMSIZE;n++){
        		String findKey = String.valueOf(m)+"-"+String.valueOf(n);
        		if(!map.containsKey(findKey)){
        			timeWeight[m][n] = 0.5;
        		}else{
        			timeWeight[m][n] = computeTimeWeight(startDate,map.get(findKey),endDate);
        		}
        	}
        }
	}*/

    ///此方法用于计算时间权重
    public double computeTimeWeight(long startDate, long t, long endDate) {
        double tre = (t - startDate) / (endDate - startDate);
        double t1 = tre * 2 - 1;
        double e = Math.E;
        double result = 1 / (1 + Math.pow(e, -t1));
        return result;
    }

    //此方法用于计算直接信任度
    public void getMTrust() {
        for (int i = 1; i <= USERSIZE; i++) {
            for (int j = 1; j <= USERSIZE; j++) {

                if (i == j) {
                    MTrust[i][j] = 1;
                } else {
                    double countI = 0.0;//用于计算两个用户共同评分的项目数
                    double countD = 0.0;//用于计算有效建议的个数
                    for (int k = 1; k <= ITEMSIZE; k++) {
                        if (rate[i][k] != 0 && rate[j][k] != 0) {
                            countI = countI + 1.0;
                            //System.out.println("countI"+countI);
                            double TPui = average[i] + Trust[j] * Math.abs(rate[j][k] - average[j]);
                            //System.out.println("TPui"+TPui+"Trust[j]"+Trust[j]+"rate[j][k]"+rate[j][k]+"average[j]"+average[j]);
                            //System.out.println("Math.abs(rate[i][k]-TPui)/rate[i][k]:"+Math.abs(rate[i][k]-TPui)/rate[i][k]);
                            if (Math.abs(rate[i][k] - TPui) / rate[i][k] < 1) {
                                countD = countD + 1.0;
                                //System.out.println("countD:"+countD);
                            }
                        }
                    }
                    if (countI != 0) {
                        MTrust[i][j] = countD / countI;
                        //System.out.println("MTrust[i][j]"+MTrust[i][j]);
                    }
                }

            }
        }
    }

    //此方法用于计算间接信任度
    public void getITrust() {
        for (int i = 1; i <= USERSIZE; i++) {
            for (int j = 1; j <= USERSIZE; j++) {
                double upside = 0;
                double downside = 0;
                for (int k = 1; k <= USERSIZE; k++) {
                    if (i != k && j != k && i != j && MTrust[i][k] != 0 && MTrust[k][i] != 0) {
                        if (MTrust[i][k] >= MTrust[k][i]) {
                            upside += (MTrust[i][k] * MTrust[k][i]);
                        } else {
                            upside += (MTrust[i][k] * MTrust[i][k]);
                        }
                        downside += MTrust[i][k];
                    }
                }
                if (downside != 0) {
                    ITrust[i][j] = upside / downside;
                }
            }
        }
    }

    //此方法用于生成相似信任矩阵
    public void getST() {
        for (int i = 1; i <= USERSIZE; i++) {
            for (int j = 1; j <= USERSIZE; j++) {
                double a = MTrust[i][j] * MTrust[i][j] / (MTrust[i][j] + ITrust[i][j] * ITrust[i][j]);//得到协调因子
                double ZTrust = 0.0;
                if (!Double.isNaN(a)) {
                    ZTrust = a * MTrust[i][j] + (1 - a) * ITrust[i][j];//得到最终信任度
                }

                if (similarity[i][j] > 0 && ZTrust > 0) {
                    ST[i][j] = 2 * similarity[i][j] * ZTrust / (similarity[i][j] + ZTrust);
                } else if (ZTrust > 0 && similarity[i][j] == 0) {
                    ST[i][j] = ZTrust;
                }
//                System.out.println("MTrust[i][j]:" + MTrust[i][j] + "ITrust[i][j]:" + ITrust[i][j] + "ZTrust:" + ZTrust + "similarity[i][j]" + similarity[i][j] + "ST[i][j]" + ST[i][j]);
            }
        }
    }

    //此方法用于找到邻居
    public void getNeighbor() {
        int id, userID;
        for (userID = 1; userID <= USERSIZE; userID++) {
            Set<Neighbor> neighborList = new TreeSet();//会将压入的Neighbor排好序存放
            Neighbor[] tmpNeighbor = new Neighbor[USERSIZE + 1];
            for (id = 1; id <= USERSIZE; id++) {
                if (id != userID) {

                    tmpNeighbor[id] = new Neighbor(id, ST[userID][id]);
                    //System.out.println("id："+id+"相似信任度："+ST[userID][id]);
                    neighborList.add(tmpNeighbor[id]);
                }
            }

            int k = 1;
            Iterator it = neighborList.iterator();
            if (neighborList.size() <= 10) {
                //System.out.println(neighborList.size()+"............");
            }
            while (k <= UN && it.hasNext()) {
                Neighbor tmp = (Neighbor) it.next();
                NofUser[userID][k] = tmp;
                k++;
            }
        }
    }

    //上面处理稀疏矩阵的方法失效，本方法为根据信任相似度，补全未评分的值，然后同样方法再迭代计算预测值
    public void dealrate() {
        for (int i = 1; i <= USERSIZE; i++) {
            for (int j = 1; j <= ITEMSIZE; j++) {
                if (DealedOfRate[i][j] == 0) {
                    DealedOfRate[i][j] = predict(i, j);
                }
            }
        }
    }
}