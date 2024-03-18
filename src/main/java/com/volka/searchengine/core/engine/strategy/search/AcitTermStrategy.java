package com.volka.searchengine.core.engine.strategy.search;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TermQuery;
import org.springframework.stereotype.Component;

@Component
public class AcitTermStrategy implements TermStrategy {
    @Override
    public BooleanQuery createQuery(String word) {

        TermQuery acitCdTermQuery = new TermQuery(new Term("acitCd", word));
        TermQuery acitNmTermQuery = new TermQuery(new Term("acitNm", word));

        return new BooleanQuery.Builder()
                .add(new BooleanClause(acitCdTermQuery, BooleanClause.Occur.SHOULD))
                .add(new BooleanClause(acitNmTermQuery, BooleanClause.Occur.SHOULD))
                .build();
    }
}
