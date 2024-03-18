package com.volka.searchengine.core.engine.strategy.search;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TermQuery;
import org.springframework.stereotype.Component;

@Component
public class TrdpTermStrategy implements TermStrategy {
    @Override
    public BooleanQuery createQuery(String word) {

        TermQuery trdpCdTermQuery = new TermQuery(new Term("trdpCd", word));
        TermQuery trdpTnTermQuery = new TermQuery(new Term("trdpTn", word));
        TermQuery trdpBrnTermQuery = new TermQuery(new Term("trdpBrn", word));

        return new BooleanQuery.Builder()
                .add(new BooleanClause(trdpCdTermQuery, BooleanClause.Occur.SHOULD))
                .add(new BooleanClause(trdpTnTermQuery, BooleanClause.Occur.SHOULD))
                .add(new BooleanClause(trdpBrnTermQuery, BooleanClause.Occur.SHOULD))
                .build();
    }
}
