# Akka sample

https://docs.google.com/presentation/d/1ApilZQnPTRCK3FkIQoXsjy5zd4R9z8Mz4MrVQ00Rk-o/edit#slide=id.p

## usage

### PayPaySystem

make jar file
```
$ sbt bootPay/assembly
$ sbt remotePay/assembly
```

run on separate consoles
```
$ java -jar {path}/remotePay/target/scala-2.11/remote-pay-0.1.jar
```
```
$ java -jar {path}/bootPay/target/scala-2.11/boot-pay-0.1.jar
```


### SuperVisorSystem

make jar file
```
$ sbt superVisor/assembly
```

run app
```
$ java -jar {path}/superVisor/target/scala-2.11/super-visor-0.1.jar
```

