package com.volka.searchengine.core.config;

import org.apache.lucene.analysis.ko.KoreanAnalyzer;
import org.apache.lucene.store.LockFactory;
import org.apache.lucene.store.SingleInstanceLockFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearchEngineConfig {

    @Bean
    public KoreanAnalyzer koreanAnalyzer() {
        return new KoreanAnalyzer();
    }

    /**
     * TODO : 추후 색인 / 검색 인스턴스 분리 또는 서버 내 인스턴스 여러개가 될 경우 LockFactory 바꿔야함 volka
     * @return
     */
    @Bean
    public LockFactory lockFactory() {
        return new SingleInstanceLockFactory();
    }
}
