package com.volka.searchengine.core.exception;

import com.volka.searchengine.core.constant.ResponseCode;
import lombok.Getter;

/**
 * 서비스 레이어 예외
 *
 * @author volka
 */
@Getter
public class BizException extends RuntimeException {
    private String code;
    private String msg;
    private Object content;

    public BizException(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    public BizException(ResponseCode responseCode, Throwable e) {
        super(e);
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }


    public BizException(String errCode) {
        this.code = errCode;
    }

    public BizException(String errCode, Throwable t) {
        super(t);
        this.code = errCode;
    }

    public BizException(String errCode, Object content) {
        this.code = errCode;
        this.content = content;
    }
}
