//package com.volka.searchengine.core.engine.tokenizer;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import static com.volka.searchengine.core.engine.constant.KoreanTokenCode.JUNGSUNG;
//
///**
// * 중성 토크나이저
// *
// * @author volka
// */
//@Slf4j
//@Component
//public class JoongsungTokenizer extends AbstractJamoTokenizer {
//
//    @Override
//    public String tokenize(String source) {
//        StringBuilder joongsungBuilder = new StringBuilder();
//        int criteria;
//        char sourceChar;
//        char jungIdx;
//
//        for(int i = 0 ; i < source.length(); i++) {
//            sourceChar = source.charAt(i);
//
//            if(sourceChar >= 0xAC00) {
//                criteria = (sourceChar - 0xAC00);
//                jungIdx = (char)(((criteria - (criteria % 28)) / 28) % 21);
//
//                joongsungBuilder.append(JUNGSUNG.getCode()[jungIdx]);
//            } else {
//                if ( isPossibleCharacter(sourceChar) ) {
//                    joongsungBuilder.append(sourceChar);
//                }
//            }
//        }
//
//        return joongsungBuilder.toString();
//    }
//}
