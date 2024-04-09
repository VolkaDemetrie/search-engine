package com.volka.searchengine.core.engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 색인화 문서 모델 도메인 인터페이스
 *
 * @author volka
 */
public abstract class DocumentModel {

    @Schema(hidden = true)
    @JsonIgnore
    protected String chosnug;
    @Schema(hidden = true)
    @JsonIgnore
    protected String jamo;

//    protected static DocumentModel of(Document document);

//    public abstract SEARCH_DOMAIN getDomain();

}
