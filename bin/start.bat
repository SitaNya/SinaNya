@echo off
set CLASSPATH=../lib;../:%CLASSPATH%;

java -cp ../lib/*;../java-1.0-SNAPSHOT.jar dice.sinanya.RunApplication
exit
@pause