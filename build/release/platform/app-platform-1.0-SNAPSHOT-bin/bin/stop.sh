#!/usr/bin/env bash
appVersion=app-platform-1.0-SNAPSHOT.jar

Pid=`ps -ef | grep ${appVersion} | grep -v grep | awk '{print $2}'`
echo "stop ${ModuleName}..."
if [ -n "${Pid}" ]
then
    kill -9 $Pid
fi