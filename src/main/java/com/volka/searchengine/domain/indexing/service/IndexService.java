package com.volka.searchengine.domain.indexing.service;

import com.volka.searchengine.core.constant.ResponseCode;

/**
 * 검색 서비스 인터페이스
 *
 * @author volka
 */
public interface IndexService {

    /**
     * 단어 검색
     * @param word
     * @return
     */
    ResponseCode indexing(String word);
}
