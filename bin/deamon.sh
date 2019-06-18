#!/usr/bin/env bash
usage="Usage: sinanya.sh {hdfs|yarn|Discommiss|hbase|kudu|hive|rest|alert|sinanya} (start|stop|restart)"

bin=`dirname "${BASH_SOURCE-$0}"`
bin=`cd "$bin"; pwd`

DEFAULT_LIBEXEC_DIR="$bin"/../libexec

#default value
Jar_Dir="$bin/../java-1.0-SNAPSHOT.jar"
startStop=$1
shift

PID_DIR="$bin"/../pid/
pid="$bin"/../pid/dice-sinanya.pid
STOP_TIMEOUT=5

case ${startStop} in

    (start)
    [ -w "$PID_DIR" ] ||  mkdir -p "$PID_DIR"

    if [ -f ${pid} ]; then
        if kill -0 `cat ${pid}` > /dev/null 2>&1; then
            echo sinanya running as process `cat ${pid}`.  Stop it first.
            exit 1
        fi
    fi

    echo starting sinanya
    sinanyaScript='java -cp '"$bin"'/../lib/*:'"$bin"'/../conf/log4j2.xml:'"$bin"'/../conf/*:'"$Jar_Dir"' -Dlog4j.configurationFile='"$bin"'/../conf/log4j2.xml -Xms1024m -Xmx2048m dice.sinanya.RunApplication'
    echo ${sinanyaScript}
    nohup ${sinanyaScript} >/dev/null 2>&1 &

    echo $! > ${pid}
    ;;

    (stop)
    if [ -f ${pid} ]; then
        TARGET_PID=`cat ${pid}`
        if kill -0 ${TARGET_PID} > /dev/null 2>&1; then
            echo stopping sinanya
            kill ${TARGET_PID}
            sleep ${STOP_TIMEOUT}
            if kill -0 ${TARGET_PID} > /dev/null 2>&1; then
                echo "sinanya did not stop gracefully after $STOP_TIMEOUT seconds: killing with kill -9"
                kill -9 ${TARGET_PID}
            fi
        else
            echo no sinanya to stop
        fi
        rm -f ${pid}
    else
        echo no sinanya to stop
    fi
    ;;

    (status)
    status=`ps -ef|grep -v grep|grep "java-1.0-SNAPSHOT.jar" |wc -l`
    if [ ${status} -eq 1 ];then
        TARGET_PID=`cat ${pid}`
        echo "sinanya" is running,PID is ${TARGET_PID}
    else
        if [ -f ${pid} ];then
            echo PID file already exists,but "sinanya" is stopped
        else
            echo "sinanya" is stopped
        fi
    fi
    ;;

    (restart)
    if [ -f ${pid} ]; then
        TARGET_PID=`cat ${pid}`
        if kill -0 ${TARGET_PID} > /dev/null 2>&1; then
            echo stopping sinanya
            kill ${TARGET_PID}
            sleep ${STOP_TIMEOUT}
            if kill -0 ${TARGET_PID} > /dev/null 2>&1; then
                echo "sinanya did not stop gracefully after $STOP_TIMEOUT seconds: killing with kill -9"
                kill -9 ${TARGET_PID}
            fi
        else
            echo no sinanya to stop
        fi
        rm -f ${pid}
    else
        echo no sinanya to stop
    fi
        [ -w "$PID_DIR" ] ||  mkdir -p "$PID_DIR"

    if [ -f ${pid} ]; then
        if kill -0 `cat ${pid}` > /dev/null 2>&1; then
            echo sinanya running as process `cat ${pid}`.  Stop it first.
            exit 1
        fi
    fi

    echo starting sinanya
    sinanyaScript='java -cp '"$bin"'/../lib/*:'"$bin"'/../conf/*:'"$Jar_Dir"' -Dlog4j.configurationFile='"$bin"'/../conf/log4j2.xml -Xms1024m -Xmx2048m vdian.hadoop.sinanya.HDFS_sinanya'
    echo ${sinanyaScript}
    nohup ${sinanyaScript} >/dev/null 2>&1 &

    echo $! > ${pid}
    ;;

    (*)
    echo ${usage}
    exit 1
    ;;

esac