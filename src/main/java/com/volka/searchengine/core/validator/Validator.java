package com.volka.searchengine.core.validator;

import java.util.regex.Pattern;

/**
 * 커스텀 어노테이션 검증 상속용 클래스
 *
 * @author volka
 */
public class Validator {

    protected static final Pattern NUMBER_VALID_PATTERN = Pattern.compile("^[0-9]*$"); //숫자만 패턴
    protected static final Pattern SYSTEM_CODE_PATTERN = Pattern.compile("^[0-9A-Z]{7,10}$"); //공통코드 정규식. 그룹코드 고려하여 7자리 포함
    protected static final Pattern SYSTEM_ID_PATTERN = Pattern.compile("^[0-9A-Z]{20}$"); //채번한 시스템 ID 정규식
    protected static final Pattern MOBILE_PATTERN = Pattern.compile("^\\d{3}-\\d{3,4}-\\d{4}$");
    /**
     * String isNullOrEmpty 메소드
     *
     * @param item
     * @return
     */
    protected static boolean isNullOrEmpty(String item) {
        return item == null || item.isEmpty();
    }
}
