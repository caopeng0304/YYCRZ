#kafka  
##启动zookeeper  
nohup ./zookeeper-server-start.sh ../config/zookeeper.properties &  
##启动kafka  
nohup ./kafka-server-start.sh ../config/server.properties &  
##修改配置文件允许远程访问  
../KAFKA_HOME/config/server.properties 36行  
advertised.listeners=PLAINTEXT://42.192.148.85:9092  
##在consumer端配置访问路径  
在host文件中添加映射  
kafka服务端ip   服务端主机名  

##关闭命令  
./kafka-server-stop.sh  


#命令  
##topic  
###创建  
bin/kafka-topics.sh --create --topic test0 --zookeeper 192.168.187.146:2181 --config max.message.bytes=12800000 --config flush.messages=1 --partitions 5 --replication-factor 1  
--create： 指定创建topic动作  
--topic：指定新建topic的名称  
--zookeeper： 指定kafka连接zk的连接url，该值和server.properties文件中的配置项{zookeeper.connect}一样  
--config：指定当前topic上有效的参数值，参数列表参考文档为: Topic-level configuration  
--partitions：指定当前创建的kafka分区数量，默认为1个  
--replication-factor：指定每个分区的复制因子个数，默认1个  

###查询topic列表  
./kafka-topics.sh  --list --bootstrap-server localhost:9092
###查看topic详情  
bin/kafka-topics.sh --describe --zookeeper 192.168.187.146:2181  --topic test0  
--describe： 指定是展示详细信息命令  
--zookeeper： 指定kafka连接zk的连接url，该值和server.properties文件中的配置项{zookeeper.connect}一样  
--topic：指定需要展示数据的topic名称  


###删除  
bin/kafka-topics.sh --delete --topic test0 --zookeeper 192.168.187.146:2181  
Note: This will have no impact if delete.topic.enable is not set to true.## 默认情况下，删除是标记删除，没有实际删除这个Topic；如果运行删除Topic，两种方式：  
方式一：通过delete命令删除后，手动将本地磁盘以及zk上的相关topic的信息删除即可  
方式二：配置server.properties文件，给定参数delete.topic.enable=true，重启kafka服务，此时执行delete命令表示允许进行Topic的删除  
