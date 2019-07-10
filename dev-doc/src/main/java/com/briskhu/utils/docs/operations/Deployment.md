# 部署文档

## Jar包部署成后台运行的启动脚本
在jar包所在目录创建一个“start.sh”的脚本，该脚本内容如下：
```
# analysis start shell
nohup java -Djava.security.egd=file:/dev/./urandom -jar iflytek-analysis-liaoning.jar --spring.profiles.active=$1 &

if [ ! -d /data/logs/ ]; then
  mkdir -p /data/logs/debugging-platform
else
  echo /data/logs/debugging-platform exists
fi

nohup java -jar debugging-platform.jar --spring.profiles.active=debug --server.port=9999 --netty.port=9990  --debugging.callback.url=http://221.228.33.71:6060/njiot/heartbeat/service/recordHeartbeat --elink.get-nd-ctei-log-url=http://192.168.24.102:9010/manufacturer/redis/log >> /data/logs/debugging-platform/debugging-platform.log &
```
该脚本保存在本模块的路径下的runJarOnLinux.sh文件中。
