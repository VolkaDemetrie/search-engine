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
    ,VALID_FAIL("FL0001", "검증실패")
    ,INIT_FAIL("FL0002", "초기화 실패")
    ,NO_DEFINITION_("FL0003", "초기화 실패")
    ;

    private final String code;
    private final String msg;

    ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
