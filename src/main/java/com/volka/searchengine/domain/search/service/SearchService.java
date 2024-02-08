package com.volka.searchengine.domain.search.service;

import com.volka.searchengine.domain.search.entity.Search;

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
    List<Search> searchWord(String word);
}
