package com.volka.searchengine;

import com.volka.searchengine.core.engine.properties.EngineProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@ConfigurationPropertiesScan
@SpringBootApplication
public class SearchEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchEngineApplication.class, args);
    }


    @Bean
    @ConfigurationProperties(prefix = "engine")
    EngineProperties createEngineProperty() {
        return new EngineProperties();
    }
}
