package com.volka.searchengine.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("engine")
public class EngineProperties {
    private EngineFileProperties file;
}

