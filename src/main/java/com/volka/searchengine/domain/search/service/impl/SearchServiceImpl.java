package com.volka.searchengine.domain.search.service.impl;

import com.volka.searchengine.core.exception.SearchException;
import com.volka.searchengine.core.exception.ServiceException;
import com.volka.searchengine.domain.search.service.SearchService;
import com.volka.searchengine.dto.Search;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.util.List;

/**
 * 검색 서비스 구현
 *
 * @author volka
 */
@Slf4j
@Service
public class SearchServiceImpl implements SearchService {
    @Override
    public List<Search> searchWord(String word) {
        try {

        } catch (ServiceException e) {

        } catch (Exception e) {

        }
    }
}
