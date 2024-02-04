package com.volka.searchengine.core.advice;

import com.volka.searchengine.core.constant.ResponseCode;
import com.volka.searchengine.core.dto.ResponseDTO;
import com.volka.searchengine.core.exception.SearchException;
import com.volka.searchengine.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 예외 처리 컨트롤러 어드바이스
 *
 * @author volka
 */
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = {SearchException.class, ServiceException.class, Exception.class})
    public ResponseDTO<?> exceptionHandler(Exception e) {
        try {
            log.error("ERROR MSG :: {}", e.getLocalizedMessage());
            if (e.getCause() != null) log.error("ERROR CAUSE :: {}", e.getCause().getMessage());

            if (e instanceof ServiceException) {
                //TODO : i18n 에러 메시지 적용
                return ResponseDTO.response(ResponseCode.FAIL);
            } else if (e instanceof SearchException) {
                //TODO : i18n 에러 메시지 적용
                return ResponseDTO.response(ResponseCode.FAIL);
            } else {
                return ResponseDTO.response(ResponseCode.FAIL);
            }
        } catch (Exception inHandlerException) {
            log.error("Handler Exception :: {}", inHandlerException.getLocalizedMessage());
            return ResponseDTO.response(ResponseCode.FAIL);
        }
    }
}
