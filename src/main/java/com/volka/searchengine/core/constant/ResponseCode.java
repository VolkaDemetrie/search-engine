package com.volka.searchengine.core.constant;

import lombok.Getter;

/**
 * 응답 코드 메시지 상수
 *
 * @author volka
 */
@Getter
public enum ResponseCode {

    SUCCESS("SC0000", "성공")
    ,FAIL("FL0000", "실패")
    ,INIT_FAIL("FL0001", "초기화 실패")
    ;

    private final String code;
    private final String msg;

    ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
