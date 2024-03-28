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
 * 계정과목 대분류 한정 노출 텀 전략
 *
 * TODO 조건별 검색 제한 전략 구현 필요 20240328 volka
 *
 * @author volka
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class AcitWithClsfTermStrategy implements TermStrategy {

    private final JamoTokenizer tokenizer;
    
    @Override
    public BooleanQuery createQuery(String word) {

        PrefixQuery acitCdTermQuery = new PrefixQuery(new Term("acitCd", word));
        PrefixQuery acitNmJamoTermQuery = tokenizer.isOnlyChosungOrEng(word) ? new PrefixQuery(new Term("chosung", word)) : new PrefixQuery(new Term("jamo", tokenizer.tokenize(word).getJamo()));
        TermQuery acitNmTermQuery = new TermQuery(new Term("acitNm", word));
//        TermQuery acitClsfCdTermQuery = new Term;

//        QueryParser parser = new QueryParser()

//        TermQuery acitNmTermQuery = new TermQuery(new Term("chosung", word));

//        TermRangeQuery acitCdTermQuery = new TermRangeQuery("acitCd", new BytesRef(word), null, true, false);
//        TermRangeQuery acitNmTermQuery = new TermRangeQuery("acitNm", new BytesRef(word), null, true, false);

        return new BooleanQuery.Builder()
                .add(new BooleanClause(acitCdTermQuery, BooleanClause.Occur.SHOULD))
                .add(new BooleanClause(acitNmJamoTermQuery, BooleanClause.Occur.SHOULD))
                .add(new BooleanClause(acitNmTermQuery, BooleanClause.Occur.SHOULD))
                .build();

    }
}