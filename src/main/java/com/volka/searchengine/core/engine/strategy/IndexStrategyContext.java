package com.volka.searchengine.core.engine.strategy;


import com.volka.searchengine.core.engine.strategy.index.IndexStrategy;
import com.volka.searchengine.core.exception.BizException;
import org.springframework.stereotype.Component;

/**
 * 도메인 전략 컨텍스트
 *
 * @author volka
 */
@Component
public class IndexStrategyContext {

    public void addDocument(IndexStrategy strategy) throws BizException, Exception {
        strategy.addDocument();
    }
}
