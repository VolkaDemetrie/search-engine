package com.volka.searchengine.core.dto;

import com.volka.searchengine.core.constant.ResponseCode;
import lombok.Getter;

/**
 * 공통 응답 DTO
 *
 * @author volka
 * @param <T>
 */
@Getter
public class ResponseDTO<T> {
    private final String code;
    private final String msg;
    private T content;

    ResponseDTO(ResponseCode responseCode, T content) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
        this.content = content;
    }

    ResponseDTO(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    ResponseDTO(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    ResponseDTO(String code, String msg, T content) {
        this.code = code;
        this.msg = msg;
        this.content = content;
    }

    public static <T> ResponseDTO<T> response(T content) {
        return new ResponseDTO<>(ResponseCode.SUCCESS, content);
    }

    public static ResponseDTO<ResponseCode> response(ResponseCode responseCode) {
        return new ResponseDTO<>(responseCode);
    }

    public static <T> ResponseDTO<T> response(String code, String msg) {
        return new ResponseDTO<>(code, msg);
    }

    public static <T> ResponseDTO<T> response(String code, String msg, T content) {
        return new ResponseDTO<>(code, msg, content);
    }
}
