package com.volka.searchengine.domain.search.controller;

import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import com.volka.searchengine.core.engine.model.DocumentModel;
import com.volka.searchengine.domain.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 검색 컨트롤러
 *
 * @author volka
 */
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
@RestController
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/{domain}/{orgId}")
    public List<DocumentModel> searchWord(
            @PathVariable(value = "domain") SEARCH_DOMAIN domain
            ,@PathVariable(value = "orgId") String orgId
            ,@RequestParam(value = "word") String word
    ) {
        return searchService.searchWord(domain, orgId, word);
    }
}
