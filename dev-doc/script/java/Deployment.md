# 部署文档

## Jar包部署成后台运行的启动脚本
在jar包所在目录创建一个“start.sh”的脚本，该脚本内容如下：
```
# testApp start shell
nohup java -Djava.security.egd=file:/dev/./urandom -jar testApp.jar --spring.profiles.active=$1 &

if [ ! -d /data/logs/ ]; then
  mkdir -p /data/logs/testApp
else
  echo /data/logs/testApp exists
fi

nohup java -jar testApp.jar --spring.profiles.active=debug --server.port=9999 --netty.port=9990  >> /data/logs/testApp/testApp.log &
```
该脚本保存在本模块的路径下的runJarOnLinux.sh文件中。
