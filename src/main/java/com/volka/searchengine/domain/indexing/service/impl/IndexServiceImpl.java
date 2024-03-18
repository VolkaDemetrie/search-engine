package com.volka.searchengine.domain.indexing.service.impl;

import com.volka.searchengine.core.constant.ResponseCode;
import com.volka.searchengine.core.exception.BizException;
import com.volka.searchengine.domain.indexing.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 검색 서비스 구현
 *
 * @author volka
 */
@Slf4j
@Service
public class IndexServiceImpl implements IndexService {


    @Override
    public ResponseCode indexing(String word) {
        try {
            return null;
        } catch (BizException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
