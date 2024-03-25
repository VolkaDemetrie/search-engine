//package com.volka.searchengine.core.engine.tokenizer;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import static com.volka.searchengine.core.engine.constant.KoreanTokenCode.JONGSUNG;
//
///**
// * 종성 토크나이저
// *
// * @author volka
// */
//@Slf4j
//@Component
//public class JongsungTokenizer extends AbstractJamoTokenizer {
//
//    @Override
//    public String tokenize(String source) {
//        StringBuilder jongsungBuilder = new StringBuilder();
//        char sourceChar;
//        char jongIdx;
//
//        for (int i = 0; i < source.length(); i++) {
//            sourceChar = source.charAt(i);
//
//            if (sourceChar >= 0xAC00) {
//                jongIdx = (char) ((sourceChar - 0xAC00) % 28);
//
//                jongsungBuilder.append(JONGSUNG.getCode()[jongIdx]);
//            } else {
//                if (isPossibleCharacter(sourceChar)) {
//                    jongsungBuilder.append(sourceChar);
//                }
//            }
//        }
//
//        return jongsungBuilder.toString();
//    }
//}
