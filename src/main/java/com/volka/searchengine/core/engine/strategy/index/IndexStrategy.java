package com.volka.searchengine.core.engine.strategy.index;

import com.volka.searchengine.core.engine.model.DocumentModel;
import com.volka.searchengine.core.exception.BizException;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.ko.KoreanAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.index.IndexWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * 도메인 전략 인터페이스
 *
 * @author volka
 */
public abstract class IndexStrategy {

    public abstract void addDocument() throws BizException, Exception;

    protected String[] extractJamo(String word) throws IOException {
        List<String> jamoList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        TokenStream tokenStream = new KoreanAnalyzer().tokenStream("dummy", word);
        tokenStream.reset();

        CharTermAttribute termAttribute = tokenStream.addAttribute(CharTermAttribute.class);

        while (tokenStream.incrementToken()) {
            String term = termAttribute.toString();
            for (char c : term.toCharArray()) {
                sb.append(c);
                jamoList.add(sb.toString());
            }
            sb.setLength(0);
        }

        tokenStream.close();

        return jamoList.toArray(new String[0]);
    }
}
