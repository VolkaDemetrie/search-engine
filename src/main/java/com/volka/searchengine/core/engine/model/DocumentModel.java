package com.volka.searchengine.core.engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.volka.searchengine.core.constant.SEARCH_DOMAIN;

/**
 * 색인화 문서 모델 도메인 인터페이스
 *
 * @author volka
 */
public abstract class DocumentModel {

    @JsonIgnore
    protected String chosnug;
    @JsonIgnore
    protected String jamo;

    public abstract SEARCH_DOMAIN getDomain();

}
