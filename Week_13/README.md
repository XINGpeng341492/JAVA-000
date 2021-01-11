# week13 summary

## kafka 集群搭建


1. 拉取镜像
  
```
 	docker pull wurstmeister/zookeeper
	docker pull wurstmeister/kafka
```

2. 启动容器

```
docker run -d --name zookeeper -p 2181:2181 wurstmeister/zookeeper

docker inspect [containerid]


docker run -d --name kafka -p 9092:9092 -e KAFKA_BROKER_ID=0 -e KAFKA_ZOOKEEPER_CONNECT={host-ip}:2181/kafka -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://{host-ip}:9092 -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 wurstmeister/kafka


在 host-ip 这块就不能填本机 ip（windows 和 linux 可以），需要填docker.for.mac.host.internal，zookeeper 端口启动在2181，kafka 即将启动在9092，那么我的命令是这样的docker run -d --name kafka -p 9092:9092 -e KAFKA_BROKER_ID=0 -e KAFKA_ZOOKEEPER_CONNECT=docker.for.mac.host.internal:2181/kafka -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://docker.for.mac.host.internal:9092 -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 wurstmeister/kafka



-d 参数指定容器后台运行

--name kafka 参数指定容器别名

-e 参数可以设置 docker 容器内环境变量，每个变量的解释：

KAFKA_BROKER_ID=0
在 kafka 集群中，每个 kafka 都有一个 BROKER_ID 来区分自己

KAFKA_ZOOKEEPER_CONNECT={host-ip}:{zookeeper-port}/kafka
配置 zookeeper 管理 kafka 的路径

KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://{host-ip}:9092
把 kafka 的地址端口注册给 zookeeper

KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092
kafka 监听地址

```

3. 测试
  
```
 	docker exec -it kafka bash

	cd/opt/kafka_2.13-2.7.0/bin



bash-4.4# cd /opt/kafka_2.13-2.7.0/bin/
bash-4.4# ./kafka-console-producer.sh --broker-list localhost:9092 --topic first-topic
>qweqwe
>qweqwe
>qweqwe
>


bash-4.4# ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first-topic --from-beginning

qweqwe
qweqwe
qweqwe


 

```

4. 自带性能脚本测试
