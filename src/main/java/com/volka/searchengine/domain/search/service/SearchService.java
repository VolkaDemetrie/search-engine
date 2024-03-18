package com.volka.searchengine.domain.search.service;

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
     * @param word
     * @return
     */
    List<DocumentModel> searchWord(String orgId, String word);
}
