package com.volka.searchengine.domain.indexing.service.impl;

import com.volka.searchengine.core.constant.ResponseCode;
import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import com.volka.searchengine.core.engine.SearchEngine;
import com.volka.searchengine.core.engine.model.Acit;
import com.volka.searchengine.core.engine.model.DocumentModel;
import com.volka.searchengine.core.engine.model.Trdp;
import com.volka.searchengine.core.engine.strategy.index.AcitIndexStrategy;
import com.volka.searchengine.core.engine.strategy.index.IndexStrategy;
import com.volka.searchengine.core.engine.strategy.index.TrdpIndexStrategy;
import com.volka.searchengine.core.exception.BizException;
import com.volka.searchengine.domain.indexing.service.IndexService;
import com.volka.searchengine.dto.IndexingRequest;
import jakarta.validation.ConstraintViolationException;
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
public class IndexServiceImpl implements IndexService {

    private final SearchEngine engine;

    @Override
    public <E extends DocumentModel> ResponseCode indexing(SEARCH_DOMAIN domain, String orgId, List<E> modelList) {
        try {
            engine.indexing(domain, orgId, createIndexStrategy(domain, modelList));
            return ResponseCode.SUCCESS;

        } catch (BizException e) {
            log.error("[EXCEPTION] indexing() :: {} : {}", e.getCode(), e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            log.error("[EXCEPTION] indexing() :: {} : {}", e.getLocalizedMessage(), e.toString());
            throw new BizException(ResponseCode.FAIL, e);
        }
    }

    @SuppressWarnings(value = "unchecked")
    private <E extends DocumentModel> IndexStrategy createIndexStrategy(SEARCH_DOMAIN domain, List<E> modelList) throws ConstraintViolationException, BizException, Exception {
        IndexStrategy indexStrategy = null;

        switch (domain) {
            case ACIT -> indexStrategy = new AcitIndexStrategy((List<Acit>) modelList);
            case TRDP -> indexStrategy = new TrdpIndexStrategy((List<Trdp>) modelList);
            default -> throw new BizException(ResponseCode.VALID_FAIL);
        }

        return indexStrategy;
    }


    /**
     * 첫 연동시 초기화
     *
     * @param param
     * @return
     */
    @Override
    public ResponseCode initialize(IndexingRequest.Init param) {
        try {
            engine.initializeIndexByDomain(SEARCH_DOMAIN.ACIT, param.getOrgId(), new AcitIndexStrategy(param.getAcitList()));
            engine.initializeIndexByDomain(SEARCH_DOMAIN.TRDP, param.getOrgId(), new TrdpIndexStrategy(param.getTrdpList()));

            return ResponseCode.SUCCESS;

        } catch (BizException e) {
            log.error("[EXCEPTION] initialize() :: {} : {}", e.getCode(), e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            log.error("[EXCEPTION] initialize() :: {} : {}", e.getLocalizedMessage(), e.toString());
            throw new BizException(ResponseCode.FAIL, e);
        }
    }

    @Override
    public <E extends DocumentModel> ResponseCode updateIndex(SEARCH_DOMAIN domain, String orgId, List<E> modelList) {
        try {
            engine.updateIndex(domain, orgId, createIndexStrategy(domain, modelList));
            return ResponseCode.SUCCESS;
        } catch (BizException e) {
            log.error("[EXCEPTION] updateIndex() :: {} : {}", e.getCode(), e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            log.error("[EXCEPTION] updateIndex() :: {} : {}", e.getLocalizedMessage(), e.toString());
            throw new BizException(ResponseCode.FAIL, e);
        }
    }

    @Override
    public ResponseCode updateRank(SEARCH_DOMAIN domain, String orgId, String keyCode) {
        try {
            engine.updateRank(domain, orgId, keyCode);
            return ResponseCode.SUCCESS;
        } catch (BizException e) {
            log.error("[EXCEPTION] updateRank() :: {} : {}", e.getCode(), e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            log.error("[EXCEPTION] updateRank() :: {} : {}", e.getLocalizedMessage(), e.toString());
            throw new BizException(ResponseCode.FAIL, e);
        }
    }

    @Override
    public ResponseCode deleteIndexById(SEARCH_DOMAIN domain, IndexingRequest.Delete param) {
        try {
            engine.deleteIndexById(domain, param.getOrgId(), param.getKeyList());
            return ResponseCode.SUCCESS;
        } catch (BizException e) {
            log.error("[EXCEPTION] deleteIndexById() :: {} : {}", e.getCode(), e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            log.error("[EXCEPTION] deleteIndexById() :: {} : {}", e.getLocalizedMessage(), e.toString());
            throw new BizException(ResponseCode.FAIL, e);
        }
    }
}
