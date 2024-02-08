package com.volka.searchengine.domain.search.service.impl;

import com.volka.searchengine.core.exception.ServiceException;
import com.volka.searchengine.domain.search.entity.Search;
import com.volka.searchengine.domain.search.service.SearchService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public List<Search> searchWord(String word) {
        try {
            Search search = new Search();
            search
        } catch (ServiceException e) {

        } catch (Exception e) {

        }
    }
}
