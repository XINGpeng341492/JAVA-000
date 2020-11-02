package io.github.kimmking.gateway.filter;

import io.github.kimmking.gateway.outbound.okhttp.HttpClientUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>TODO
 * </p>
 *
 * @author xingpeng
 * @date 2020/11/1 10:05 下午
 **/
public class HttpRequestFilterImpl implements HttpRequestFilter{
    private static Logger logger = LoggerFactory.getLogger(HttpRequestFilterImpl.class);

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {

        logger.info("xptest1111");
        fullRequest.headers().set(Const.NIO_TEST_KEY,"xpStyle");
        logger.info("niokey=====: "+fullRequest.headers().get(Const.NIO_TEST_KEY));
    }
}
