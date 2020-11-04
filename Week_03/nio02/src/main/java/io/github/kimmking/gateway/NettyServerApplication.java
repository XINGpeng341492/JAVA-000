package io.github.kimmking.gateway;


import io.github.kimmking.gateway.inbound.HttpInboundServer;
import io.netty.handler.codec.json.JsonObjectDecoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NettyServerApplication {
    
    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "1.0.0";
    
    public static void main(String[] args) {
        String proxyServer = System.getProperty("proxyServer","http://localhost:8088");
        String proxyPort = System.getProperty("proxyPort","8888");
        //可抽离到配置中心或者其他 //暂时模拟传递
        List<String> proxyServerList = new ArrayList<>();
        proxyServerList.add("http://localhost:8080");
        proxyServerList.add("http://localhost:8081");
          //  http://localhost:8888/api/hello  ==> gateway API
          //  http://localhost:8088/api/hello  ==> backend service
    
        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" starting...");
        HttpInboundServer server = new HttpInboundServer(port, proxyServer,proxyServerList);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port + " for servers:" + proxyServerList.get(0));
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
