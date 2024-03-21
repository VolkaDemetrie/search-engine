package com.volka.searchengine.core.engine.tokenizer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.volka.searchengine.core.engine.constant.KoreanTokenCode.JUNGSUNG;

/**
 * 중성 토크나이저
 *
 * @author volka
 */
@Slf4j
@Component
public class KorToEngTokenizer extends JamoTokenizer {

    private static final String[] CHOSUNG_EN = { "r", "R", "s", "e", "E", "f", "a", "q", "Q", "t", "T", "d", "w", "W", "c", "z", "x", "v", "g" };
    private static final String[] JUNGSUNG_EN = { "k", "o", "i", "O", "j", "p", "u", "P", "h", "hk", "ho", "hl", "y", "n", "nj", "np", "nl", "b", "m", "ml", "l" };
    private static final String[] JONGSUNG_EN = { "", "r", "R", "rt", "s", "sw", "sg", "e", "f", "fr", "fa", "fq", "ft", "fx", "fv", "fg", "a", "q", "qt", "t", "T", "d", "w", "c", "z", "x", "v", "g" };
    private static final String[] LETTER_EN = { "r", "R", "rt", "s", "sw", "sg", "e","E" ,"f", "fr", "fa", "fq", "ft", "fx", "fv", "fg", "a", "q","Q", "qt", "t", "T", "d", "w", "W", "c", "z", "x", "v", "g" };

    @Override
    public String tokenize(String source) {
        StringBuilder englishBuilder = new StringBuilder();
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
                englishBuilder.append(CHOSUNG_EN[choIdx]);
                englishBuilder.append(JUNGSUNG_EN[jungIdx]);

                if (jongIdx != 0x0000) {
                    englishBuilder.append(JONGSUNG_EN[jongIdx]);
                }
            } else {
                if (isPossibleCharacter(sourceChar) ) {
                    englishBuilder.append(sourceChar);
                }
            }
        }

        return englishBuilder.toString();
    }
}
