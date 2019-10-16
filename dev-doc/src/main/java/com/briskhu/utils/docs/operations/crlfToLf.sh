#!/usr/bin/env bash

# 将Windows下以"\r\n"作为换行符的文件转换为Unix系统下的以"\n"作为换行符的文件。


# 转换文件
function convert_file() {
	targetFile=$1
	curDir=`pwd`
	echo -e "\tcurrent directory: ${curDir}"
	
	if [ -f $targetFile ]; then
		cat $targetFile | tr -t "\r\n" "\n" > ./temp
		mv -f ./temp $targetFile
		echo -e "\t${targetFile} has been successly converted.\n"
	else
		echo -e "\t${targetFile} is not a valid file.\n"
	fi
}

# 处理目录
function handle_directory() {
	input=$1
	
	if test -d $input ; then
		cd $input
		curDir=`pwd`
		echo -e "\tcurrent directory: ${curDir}"
		
		for sub in `ls -rt ${curDir}`; do
			handle_directory $sub
		done
		cd ..
	elif [ -f $input ]; then
		convert_file $input
	else
		echo -e "\t${input} is not a valid directory.Now go to the parent directory.\n"	
	fi
}



echo "Start... "

if [ $# -eq 0 ]; then
    echo ""
    echo "Invalid usage... "
    echo "Usage: crlfToLf.sh <targetFileName1> <directory> <...>"
    echo "You should assign a target file name which like this: \"crlfToLf.sh test.txt \"."
    echo ""
	exit -1
fi

echo "You have assigned $# parameters."
if (( $# > 10 )) ; then
	echo -e "You have input more than 10 parameters.\nFor God's sake, please input less than 10 files or directories.\n"
	exit -1
fi

args=$@
for arg in $args ; do
	echo -e "\tAddressing ${arg}..."
	if [ -d $arg ]; then
		handle_directory $arg
	elif [ -f $arg ]; then
		convert_file $arg
	else
		echo -e "\t${arg} is not a valid file or directory.\n"
	fi
done

echo -e "End.\n"






# 配置文件修改后的验证脚本
# cd /data/services/iflytek-jobclient/config
# vim application-dev.yml
# vim application-local.yml
# vim application-pro.yml
# vim application-test.yml
# vim application.yml
# vim logback-boot.xml
# vim messages.properties


