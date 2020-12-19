package com.xp.kid.rpcfx.client;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.xp.kid.rpcfx.api.RpcfxRequest;
import com.xp.kid.rpcfx.api.RpcfxResponse;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public  class RpcAopfx {

    static {
        //ParserConfig.getGlobalInstance().addAccept("io.kimmking");
        ParserConfig.getGlobalInstance().addAccept("com.xp.kid");
    }

    public static <T> T create(final Class<T> serviceClass, final String url) {

        // 0. 替换动态代理 -> AOP
//        return (T) Proxy.newProxyInstance(RpcAopfx.class.getClassLoader(), new Class[] {serviceClass},
//                new RpcfxInvocationHandler(serviceClass, url));
        Enhancer enhancer = new Enhancer();
        //enhancer.setSuperclass(RpcAopfx.class);
        enhancer.setInterfaces(new Class[] {serviceClass});
        enhancer.setCallback(new RpcfxCglibInvocationHandler(serviceClass,url));
        return (T) enhancer.create();

    }

    public static class RpcfxCglibInvocationHandler implements MethodInterceptor {
        public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

        private final Class<?> serviceClass;
        private final String url;

        public RpcfxCglibInvocationHandler(Class<?> serviceClass, String url) {
            this.serviceClass = serviceClass;
            this.url = url;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

            RpcfxRequest request = new RpcfxRequest();
            request.setServiceClass(this.serviceClass.getName());
            request.setMethod(method.getName());
            request.setParams(objects);

            RpcfxResponse response = post(request, url);

            // 这里判断response.status，处理异常
            // 考虑封装一个全局的RpcfxException

            return JSON.parse(response.getResult().toString());
        }

        private RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
            String reqJson = JSON.toJSONString(req);
            System.out.println("req json: " + reqJson);

            // 1.可以复用client
            // 2.尝试使用httpclient或者netty client
            OkHttpClient client = new OkHttpClient();
            final Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(JSONTYPE, reqJson))
                    .build();
            String respJson = client.newCall(request).execute().body().string();
            System.out.println("resp json: " + respJson);
            return JSON.parseObject(respJson, RpcfxResponse.class);
        }
    }

}
