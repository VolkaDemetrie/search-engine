package com.volka.searchengine.core.config;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.ko.KoreanAnalyzer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 검색엔진 설정
 *
 * @author volka
 */
@Configuration
public class SearchEngineConfig {


    @Bean
    public Analyzer analyzer() {
        return new KoreanAnalyzer();
    }

//    @Bean
//    public Document AcitDocument() {
////        Document acitDocument = new Document();
////        acitDocument.add();
//        return null;
//    }
}
