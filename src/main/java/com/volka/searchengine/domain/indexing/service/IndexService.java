package com.volka.searchengine.domain.indexing.service;

import com.volka.searchengine.core.constant.ResponseCode;
import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import com.volka.searchengine.core.engine.model.Acit;
import com.volka.searchengine.core.engine.model.DocumentModel;
import com.volka.searchengine.dto.IndexingRequest;

import java.util.List;

/**
 * 검색 서비스 인터페이스
 *
 * @author volka
 */
public interface IndexService {

    /**
     * 색인
     * @param domain
     * @param orgId
     * @param modelList
     * @param <E>
     * @return
     */
    <E extends DocumentModel> ResponseCode indexing(SEARCH_DOMAIN domain, String orgId, List<E> modelList);

    /**
     * 가입 시 초기화 색인
     * @param param
     * @return
     */
    ResponseCode initialize(IndexingRequest.Init param);

    /**
     * 색인 업데이트
     * @param domain
     * @param orgId
     * @param modelList
     * @param <E>
     * @return
     */
    <E extends DocumentModel> ResponseCode updateIndex(SEARCH_DOMAIN domain, String orgId, List<E> modelList);

    /**
     * 랭킹 업데이트
     * @param domain
     * @param orgId
     * @param keyCode
     * @return
     */
    ResponseCode updateRank(SEARCH_DOMAIN domain, String orgId, String keyCode);

    /**
     * 색인 제거
     * @param domain
     * @param param
     * @return
     */
    ResponseCode deleteIndex(SEARCH_DOMAIN domain, IndexingRequest.Delete param);
}
