package com.volka.searchengine.core.advice;

import com.volka.searchengine.core.constant.ResponseCode;
import com.volka.searchengine.core.dto.ResponseDTO;
import com.volka.searchengine.core.exception.BizException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 예외 처리 컨트롤러 어드바이스
 *
 * @author volka
 */
@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionControllerAdvice {

    private final MessageSource messageSource;

    @ExceptionHandler
    public ResponseDTO<?> exceptionHandler(Exception e) {
        try {
            log.error("###[ERROR] EXCEPTION :: {}", e.toString());
            log.error("###[ERROR] MSG :: {}", e.getLocalizedMessage());
            if (e.getCause() != null) log.error("###[ERROR] CAUSE :: {}", e.getCause().getLocalizedMessage());

            if (e instanceof BizException) {
                BizException bizException = (BizException) e;
                return ResponseDTO.response(bizException.getCode(), messageSource.getMessage(bizException.getCode(), null, LocaleContextHolder.getLocale()));

            } else if (e instanceof MethodArgumentNotValidException) {
                Map<String, String> errMap = new HashMap<>();

                try {
                    MethodArgumentNotValidException validException = (MethodArgumentNotValidException) e;
                    validException.getBindingResult().getAllErrors().forEach(err -> errMap.put(((FieldError) err).getField(), err.getDefaultMessage()));

                    return ResponseDTO.response("FL0001", messageSource.getMessage("FL0001", null, LocaleContextHolder.getLocale()), errMap);

                } catch (NoSuchMessageException ex1) {
                    log.error("undefined this error code in this language pack");
                } catch (Exception ex2) {
                    log.error("valid exception handler error :: {}", ex2);
                }
            } else if (e instanceof ConstraintViolationException) {
                try {
                    ConstraintViolationException validException = (ConstraintViolationException) e;
                    return ResponseDTO.response("FL0001", messageSource.getMessage("FL0001", null, LocaleContextHolder.getLocale()), validException.getMessage());
                } catch (Exception ex) {
                    log.error("valid exception handler error :: {}", ex);
                }
            } else if (e instanceof BindException) { //바인드 예외 추가
                Map<String, String> errMap = new HashMap<>();

                try {
                    BindException bindException = (BindException) e;
                    bindException.getBindingResult().getAllErrors().forEach(err -> errMap.put(((FieldError) err).getField(), err.getDefaultMessage()));

                    return ResponseDTO.response("FL0001", messageSource.getMessage("FL0001", null, LocaleContextHolder.getLocale()), errMap);
                } catch (Exception ex) {
                    log.error("valid exception handler error bind Exception :: {}", ex);
                }
            }
            return ResponseDTO.response(ResponseCode.FAIL);

        } catch (Exception inHandlerException) {
            log.error("Handler Exception :: {}", inHandlerException.getLocalizedMessage());
            return ResponseDTO.response(ResponseCode.FAIL);
        }
    }
}
