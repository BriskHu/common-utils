# 常用工具
本项目管理在Java Web开发中常用的一些工具。

# Docker 基础知识

## 搭建Docker环境

## 构建镜像
1. 在项目模块下的src/java根路径下创建一个“docker”文件夹，在这个文件夹内创建一个Dockerfile文件（这个文件没有后缀名），
这个文件的内容可以参考下面的内容：
```
# Start with a base image containing Java runtime. The default timezone is Shanghai.
FROM 基础镜像仓库地址

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Add the application's jar to the container
ADD rest-test.jar rest-test.jar

RUN sh -c 'touch /rest-test.jar'
# Set jvm
ENV JAVA_OPTS="-server -Xmx512m -Xms512m -Djava.awt.headless=true"

# Run the jar file
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /rest-test.jar" ]

```

2. 在模块的根路径下创建一个Dockerfile文件。这个文件的内容可以跟第一步创建的文件保持相同。

3. 在模块的pom文件中加入下面的内容：

```
<plugin>
    <groupId>com.spotify</groupId>
    <artifactId>dockerfile-maven-plugin</artifactId>
    <version>${docker.plugin.version}</version>
    <executions>
        <execution>
            <id>default</id>
            <goals>
                <goal>build</goal>
                <goal>push</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <username>abcd</username>
        <password>pwd for abcd</password>
        <repository>${docker.repository.prefix}/${project.build.finalName}</repository>
        <tag>${project.version}_${current.time}</tag>
        <buildArgs>
            <JAR_FILE>target/${project.build.finalName}.jar</JAR_FILE>
        </buildArgs>
    </configuration>
</plugin>
```

4. 使用下面的脚本将镜像推到仓库(比如阿里云仓库、自己的仓库)：

```
#!/bin/bash

DOCKER_REGISTRY=registry.cn-hangzhou.aliyuncs.com

DOCKER_REGISTRY_USERNAME=abcd

DOCKER_IMAGE_NAME=registry.cn-hangzhou.aliyuncs.com/programName/packageName

DOCKER_IMAGE_TAG=$1

# ==========================stop container ========================
CONTAINER_ID=$(docker ps|grep ${DOCKER_IMAGE_NAME}| awk '{print $1}')
if [[ $CONTAINER_ID ]]
then
    docker kill $CONTAINER_ID
    echo "INFO：The container about of image ${DOCKER_IMAGE_NAME} Stopped."
else
    echo "INFO: No cantainer need be kill."
fi

# =========================remove container===============================
if [[ $CONTAINER_ID ]]
then
    docker rm $CONTAINER_ID
    echo "Remove container about of image ${DOCKER_IMAGE_NAME} success"
else
    echo "INFO: No contain need be remove"
fi

# =========================remove old images==============================
IMAGEID=$(docker images | grep ${DOCKER_IMAGE_NAME} | awk '{print $3}')
if [[ $IMAGEID ]]
then
    docker images | grep ${DOCKER_IMAGE_NAME} | awk '{print $3}' | xargs docker rmi -f
else
    echo "INFO：No images need be rmi"
fi

# ==========================pull image and run========================
sudo docker login --username=${DOCKER_REGISTRY_USERNAME} ${DOCKER_REGISTRY}
echo "INFO：Starting pull image tag of ${DOCKER_IMAGE_TAG} to docker registry ${DOCKER_REGISTRY}"
sudo docker pull ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}
docker run -it -d --name=containerName --volume=/Users/temp:/logs --publish=prot:prot ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}

```

利用指令sh build.sh packageName来推送包。

## 部署镜像



# maven 基础知识

## 多模块项目下对单个模块进行打包
### 基于assembly的打包构建
如果不需要镜像部署，则可以maven打成tar包部署。以打包rest-test为例
```
mvn clean package -DskipTests -pl :rest-test -am
```

### maven模块单独编译
对于多模块的庞大项目根pom编译项目是一个很头疼的问题，如果只需要编译自己的模块，则可以通过maven pl命令来编译，
例如编译app-rest，编译app-rest是会把相关的依赖模块一起编译

```
mvn clean compile -DskipTests -pl :rest-test -am
```

如果上述命令不能执行，可能需要在pom文件中加入下面的构建plugin：

```
          <plugin>
                <artifactId>maven-assembly-plugin</artifactId>
                <version>3.1.0</version>
                <configuration>
                    <descriptors>
                        <descriptor>src/main/assembly/assembly.xml</descriptor>
                    </descriptors>
                </configuration>
                <executions>
                    <execution>
                        <id>make-assembly</id>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>

            <plugin>
                <groupId>com.spotify</groupId>
                <artifactId>docker-maven-plugin</artifactId>
                <version>1.1.0</version>
                <configuration>
                    <!--<dockerHost>http://192.168.248.135:2376</dockerHost>-->
                    <imageName>${project.groupId}/${project.build.finalName}:${project.version}</imageName>
                    <dockerDirectory>${project.basedir}/src/main/docker</dockerDirectory>
                    <resources>
                        <resource>
                            <directory>${project.build.directory}</directory>
                            <include>${project.build.finalName}.jar</include>
                        </resource>
                    </resources>
                </configuration>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.0</version>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
        </plugins>

```

启动脚本 start.sh：

```
start.sh 
#!/bin/bash
# Startup script for spring boot project
# @author shalousun
# see https://github.com/Shalousun/ApplicationPower

# include yaml_parse function
. "$(dirname "$0")"/yaml.sh

SERVER_NAME='iflytek-analysis'
JAR_NAME='iflytek-analysis.jar'
cd $(dirname $0)
BIN_DIR=$(pwd)
cd ..
DEPLOY_DIR=$(pwd)
CONF_DIR=$DEPLOY_DIR/config
# log file
LOG_IMPL_FILE=logback.xml
APPLICATION_FILE=application.yml

# ====================================FIND SERVER PORT===================================================
SERVER_PORT=""
if [ "$APPLICATION_FILE" = "application.yml" ]
then
    # remove ^M in file
    sed -i "s/$(echo -e '\015')/\n/g" config/application.yml
    # read yml file
    eval $(YamlParse__parse "config/application.yml" "config_")
    SERVER_PORT=$config_server_port
else
    SERVER_PORT=$(sed '/server.port/!d;s/.*=//' config/application.properties | tr -d '\r')
fi
# ===================================FIND SERVER PORT END================================================

PIDS=$(ps -ef | grep java | grep "$CONF_DIR" |awk '{print $2}')
if [ "$1" = "status" ]; then
    if [ -n "$PIDS" ]; then
        echo "The $SERVER_NAME is running...!"
        echo "PID: $PIDS"
        echo "Used port: $SERVER_PORT"
        exit 0
    else
        echo "The $SERVER_NAME is stopped"
        exit 0
    fi
fi

if [ -n "$PIDS" ]; then
    echo "ERROR: The $SERVER_NAME already started!"
    echo "PID: $PIDS"
    echo "Used port: $SERVER_PORT"
    exit 1
fi

if [ -n "$SERVER_PORT" ]; then
    SERVER_PORT_COUNT=$(netstat -tln | grep "\b$SERVER_PORT\b" | wc -l)
    if [ "$SERVER_PORT_COUNT" -gt 0 ]; then
        echo "ERROR: The $SERVER_NAME port $SERVER_PORT already used!"
        exit 1
    fi
fi
# =================================init logging dir and config===========================================
LOGS_DIR=$DEPLOY_DIR/logs
if [ ! -d "$LOGS_DIR" ]; then
    mkdir $LOGS_DIR
fi
STDOUT_FILE=$LOGS_DIR/stdout.log

LOGGING_CONFIG=""
if [ -f "$CONF_DIR/$LOG_IMPL_FILE" ]
then
    LOGGING_CONFIG="-Dlogging.config=$CONF_DIR/$LOG_IMPL_FILE"
fi

CONFIG_FILES=" -Dlogging.path=$LOGS_DIR $LOGGING_CONFIG -Dspring.config.location=$CONF_DIR/$APPLICATION_FILE,$CONF_DIR/messages.properties"

# =================================set jvm params=======================================================
JAVA_DEFAULT_OPTS=" -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true "
JAVA_DEBUG_OPTS=""
if [ "$1" = "debug" ]; then
    JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n "
fi

JAVA_JMX_OPTS=""
if [ "$1" = "jmx" ]; then
    JAVA_JMX_OPTS="-Djava.rmi.server.hostname=192.168.85.63 -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "
fi

JAVA_MEM_OPTS=""
BITS=$(java -version 2>&1 | grep -i 64-bit)
if [ -n "$BITS" ]; then
    JAVA_MEM_OPTS=" -server -Xmx1024m -Xms1024m -Xmn256m -XX:PermSize=128m -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 "
else
    JAVA_MEM_OPTS=" -server -Xms512m -Xmx512m -XX:PermSize=128m -XX:SurvivorRatio=2 -XX:+UseParallelGC "
fi

# =====================get set custom jvm params ==============================================================

if [ -r "$BIN_DIR/setenv.sh" ]; then
  . "$BIN_DIR/setenv.sh"
fi
JAVA_OPTS_TEMP=
if [ -n "$JAVA_OPTS" ]
then
    # use custom jvm param in setenv.sh
    echo "INFO: use custom jvm params in setenv.sh"
    JAVA_OPTS_TEMP=$JAVA_OPTS
else
    # use default jvm param in this script
    echo "INFO: use default jvm params in start.sh"
    JAVA_OPTS_TEMP="$JAVA_DEFAULT_OPTS $JAVA_MEM_OPTS"
fi
echo -e "Starting the $SERVER_NAME ..."
# nohup java $JAVA_OPTS_TEMP $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS $CONFIG_FILES -jar $DEPLOY_DIR/lib/$JAR_NAME > $STDOUT_FILE 2>&1 &
nohup java $JAVA_OPTS_TEMP $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS -jar $DEPLOY_DIR/$JAR_NAME >> $STDOUT_FILE 2>&1 &

COUNT=0
while [ "$COUNT" -lt 1 ]; do
    echo -e ".\\c"
    sleep 1
    if [ -n "$SERVER_PORT" ]; then
        COUNT=$(netstat -an | grep "$SERVER_PORT" | wc -l)
    else
        COUNT=$(ps -ef | grep java | grep "$DEPLOY_DIR" | awk '{print $2}' | wc -l)
    fi
    if [ "$COUNT" -gt 0 ]; then
        break
    fi
done

# ====================print finish info=================================================================================
echo "INFO: OK!"
PIDS=$(ps -ef | grep java | grep "$DEPLOY_DIR" | awk '{print $2}')

echo "Command line argument: $JAVA_OPTS_TEMP"
echo "PID: $PIDS"
echo "PORT: $SERVER_PORT"
echo "STDOUT: $STDOUT_FILE"

```

