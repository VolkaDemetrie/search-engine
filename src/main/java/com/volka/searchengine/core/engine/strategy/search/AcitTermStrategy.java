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
 * 계정과목 텀 전략
 *
 * @author volka
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class AcitTermStrategy implements TermStrategy {

    private final ChosungTokenizer tokenizer;
    
    @Override
    public BooleanQuery createQuery(String word) {

        TermQuery acitCdTermQuery = new TermQuery(new Term("acitCd", word));
        TermQuery acitNmTermQuery = null;

        if (tokenizer.isPossibleCharacter(word.charAt(0))) {
            acitNmTermQuery = new TermQuery(new Term("chosung", tokenizer.tokenize(word)));
        } else {
            acitNmTermQuery = new TermQuery(new Term("acitNm", word));
        }

//        TermRangeQuery acitCdTermQuery = new TermRangeQuery("acitCd", new BytesRef(word), null, true, false);
//        TermRangeQuery acitNmTermQuery = new TermRangeQuery("acitNm", new BytesRef(word), null, true, false);

        return new BooleanQuery.Builder()
                .add(new BooleanClause(acitCdTermQuery, BooleanClause.Occur.SHOULD))
                .add(new BooleanClause(acitNmTermQuery, BooleanClause.Occur.SHOULD))
                .build();

    }
}