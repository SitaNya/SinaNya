@echo off
set CLASSPATH=../lib;../:%CLASSPATH%;

java -cp ../lib/*;../java-1.0-SNAPSHOT.jar -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:-CMSConcurrentMTEnabled -XX:CMSInitiatingOccupancyFraction=70 -XX:+CMSParallelRemarkEnabled dice.sinanya.RunApplication
@pause