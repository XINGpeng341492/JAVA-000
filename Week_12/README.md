

# week12 summary


## redis



## 主从配置
1. docker pull redis
2. docker run -itd --name redis6380 -p 6380:6379 redis
   docker run -itd --name redis6381 -p 6381:6379 redis
   docker run -itd --name redis6382 -p 6382:6379 redis
3. docker inspect containerid (查看容器内网ip)
4. slaveof 172.17.0.2 6379 (分别在6381，6382中执行该命令设置 6380 为主)
5. 验证主从配置

    127.0.0.1:6380> info replication
    # Replication
	role:master
	connected_slaves:2
	slave0:ip=172.17.0.3,port=6379,state=online,offset=2253,lag=1
	slave1:ip=172.17.0.4,port=6379,state=online,offset=2253,lag=0
	master_replid:8bdfc5cd9bd57e01869003462d20a5e65d9bac91
	master_replid2:0000000000000000000000000000000000000000
	master_repl_offset:2253
	second_repl_offset:-1
	repl_backlog_active:1
	repl_backlog_size:1048576
	repl_backlog_first_byte_offset:1
	repl_backlog_histlen:2253

   



## sentinel配置
1. 


## 集群配置
1. 

## https://www.cnblogs.com/vipzhou/p/8580495.html