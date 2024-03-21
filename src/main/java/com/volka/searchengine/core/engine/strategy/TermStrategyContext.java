package com.volka.searchengine.core.engine.strategy;


import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import com.volka.searchengine.core.engine.strategy.search.AcitTermStrategy;
import com.volka.searchengine.core.engine.strategy.search.TrdpTermStrategy;
import com.volka.searchengine.core.engine.tokenizer.ChosungTokenizer;
import com.volka.searchengine.core.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.search.BooleanQuery;
import org.springframework.stereotype.Component;

/**
 * 도메인 전략 컨텍스트
 *
 * @author volka
 */
@RequiredArgsConstructor
@Component
public class TermStrategyContext {

    private final AcitTermStrategy acitTermStrategy;
    private final TrdpTermStrategy trdpTermStrategy;

    public BooleanQuery createQuery(String word, SEARCH_DOMAIN domain) throws BizException, Exception {
        return switch (domain) {
            case ACIT -> acitTermStrategy.createQuery(word);
            case TRDP -> trdpTermStrategy.createQuery(word);
        };
    }
}
