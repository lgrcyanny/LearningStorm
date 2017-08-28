## Setting up Storm local evn

you can refer to my blog [here](http://www.cyanny.com/2017/04/10/set-up-storm-on-mac-in-10min/)

## How to build and run

```shell

mvn clean package -DskipTests
./bin/storm jar learning-storm-1.0-SNAPSHOT.jar "com.learning.storm.TridentTest" 

```