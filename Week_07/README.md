# week7 summary






## mysql基础

1. 索引结构。b+tree page的理解
2. 参数优化
3. 设计原则
4. 对应数据库文件存储结构需要了解

## 优化
 
1. 锁
2. 事物
3. 查询优化


## 高可用高性能

1. 读写分离 （代码层面or中间件）
2. 主从复制 （传统异步，半同步复制，MGR）
3. 主从切换 （手动，脚本，MHA ，MGR ,mysql cluster ....）


## 分库分表


## 批量插入测试百万数据 单线程批量插入 单批次 10000 性能远高于单条循环插入

2020-12-02 17:24:32.716  INFO 31170 --- [nio-8082-exec-1] c.e.w.config.DataSourceRouter            : ======current dbtype connection is ===: master
2020-12-02 17:24:33.050  INFO 31170 --- [nio-8082-exec-1] c.e.w.controller.IndexController         : ====cur===: 1000000
2020-12-02 17:24:33.050  INFO 31170 --- [nio-8082-exec-1] c.e.w.controller.IndexController         : ====batch costTime===: 523
2020-12-02 17:24:33.237  INFO 31170 --- [nio-8082-exec-1] c.e.w.config.DataSourceRouter            : ======current dbtype connection is ===: master
2020-12-02 17:24:33.571  INFO 31170 --- [nio-8082-exec-1] c.e.w.controller.IndexController         : ====cur===: 1010000
2020-12-02 17:24:33.571  INFO 31170 --- [nio-8082-exec-1] c.e.w.controller.IndexController         : ====batch costTime===: 521
2020-12-02 17:24:33.571  INFO 31170 --- [nio-8082-exec-1] c.e.w.controller.IndexController         : ====costtime===: 54634
