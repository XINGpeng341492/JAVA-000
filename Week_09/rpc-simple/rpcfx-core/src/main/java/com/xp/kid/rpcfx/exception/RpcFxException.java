package com.xp.kid.rpcfx.exception;

import org.springframework.http.HttpStatus;

/**
 * <p>TODO
 * </p>
 *
 * @author xingpeng
 * @date 2020/12/20 10:01 下午
 **/
public class RpcFxException extends BaseException{


    public RpcFxException(Integer code, String message) {
        super(code, message);
    }
}
