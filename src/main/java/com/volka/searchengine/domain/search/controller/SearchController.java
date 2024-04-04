package com.volka.searchengine.domain.search.controller;

import com.volka.searchengine.core.annotation.SysId;
import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import com.volka.searchengine.core.engine.model.DocumentModel;
import com.volka.searchengine.domain.search.service.SearchService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@RequestMapping("/search/api/v1/search")
@RestController
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/{domain}/{orgId}")
    public List<DocumentModel> searchWord(
            @PathVariable(value = "domain") SEARCH_DOMAIN domain
            ,@PathVariable(value = "orgId") @SysId String orgId
            ,@RequestParam(value = "word") @NotBlank @NotNull @Size(max = 50) String word
    ) {
        return searchService.searchWord(domain, orgId, word);
    }
}
