package com.volka.searchengine.core.engine.tokenizer;


import com.volka.searchengine.core.engine.constant.KoreanTokenCode;
import org.springframework.stereotype.Component;

import static com.volka.searchengine.core.engine.constant.KoreanTokenCode.*;

/**
 * 자모 분리 Tokenizer
 *
 * @author volka
 */
@Component
public class JamoTokenizer {

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

    private static final String[] CHOSUNG_EN = { "r", "R", "s", "e", "E", "f", "a", "q", "Q", "t", "T", "d", "w", "W", "c", "z", "x", "v", "g" };
    private static final String[] JUNGSUNG_EN = { "k", "o", "i", "O", "j", "p", "u", "P", "h", "hk", "ho", "hl", "y", "n", "nj", "np", "nl", "b", "m", "ml", "l" };
    private static final String[] JONGSUNG_EN = { "", "r", "R", "rt", "s", "sw", "sg", "e", "f", "fr", "fa", "fq", "ft", "fx", "fv", "fg", "a", "q", "qt", "t", "T", "d", "w", "c", "z", "x", "v", "g" };
    private static final String[] LETTER_EN = { "r", "R", "rt", "s", "sw", "sg", "e","E" ,"f", "fr", "fa", "fq", "ft", "fx", "fv", "fg", "a", "q","Q", "qt", "t", "T", "d", "w", "W", "c", "z", "x", "v", "g" };



    private static boolean isPossibleCharacter(char c){
        if ((   (c >= NUMBER_BEGIN_UNICODE && c <= NUMBER_END_UNICODE)
                || (c >= ENGLISH_UPPER_BEGIN_UNICODE && c <= ENGLISH_UPPER_END_UNICODE)
                || (c >= ENGLISH_LOWER_BEGIN_UNICODE && c <= ENGLISH_LOWER_END_UNICODE)
                || (c >= HANGUEL_BEGIN_UNICODE && c <= HANGUEL_END_UNICODE)
                || (c >= CHOSUNG_BEGIN_UNICODE && c <= CHOSUNG_END_UNICODE))
        ){
            return true;
        }else{
            return false;
        }
    }


    /*
    [분리 기본 공식]
    초성 = ( ( (글자 - 0xAC00) - (글자 - 0xAC00) % 28 ) ) / 28 ) / 21
    중성 = ( ( (글자 - 0xAC00) - (글자 - 0xAC00) % 28 ) ) / 28 ) % 21
    종성 = (글자 - 0xAC00) % 28

    [합치기 기본 공식]
    원문 = 0xAC00 + 28 * 21 * (초성의 index) + 28 * (중성의 index) + (종성의 index)

    각 index 정보는 CHOSUNG, JUNGSUNG, JONGSUNG char[]에 정의한 index 입니다.
    하지만 아래 코드에서는 원문이 필요 없기 때문에 합치기 위한 로직은 포함 되어 있지 않습니다.
    */
    public String tokenizer(String source, KoreanTokenCode tokenCode) {
        String jamo = null;

        switch (tokenCode) {
            case CHOSUNG:
                jamo = chosungTokenizer(source);
                break;
            case JUNGSUNG:
                jamo = jungsungTokenizer(source);
                break;
            case JONGSUNG:
                jamo = jongsungTokenizer(source);
                break;
//            case KORTOENG:
//                jamo = convertKoreanToEnglish(source);
//                break;
            default:
                jamo = chosungTokenizer(source);
        }

        return jamo;
    }

    public String chosungTokenizer(String source) {
        String chosung = "";
        int criteria;
        char sourceChar;
        char choIdx;

        for(int i = 0 ; i < source.length(); i++) {
            sourceChar = source.charAt(i);

            if(sourceChar >= 0xAC00) {
                criteria = (sourceChar - 0xAC00);
                choIdx = (char)(((criteria - (criteria%28))/28)/21);

                chosung = chosung + CHOSUNG.getCode()[choIdx];
            } else {
                if ( isPossibleCharacter(sourceChar) ) {
                    chosung = chosung + sourceChar;
                }
            }
        }

        return chosung;
    }

    public String jungsungTokenizer(String source) {
        String jungsung = "";
        int criteria;
        char sourceChar;
        char jungIdx;

        for(int i = 0 ; i < source.length(); i++) {
            sourceChar = source.charAt(i);

            if(sourceChar >= 0xAC00) {
                criteria = (sourceChar - 0xAC00);
                jungIdx = (char)(((criteria - (criteria%28))/28)%21);

                jungsung = jungsung + JUNGSUNG.getCode()[jungIdx];
            } else {
                if ( isPossibleCharacter(sourceChar) ) {
                    jungsung = jungsung + sourceChar;
                }
            }
        }

        return jungsung;
    }

    public String jongsungTokenizer(String source) {
        String jongsung = "";
        char sourceChar;
        char jongIdx;

        for(int i = 0 ; i < source.length(); i++) {
            sourceChar = source.charAt(i);

            if(sourceChar >= 0xAC00) {
                jongIdx = (char)((sourceChar - 0xAC00)%28);

                jongsung = jongsung + JONGSUNG.getCode()[jongIdx];
            } else {
                if (isPossibleCharacter(sourceChar) ) {
                    jongsung = jongsung + sourceChar;
                }
            }
        }

        return jongsung;
    }

    public String convertKoreanToEnglish(String source) {
        String english = "";
        char sourceChar;
        int choIdx;
        int jungIdx;
        int jongIdx;
        int criteria;

        for(int i = 0 ; i < source.length(); i++) {
            sourceChar = source.charAt(i);
            criteria = sourceChar - 0xAC00;
            choIdx = criteria / (21 * 28);
            jungIdx = criteria % (21 * 28) / 28;
            jongIdx = criteria % (21 * 28) % 28;

            if(sourceChar >= 0xAC00) {
                english = english + CHOSUNG_EN[choIdx] + JUNGSUNG_EN[jungIdx];

                if (jongIdx != 0x0000) {
                    english =  english + JONGSUNG_EN[jongIdx];
                }
            } else {
                if (isPossibleCharacter(sourceChar) ) {
                    english = english + sourceChar;
                }
            }
        }

        return english;
    }
}
