package com.volka.searchengine.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 쓰레드풀 Bean
 */
@Configuration
public class ThreadPoolConfig {
    
    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(100);
    }
}
