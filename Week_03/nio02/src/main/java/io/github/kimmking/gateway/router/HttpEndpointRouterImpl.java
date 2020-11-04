package io.github.kimmking.gateway.router;

import java.util.List;

/**
 * <p>TODO
 * </p>
 *
 * @author xingpeng
 * @date 2020/11/3 8:58 下午
 **/
public class HttpEndpointRouterImpl implements HttpEndpointRouter{
    @Override
    public String route(List<String> endpoints) {
        //简单模拟但节点路由到第一个节点
        return endpoints.get(0);
    }
}
