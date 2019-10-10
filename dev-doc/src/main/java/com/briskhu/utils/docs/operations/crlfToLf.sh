#!/usr/bin/env bash

#将Windows下以"\r\n"作为换行符的文件转换为Unix系统下的以"\n"作为换行符的文件。

echo "Start... "

if [ $# -eq 0 ]; then
    echo ""
    echo "Invalid usage... "
    echo "Usage: crlfToLf.sh <targetFileName1> <file2> <...>"
    echo "You should assign a target file name which like this: \"crlfToLf.sh test.txt \"."
    echo ""
	exit -1
fi

echo "You have assigned $# files."
if [ "$# < 10" ]; then
	args=$@
	for targetFile in $args ; do
		echo -e "\t Addressing ${targetFile}..."
		if [ -f $targetFile ]; then
			cat $targetFile | tr -t "\r\n" "\n" > temp
			mv -f temp $targetFile
			echo -e "\t ${targetFile} has been successly converted."
			echo ""
		else
			echo -e "\t ${targetFile} is not a valid file"
			echo ""
		fi
	done
fi

echo "End."
echo ""


