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
    private String code;
    private String msg;
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

    public static <T> ResponseDTO<T> response(T content) {
        return new ResponseDTO<>(ResponseCode.SUCCESS, content);
    }

    public static ResponseDTO<ResponseCode> response(ResponseCode responseCode) {
        return new ResponseDTO<>(responseCode);
    }
}
