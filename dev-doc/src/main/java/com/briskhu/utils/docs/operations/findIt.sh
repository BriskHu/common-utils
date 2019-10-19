#!/usr/bin/env bash
# 查找日志文件是否存在某些关键字
# $1 搜索文件或搜索目录。目录只搜索指定的目录，不会向下递归。 
# $2 如果匹配，则打印匹配行前后多少行。
# $3 第1个搜索关键字
# $4 第2个搜索关键字。

target=$1
curDate=`date "+%Y-%m-%d_%H%M"`
printLines=$2
key1=$3
key2=$4
resultFile=""


# 执行查找操作
function doFind() {
	targetFile=$1
	echo -e "\tHandling file: $targetFile."
	resultFile="./searchResult-${curDate}.txt"
	touch $resultFile
	echo -e "\n\tHandling file: $targetFile." >> $resultFile
	
	if [ "${key2}" != "" ]; then 
		echo -e "\tFinding \"${key1}\" & \"${key2}\" ..."
		tempFile="./finding-temp.txt"
		touch $tempFile
		
		cat $targetFile | grep ${key1} -n -C $(( ${printLines} * 2)) >> $tempFile
#		cat $targetFile | grep ${key1} -C $(( ${printLines} * 2)) >> $tempFile
		cat $tempFile | grep ${key2} -C ${printLines} >> $resultFile
		rm -f $tempFile
	else
		echo -e "\tFinding \"${key1}\" ..."
		cat $targetFile | grep ${key1} -n -C ${printLines} >> $resultFile
#		cat $targetFile | grep ${key1} -C ${printLines} >> $resultFile
	fi
}


# Attach tips
function addTips() {
	resultFile=$1
	
	sed -i "/${key1}/i\ \n \nFound here ..." $resultFile 

	sed -i "1iWe have found these: \n" $resultFile
	if ! test -z ${key2} ; then
		sed -i "3i\ \tFinding \"${key1}\" & \"${key2}\" ...\n" $resultFile
	else
		sed -i "3i\ \tFinding \"${key1}\" ...\n" $resultFile
	fi
}


# print usage
function printUsage() {
	echo -e "\nUsage: sh findIt.sh targetDirectory_or_targetFileName printLines searchKey1 [searchKey2]"
	
	echo "Input: "
	echo -e "\ttargetDirectory: the directory contains files which you want to search. You can input the \".\" to search the current directory."
	echo -e "\ttargetFileName: the file which you want to search."
	echo -e "\tprintLines: the number which you want to print if we find the keyword(s)."
	echo -e "\tsearchKey1: the first keyword which you want to search."
	echo -e "\tsearchKey2: the second keyword which you want to search. This can be null. If the searchKey2 is not null, the script will do search logical and between searchKey1 and searchKey2.\n"

	echo "Output: "
	echo -e "\tA file which was named as \"searchResult-${yyyy-MM-dd_HHmm}.txt\"\n"
	
	echo -e "Example: sh findIt.sh . 10 \"echo\" \"2017\" \n"
	echo -e "Author: Brisk Hu.\tVersion: 0.0.1\n"
}


# 程序入口
case "$target" in
	"-h" | "--help")
		printUsage
		exit 0
		;;
	"")
		printUsage
		exit 0
		;;
	*)
		if [[ ! $printLines =~ ^[0-9]*$ ]] || test -z $printLines || [ $printLines -eq 0 ] ; then
			echo -e "The printLines is null or not a number, please input a number."
			printUsage
			exit -1
		fi
		if test -z $key1 ; then
			echo -e "The search keyword is null, so we'll do nothing.\n"
			exit 0
		fi
		# do nothing, and goto the next command.
		;;
esac


echo "Start finding ......"

if [ "${key2}" != "" ]; then
	if [ -d $target ] ; then
		for file in `ls -rt $target` ; do
			doFind $file ${key1} ${key2}
		done
		addTips $resultFile
	elif [ -f $target ] ; then
		doFind $target ${key1} ${key2}
		addTips $resultFile
	else
		echo -e "\t${1} is not a valid file or directory.\n"
	fi
else
	if [ -d $target ] ; then
		for file in `ls -rt $target` ; do
			doFind $file ${key1}
		done
		addTips $resultFile
	elif [ -f $target ] ; then
		doFind $target ${key1}
		addTips $resultFile
	else
		echo -e "\t${1} is not a valid file or directory.\n"
	fi
fi

echo -e "End.\n"


# 测试指令
# sh findIt.sh
# sh findIt.sh .
# sh findIt.sh . 10a 
# sh findIt.sh . 10 echo


