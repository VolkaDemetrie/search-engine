package com.volka.searchengine.core.engine.config;

import org.apache.lucene.analysis.ko.KoreanAnalyzer;
import org.apache.lucene.index.IndexWriterConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearchEngineConfig {

    @Bean
    public KoreanAnalyzer koreanAnalyzer() {
        return new KoreanAnalyzer();
    }

    @Bean
    public IndexWriterConfig koreanIndexWriterConfig() {
        return new IndexWriterConfig(koreanAnalyzer());
    }
}
