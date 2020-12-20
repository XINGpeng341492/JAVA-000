package com.xp.kid.rpcfx.exception;

/**
 * <p>TODO
 * </p>
 *
 * @author xingpeng
 * @date 2020/12/20 10:00 下午
 **/
public class BaseException extends RuntimeException{
    public Integer code;
    public Object data;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(Integer code, String message, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
