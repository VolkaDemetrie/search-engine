package com.volka.searchengine.domain.indexing.service;

import com.volka.searchengine.core.constant.ResponseCode;
import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import com.volka.searchengine.dto.IndexingRequest;

/**
 * 검색 서비스 인터페이스
 *
 * @author volka
 */
public interface IndexService {

    /**
     * 단어 검색
     * @param param
     * @return
     */
    ResponseCode indexingAcit(SEARCH_DOMAIN domain, IndexingRequest.SaveAcit param);

    ResponseCode initialize(IndexingRequest.Init param);

    ResponseCode updateIndex(SEARCH_DOMAIN domain, IndexingRequest.SaveAcit param);
}
