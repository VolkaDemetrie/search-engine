package com.volka.searchengine.core.engine.strategy.search;

import com.volka.searchengine.core.engine.tokenizer.JamoTokenizer;
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
 * TODO : 입력 초성만인지 체크 후 초성 또는 자모로 검색 분기 추가 : 현재 영어 고려 X
 *
 * @author volka
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class TrdpTermStrategy implements TermStrategy {

    private final JamoTokenizer tokenizer;

    @Override
    public BooleanQuery createQuery(String word) {

        TermQuery trdpCdTermQuery = new TermQuery(new Term("trdpCd", word));
        TermQuery trdpBrnTermQuery = new TermQuery(new Term("trdpBrn", word));
        TermQuery trdpTnTermQuery = tokenizer.isOnlyChosungOrEng(word) ? new TermQuery(new Term("chosung", word)) : new TermQuery(new Term("jamo", tokenizer.tokenize(word).getJamo()));

        return new BooleanQuery.Builder()
                .add(new BooleanClause(trdpCdTermQuery, BooleanClause.Occur.SHOULD))
                .add(new BooleanClause(trdpBrnTermQuery, BooleanClause.Occur.SHOULD))
                .add(new BooleanClause(trdpTnTermQuery, BooleanClause.Occur.SHOULD))
                .build()
                ;
    }
}
