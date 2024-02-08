package com.volka.searchengine.domain.search.controller;

import com.volka.searchengine.domain.search.entity.Search;
import com.volka.searchengine.domain.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 검색 컨트롤러
 *
 * @author volka
 */
@Validated
@RequiredArgsConstructor
@RequestMapping("/search")
@RestController
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/{searchWord}")
    public List<Search> searchWord(
            @PathVariable(value = "word") String word
    ) {
        return searchService.searchWord(word);
    }
}
