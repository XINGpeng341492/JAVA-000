学习笔记
一、Java基础
1.字节码:javac -g xxx.java、javap -verbose xxx
2.类加载机制:加载、链接（验证、准备、解析）、初始化
3.自定义一个类加载器
4.JMM happens before 规则
5.JVM运行时内存模型
6.堆、非堆（栈、程序计数器）、堆外（method area、code cache、compressed）
二、Java进阶
1.jdk 常用命令：jmap,jinfo,jstack,jcmd ....
2.图形化工具：Jconsole,JvisualVM ,Jmc 等
3.GC相关
  垃圾标记算法
  垃圾回收算法
  常见垃圾回收器的组合，以及常用参数含义
  GC日志分析
  最佳实践，手动尝试各种G参数配置，使用wrk或者sb等压力测试工具通过具体现象理解
三、常见问题分析过程
  问题分析过程，与调优具体实践