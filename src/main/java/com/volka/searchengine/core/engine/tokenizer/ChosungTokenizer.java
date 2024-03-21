package com.volka.searchengine.core.engine.tokenizer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.volka.searchengine.core.engine.constant.KoreanTokenCode.CHOSUNG;

/**
 * 초성 토크나이저
 *
 * @author volka
 */
@Slf4j
@Component
public class ChosungTokenizer extends JamoTokenizer {

    @Override
    public String tokenize(String source) {
        StringBuilder chosungBuilder = new StringBuilder();
        int criteria;
        char sourceChar;
        char choIdx;

        for(int i = 0 ; i < source.length(); i++) {
            sourceChar = source.charAt(i);

            if(sourceChar >= 0xAC00) {
                criteria = (sourceChar - 0xAC00);
                choIdx = (char) (((criteria - (criteria % 28)) / 28) / 21);

                chosungBuilder.append(CHOSUNG.getCode()[choIdx]);
            } else {
                if (isPossibleCharacter(sourceChar)) {
                    chosungBuilder.append(sourceChar);
                }
            }
        }

        return chosungBuilder.toString();
    }
}
