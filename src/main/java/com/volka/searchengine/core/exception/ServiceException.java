package com.volka.searchengine.core.exception;

import com.volka.searchengine.core.constant.ResponseCode;
import lombok.Getter;

/**
 * 서비스 레이어 예외
 *
 * @author volka
 */
@Getter
public class ServiceException extends RuntimeException {
    private String code;
    private String msg;

    public ServiceException(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }
}
