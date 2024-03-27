package com.volka.searchengine.core.engine.model;

import com.volka.searchengine.core.constant.SEARCH_DOMAIN;

/**
 * 색인화 문서 모델 도메인 인터페이스
 *
 * @author volka
 */
public abstract class DocumentModel {

    protected String chosnug;
    protected String jamo;

    public abstract SEARCH_DOMAIN getDomain();

}
