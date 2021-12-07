### Execute Docker Compose
```shell
$ docker-compose up -d -f docker-compose.yml

$ docker ps -a
CONTAINER ID   IMAGE                    COMMAND                  CREATED          STATUS                     PORTS                                                                   NAMES
115b7c1b7a7c   wurstmeister/zookeeper   "/bin/sh -c '/usr/sb…"   56 minutes ago   Up 56 minutes              22/tcp, 2888/tcp, 3888/tcp, 0.0.0.0:2181->2181/tcp, :::2181->2181/tcp   sample-zookeeper
0c951512cdf1   wurstmeister/kafka       "start-kafka.sh"         56 minutes ago   Up 56 minutes              0.0.0.0:9092->9092/tcp, :::9092->9092/tcp                               sample-kafka
62f0781a2813   mysql:latest             "docker-entrypoint.s…"   6 hours ago      Up 5 hours                 0.0.0.0:3306->3306/tcp, :::3306->3306/tcp, 33060/tcp                    sample-mysql
```
### Setting a Kafka
Download Kafka Binary
```shell
$ wget https://archive.apache.org/dist/kafka/2.3.0/kafka_2.12-2.3.0.tgz
```

Producer Test

```shell
$ bin/kafka-console-producer.sh --topic sample --broker-list localhost:9092
> {"id":"1234"}
```

Consumer Test
```shell
$ bin/kafka-console-consumer.sh --topic sample --bootstrap-server localhost:9092 --from-beginning
{"id":"1234"}
```

### Create a Database
```
mysql> create database sample;
Query OK, 1 row affected (0.00 sec)

mysql> create user 'sample'@'%' identified by 'sample';
Query OK, 0 rows affected (0.01 sec)

mysql> grant all privileges on sample.* to 'sample'@'%';
Query OK, 0 rows affected (0.00 sec)

mysql> flush privileges;
Query OK, 0 rows affected (0.00 sec)
```

### Containerization
You should build dockerfile and push to ECR.
```
$ aws configure
$ aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin xxxxxxx.dkr.ecr.ap-northeast-2.amazonaws.com
$ docker build -t xxxxxxx.dkr.ecr.ap-northeast-2.amazonaws.com/sample-member-api:v1 -t xxxxxxx.dkr.ecr.ap-northeast-2.amazonaws.com/sample-member-api:latest .
$ docker push xxxxxxx.dkr.ecr.ap-northeast-2.amazonaws.com/sample-member-api
```
