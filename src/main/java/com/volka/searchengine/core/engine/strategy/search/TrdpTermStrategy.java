package com.volka.searchengine.core.engine.strategy.search;

import com.volka.searchengine.core.engine.tokenizer.ChosungTokenizer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TermQuery;
import org.springframework.stereotype.Component;

/**
 * 거래처 텀 전략
 *
 * @author volka
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class TrdpTermStrategy implements TermStrategy {

    private final ChosungTokenizer tokenizer;

    @Override
    public BooleanQuery createQuery(String word) {

        TermQuery trdpCdTermQuery = new TermQuery(new Term("trdpCd", word));
        TermQuery trdpBrnTermQuery = new TermQuery(new Term("trdpBrn", word));
        TermQuery trdpTnTermQuery = null;

        if (tokenizer.isPossibleCharacter(word.charAt(0))) {
            trdpTnTermQuery = new TermQuery(new Term("chosung", tokenizer.tokenize(word)));
        } else {
            trdpTnTermQuery = new TermQuery(new Term("trdpTn", word));
        }

        return new BooleanQuery.Builder()
                .add(new BooleanClause(trdpCdTermQuery, BooleanClause.Occur.SHOULD))
                .add(new BooleanClause(trdpTnTermQuery, BooleanClause.Occur.SHOULD))
                .add(new BooleanClause(trdpBrnTermQuery, BooleanClause.Occur.SHOULD))
                .build();
    }
}
