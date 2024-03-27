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

    /**
     * 가입 시 초기화 색인
     * @param param
     * @return
     */
    ResponseCode initialize(IndexingRequest.Init param);

    /**
     * 색인 업데이트
     * @param domain
     * @param param
     * @return
     */
    ResponseCode updateIndex(SEARCH_DOMAIN domain, IndexingRequest.SaveAcit param);

    /**
     * 랭킹 업데이트
     * @param domain
     * @param orgId
     * @param keyCode
     * @return
     */
    ResponseCode updateRank(SEARCH_DOMAIN domain, String orgId, String keyCode);
}
