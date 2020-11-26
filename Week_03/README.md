1.理解高性能
  高并发
  高吞吐
  低延迟
2.netty如何实现高性能
  为什么netty不直接使用jdk nio？（规避了jdk nio的相关bug 统一的API更好更强大，隔离了变化屏蔽细节）
  处理了沾包、半包现象
  支持多用编码解码协议
  完善的keepalive与idle监测异常处理
  流量整形
  做了大量的优化 eg:(网络程序优化，连接优化， nagle算法开启，编解码相关的压缩等，)

3. netty 重的常用类 （BECH）
   
  查看源码中channel如何绑定到对应的eventloop上
  基础的基于netty的客户端 、服务端的简易编写
4.netty优化的常规打法
  不要阻塞EventLoop
  系统参数优化：适当调大文件描述符数量
  缓冲区优化
  keepalive 周期优化 idle优化
  内存与bytebuffer优化（DirectBuffer,HeapBuffer）
  其他（ioRatio,waterMark,Traffic sharping）





