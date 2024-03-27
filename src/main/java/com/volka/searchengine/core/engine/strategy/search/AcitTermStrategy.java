package com.volka.searchengine.core.engine.strategy.search;

import com.volka.searchengine.core.engine.tokenizer.JamoTokenizer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.PrefixQuery;
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

    private final JamoTokenizer tokenizer;
    
    @Override
    public BooleanQuery createQuery(String word) {

        PrefixQuery acitCdTermQuery = new PrefixQuery(new Term("acitCd", word));
        PrefixQuery acitNmTermQuery = tokenizer.isOnlyChosungOrEng(word) ? new PrefixQuery(new Term("chosung", word)) : new PrefixQuery(new Term("jamo", tokenizer.tokenize(word).getJamo()));
//        TermQuery acitNmTermQuery = new TermQuery(new Term("jamo", tokenizer.tokenize(word).getJamo()));

//        TermQuery acitNmTermQuery = new TermQuery(new Term("chosung", word));

//        TermRangeQuery acitCdTermQuery = new TermRangeQuery("acitCd", new BytesRef(word), null, true, false);
//        TermRangeQuery acitNmTermQuery = new TermRangeQuery("acitNm", new BytesRef(word), null, true, false);

        return new BooleanQuery.Builder()
                .add(new BooleanClause(acitCdTermQuery, BooleanClause.Occur.SHOULD))
                .add(new BooleanClause(acitNmTermQuery, BooleanClause.Occur.SHOULD))
                .build();

    }
}