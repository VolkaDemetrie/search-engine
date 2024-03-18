package com.volka.searchengine.core.engine.strategy;


import com.volka.searchengine.core.engine.strategy.index.DomainIndexStrategy;
import com.volka.searchengine.core.exception.BizException;
import org.apache.lucene.search.BooleanQuery;
import org.springframework.stereotype.Component;

/**
 * 도메인 전략 컨텍스트
 *
 * @author volka
 */
@Component
public class IndexStrategyContext {

    public void addDocument(DomainIndexStrategy strategy) throws BizException, Exception {
        strategy.addDocument();
    }
}
