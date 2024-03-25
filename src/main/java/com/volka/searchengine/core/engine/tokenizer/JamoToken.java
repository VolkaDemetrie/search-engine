package com.volka.searchengine.core.engine.tokenizer;


import lombok.Data;
import lombok.ToString;

/**
 * 자모분리 결과 토큰
 */
@ToString
@Data
public class JamoToken {

    public JamoToken() {
        super();
    }

    public JamoToken(String chosung, String jamo) {
        this.chosung = chosung;
        this.jamo = jamo;
    }

    private String chosung;
    private String jamo;
}
