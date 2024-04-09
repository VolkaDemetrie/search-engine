package com.volka.searchengine.domain.search.service.impl;

import com.volka.searchengine.core.constant.ResponseCode;
import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import com.volka.searchengine.core.engine.SearchEngine;
import com.volka.searchengine.core.engine.model.DocumentModel;
import com.volka.searchengine.core.exception.BizException;
import com.volka.searchengine.domain.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 검색 서비스 구현
 *
 * @author volka
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SearchServiceImpl implements SearchService {

    private final SearchEngine engine;
//    private final CacheManager cacheManager;

    @Override
    public List<DocumentModel> searchWord(SEARCH_DOMAIN domain, String orgId, String word) {
        try {
            if (word == null || word.isBlank()) throw new BizException("SR0001"); //검색어가 존재하지 않습니다
            return engine.search(orgId, word, domain);
        } catch (BizException e) {
            log.error("[EXCEPTION] searchWord() :: {} : {}", e.getCode(), e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            log.error("[EXCEPTION] searchWord() :: {} : {}", e.getLocalizedMessage(), e.toString());
            throw new BizException(ResponseCode.FAIL, e);
        }
    }
}
