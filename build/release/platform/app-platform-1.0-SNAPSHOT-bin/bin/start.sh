#!/usr/bin/env bash

binDir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd ${binDir} && cd ../
curDir=`pwd`

if [ ! -n "$PRO_PATH" ]; then
    export PRO_PATH="${curDir}"
fi

#用哪个配置文件
profiles=deploy
rootDir=${curDir}
libPath=${rootDir}/lib
sourcePath=${rootDir}/conf
logPath=${rootDir}/logs
appVersion=app-platform-1.0-SNAPSHOT.jar

export PRO_PATH="${curDir}"
export ModuleName="app-platform"

if [ ! -d ${logPath} ]; then
    mkdir -p ${logPath}
fi

echo "start ${ModuleName}..."
nohup java -Dloader.path=${libPath},${sourcePath} \
-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=10039 \
-XX:+UseConcMarkSweepGC -Xmx2048m -Xms1024m  -XX:+PrintGCDateStamps -XX:+PrintGCDetails  -Xloggc:${logPath}/java_gc.log \
-XX:-HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${logPath} -jar ${libPath}/${appVersion} \
--spring.profiles.active=${profiles} --isJar=true &>${logPath}/${ModuleName}.log &