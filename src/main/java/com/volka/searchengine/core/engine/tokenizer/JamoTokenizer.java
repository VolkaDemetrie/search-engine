package com.volka.searchengine.core.engine.tokenizer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.volka.searchengine.core.engine.constant.KoreanTokenCode.*;

/**
 * 자모 토크나이저
 *
 * @author volka
 */
@Slf4j
@Component
public class JamoTokenizer extends AbstractJamoTokenizer {
    @Override
    public JamoToken tokenize(String source) {
        StringBuilder chosungBuilder = new StringBuilder();
        StringBuilder jamoBuilder = new StringBuilder();
        int criteria;
        char sourceChar;

        char choIdx;
        char joongIdx;
        char jongIdx;

        for (int i = 0; i < source.length(); i++) {
            sourceChar = source.charAt(i);

            if (sourceChar >= 0xAC00) {
                criteria = (sourceChar - 0xAC00);
                choIdx = (char) (((criteria - (criteria % 28)) / 28) / 21);
                joongIdx = (char) (((criteria - (criteria % 28)) / 28) % 21);
                jongIdx = (char) (criteria % 28);

                chosungBuilder.append(CHOSUNG.getCode()[choIdx]);
                jamoBuilder.append(CHOSUNG.getCode()[choIdx]);
                jamoBuilder.append(JOONGSUNG.getCode()[joongIdx]);

                if (jongIdx != 0x0000) {
                    jamoBuilder.append(JONGSUNG.getCode()[jongIdx]);
                }

            } else {
                if (isPossibleCharacter(sourceChar)) jamoBuilder.append(sourceChar);
            }
        }

        return new JamoToken(chosungBuilder.toString(), jamoBuilder.toString());
    }
}
