package io.github.kimmking.gateway.outbound.okhttp;

import io.github.kimmking.gateway.filter.Const;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * <p>
 *     httpclient
 * </p>
 *
 * @author xingpeng
 * @date 2020/10/27 11:47 上午
 **/
public class HttpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class.getSimpleName());
    private static final int HTTP_TIMEOUT = 10000;
    private static final int HTTP_MAX_TOTAL = 1000;
    private static final int HTTP_MAX_PERROUTE = 500;
    private static final int HTTP_CON_TIMEOUT = 10000;
    private static final int HTTP_CON_REQ_TIMEOUT = 500;
    private static final String DEF_CHARSET = "UTF-8";
    private static int minTimeSecond = 0;
    private static CloseableHttpClient closeableHttpClient = null;
    private static RequestConfig defaultRequestConfig = null;

    static {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
        connectionManager.setDefaultSocketConfig(socketConfig);
        connectionManager.setMaxTotal(HTTP_MAX_TOTAL);
        connectionManager.setDefaultMaxPerRoute(HTTP_MAX_PERROUTE);
        defaultRequestConfig = RequestConfig.custom().setSocketTimeout(HTTP_TIMEOUT).setConnectTimeout(HTTP_CON_TIMEOUT).setConnectionRequestTimeout(HTTP_CON_REQ_TIMEOUT).build();
        closeableHttpClient = HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(defaultRequestConfig).build();
    }


    public static String get(String url, int timeout) throws Exception{
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout is not right :" + timeout);
        }
        RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).setSocketTimeout(timeout).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        long beginTime = System.currentTimeMillis();
        CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
        long costTime =  System.currentTimeMillis() - beginTime;
        if (costTime>=minTimeSecond){
            logger.info("costTime="+costTime+"ms,url="+url);
        }
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, DEF_CHARSET);
        EntityUtils.consume(entity);
        return result;
    }

    public static String get(String url, Header header) throws Exception {
        RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).setSocketTimeout(HTTP_TIMEOUT).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(header);
        httpGet.setConfig(requestConfig);

        return get(httpGet, url);
    }

    private static String get(HttpGet httpGet, String url) throws Exception {
        long beginTime = System.currentTimeMillis();
        CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
        long costTime = System.currentTimeMillis() - beginTime;
        if (costTime >= minTimeSecond) {
            logger.info("costTime=" + costTime + "ms,url=" + url);
        }
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, DEF_CHARSET);
        EntityUtils.consume(entity);
        return result;
    }

    public static String get(String url) throws Exception{
        return get(url, HTTP_TIMEOUT);
    }

    public static String post(String url, String content) throws Exception{
        HttpEntity httpEntity = new StringEntity(content, DEF_CHARSET);
        return post(url, httpEntity);
    }

    public static String post(String url, HttpEntity httpEntity) throws Exception {
        RequestConfig config = RequestConfig.copy(defaultRequestConfig).setSocketTimeout(HTTP_TIMEOUT).build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(config);
        httpPost.setEntity(httpEntity);
        long beginTime = System.currentTimeMillis();
        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
        long costTime =  System.currentTimeMillis() - beginTime;
        if (costTime>=minTimeSecond){
            logger.info("costTime="+costTime+"ms,url="+url);
        }
        HttpEntity responseEntity = response.getEntity();
        String rt = EntityUtils.toString(responseEntity, DEF_CHARSET);
        EntityUtils.consume(responseEntity);
        return rt;
    }


    public static void main(String[] args) {
        try {
            String res = HttpClientUtil.get("http://localhost:8088/test");
            logger.info("res==:,{}",res);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void testGet(FullHttpRequest inboundReq, ChannelHandlerContext ctx, String url) {
        FullHttpResponse fullRes = null;
        RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).setSocketTimeout(HTTP_TIMEOUT).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        httpGet.setHeader(Const.NIO_TEST_KEY,inboundReq.headers().get(Const.NIO_TEST_KEY));
        long beginTime = System.currentTimeMillis();
        CloseableHttpResponse response = null;
        try {
            response = closeableHttpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long costTime =  System.currentTimeMillis() - beginTime;
        if (costTime>=minTimeSecond){
            logger.info("costTime="+costTime+"ms,url="+url);
        }
        HttpEntity entity = response.getEntity();
        try {
            String result = EntityUtils.toString(entity, DEF_CHARSET);
            fullRes = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(result.getBytes("UTF-8")));
            fullRes.headers().set("Content-Type", "application/json");
            fullRes.headers().setInt("Content-Length", fullRes.content().readableBytes());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("处理测试接口出错", e);
            fullRes = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            /*if (inboundReq != null) {
                if (!HttpUtil.isKeepAlive(inboundReq)) {
                    ctx.write(fullRes).addListener(ChannelFutureListener.CLOSE);
                } else {
                    fullRes.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(fullRes);
                }
            }*/

            if (inboundReq != null) {
                if (!HttpUtil.isKeepAlive(inboundReq)) {
                    ctx.write(fullRes).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(fullRes);
                }
            }
            ctx.flush();


        }
    }
}
