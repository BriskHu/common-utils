#!/usr/bin/env bash
# analysis start shell

if [ ! -d /data/logs/ ]; then
  mkdir -p /data/logs/debugging-platform
else
  echo /data/logs/debugging-platform exists
fi

nohup java -jar rest-test.jar --spring.profiles.active=debug --server.port=9999 --netty.port=9990  >> /data/logs/rest-test/rest-test.log &
# run with security file and set the running configuration by the cml parameter.
nohup java -Djava.security.egd=file:/dev/./urandom -jar rest-test.jar --spring.profiles.active=$1 &

