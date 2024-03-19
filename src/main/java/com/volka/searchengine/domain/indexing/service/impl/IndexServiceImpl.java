package com.volka.searchengine.domain.indexing.service.impl;

import com.volka.searchengine.core.constant.ResponseCode;
import com.volka.searchengine.core.engine.SearchEngine;
import com.volka.searchengine.core.exception.BizException;
import com.volka.searchengine.domain.indexing.service.IndexService;
import com.volka.searchengine.dto.IndexingRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public ResponseCode indexing(IndexingRequest.Save param) {
        try {
            switch (param.getDomain()) {
                case ACIT:
                    if (param.getAcitList() != null && !param.getAcitList().isEmpty()) {
                        engine.indexing(param.getOrgId(), param.getAcitList());
                        return ResponseCode.SUCCESS;
                    }

                    return ResponseCode.VALID_FAIL;

                case TRDP:
                    if (param.getTrdpList() != null && !param.getTrdpList().isEmpty()) {
                        engine.indexing(param.getOrgId(), param.getTrdpList());
                        return ResponseCode.SUCCESS;
                    }

                    return ResponseCode.VALID_FAIL;

                default: return ResponseCode.VALID_FAIL;
            }

        } catch (BizException e) {
            log.error("[EXCEPTION] indexing() :: {} : {}", e.getCode(), e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            log.error("[EXCEPTION] indexing() :: {} : {}", e.getLocalizedMessage(), e.toString());
            throw new BizException(ResponseCode.FAIL, e);
        }
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
            engine.indexing(param.getOrgId(), param.getAcitList());
            engine.indexing(param.getOrgId(), param.getTrdpList());

            return ResponseCode.SUCCESS;

        } catch (BizException e) {
            log.error("[EXCEPTION] initialize() :: {} : {}", e.getCode(), e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            log.error("[EXCEPTION] initialize() :: {} : {}", e.getLocalizedMessage(), e.toString());
            throw new BizException(ResponseCode.FAIL, e);
        }
    }
}
