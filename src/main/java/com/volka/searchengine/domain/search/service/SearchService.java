package com.volka.searchengine.domain.search.service;

import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import com.volka.searchengine.core.engine.model.DocumentModel;

import java.util.List;

/**
 * 검색 서비스 인터페이스
 *
 * @author volka
 */
public interface SearchService {

    /**
     * 단어 검색
     *
     * @param domain
     * @param word
     * @return
     */
    List<DocumentModel> searchWord(SEARCH_DOMAIN domain, String orgId, String word);
}
