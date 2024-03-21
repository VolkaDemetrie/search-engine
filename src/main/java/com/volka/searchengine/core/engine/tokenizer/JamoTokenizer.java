package com.volka.searchengine.core.engine.tokenizer;


/**
 * 자모 분리 Tokenizer
 *
 * @author volka
 */
public abstract class JamoTokenizer {

    private static final char CHOSUNG_BEGIN_UNICODE = 12593;
    private static final char CHOSUNG_END_UNICODE = 12622;
    private static final char HANGUEL_BEGIN_UNICODE = 44032;
    private static final char HANGUEL_END_UNICODE = 55203;
    private static final char NUMBER_BEGIN_UNICODE = 48;
    private static final char NUMBER_END_UNICODE = 57;
    private static final char ENGLISH_LOWER_BEGIN_UNICODE = 65;
    private static final char ENGLISH_LOWER_END_UNICODE = 90;
    private static final char ENGLISH_UPPER_BEGIN_UNICODE = 97;
    private static final char ENGLISH_UPPER_END_UNICODE = 122;


    public boolean isPossibleCharacter(char c){
        return (c >= NUMBER_BEGIN_UNICODE && c <= NUMBER_END_UNICODE)
                || (c >= ENGLISH_UPPER_BEGIN_UNICODE && c <= ENGLISH_UPPER_END_UNICODE)
                || (c >= ENGLISH_LOWER_BEGIN_UNICODE && c <= ENGLISH_LOWER_END_UNICODE)
                || (c >= HANGUEL_BEGIN_UNICODE && c <= HANGUEL_END_UNICODE)
                || (c >= CHOSUNG_BEGIN_UNICODE && c <= CHOSUNG_END_UNICODE);
    }

    /**
     * [분리 기본 공식]
     *     초성 = ( ( (글자 - 0xAC00) - (글자 - 0xAC00) % 28 ) ) / 28 ) / 21
     *     중성 = ( ( (글자 - 0xAC00) - (글자 - 0xAC00) % 28 ) ) / 28 ) % 21
     *     종성 = (글자 - 0xAC00) % 28
     *
     *     [합치기 기본 공식]
     *     원문 = 0xAC00 + 28 * 21 * (초성의 index) + 28 * (중성의 index) + (종성의 index)
     *
     *     각 index 정보는 CHOSUNG, JUNGSUNG, JONGSUNG char[]에 정의한 index 입니다.
     *     하지만 아래 코드에서는 원문이 필요 없기 때문에 합치기 위한 로직은 포함 되어 있지 않습니다.
     * @param source
     * @return
     */
    public abstract String tokenize(String source);

}
