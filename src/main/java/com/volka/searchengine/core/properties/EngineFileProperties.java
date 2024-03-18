package com.volka.searchengine.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("engine.file")
public class EngineFileProperties {
    private String idxDir; //색인파일 위치
    private String originDir; //원천 데이터 위치
}
