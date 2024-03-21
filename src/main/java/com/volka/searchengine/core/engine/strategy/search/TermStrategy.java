package com.volka.searchengine.core.engine.strategy.search;

import com.volka.searchengine.core.exception.BizException;
import org.apache.lucene.search.BooleanQuery;

public interface TermStrategy {

    BooleanQuery createQuery(String word) throws BizException, Exception;
}
