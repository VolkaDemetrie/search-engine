package com.volka.searchengine.domain.search.service.impl;

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


    private final SearchEngine searchEngine;

    @Override
    public List<DocumentModel> searchWord(String orgId, String word) {
        try {
            return null;
        } catch (BizException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
