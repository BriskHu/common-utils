#!/usr/bin/env bash
# 查找日志文件是否存在某些关键字

# $1 搜索文件或搜索目录。目录只搜索指定的目录，不会向下递归。
# 
# $2 如果匹配，则打印匹配行前后多少行。
# $3 第1个搜索关键字
# $4 第2个搜索关键字。

target=$1
curDate=`date "+%Y-%m-%d_%H%M"`
printLines=$2
key1=$3
key2=$4


# 执行查找操作
function doFind() {
	echo -e "\tHandling file: $target."
	resultFile="findResult-${curDate}.txt"
	touch ./$resultFile
	
	if [ "${key2}" != "" ]; then 
		echo -e "\tFinding ${key1} & ${key2}......"
		tempFile="findResult-${curDate}-temp.txt"
		touch ./$tempFile
		
		cat $target | grep ${key1} -n -C 50 >> $tempFile
#		cat $target | grep ${key1} -C 50 >> $tempFile
		cat $tempFile | grep ${key2} -C ${printLines} >> $resultFile
		rm -f $tempFile
	else
		echo -e "\tFinding ${key1}......"
		cat $target | grep ${key1} -n -C ${printLines} >> $resultFile
#		cat $target | grep ${key1} -C ${printLines} >> $resultFile
	fi
		
	sed -i "/${key1}/i\ \n \nFound here..." $resultFile 
	# str=${key1}
	# sed -i "/${str}/i\ \n \n/=\tFound..." $resultFile 
}


# 程序入口
echo -e "Start finding......\n"

if [ "${key2}" != "" ]; then
	if [ -d $target ] ; then
		for file in `ls -rt $target` ; do
			doFind $file ${key1} ${key2}
		done
	elif [ -f $target ] ; then
		doFind $target ${key1} ${key2}
	else
		echo -e "\t${1} is not a valid file or directory.\n"
	fi
else
	if [ -d $target ] ; then
		for file in `ls -rt $target` ; do
			doFind $file ${key1} 
		done
	elif [ -f $target ] ; then
		doFind $target ${key1}
	else
		echo -e "\t${1} is not a valid file or directory.\n"
	fi
fi

echo -e "End.\n"


# 测试指令
# cat debugging-platform-20190730.log | grep "型号/产品上架->服务层入参" | grep "2317" -C 5 > findResult.txt
# cat findResult-.txt |grep "型号/产品上架->服务层入参" -C 20 | grep "2017" -C 10




