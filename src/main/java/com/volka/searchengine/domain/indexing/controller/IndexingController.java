package com.volka.searchengine.domain.indexing.controller;

import com.volka.searchengine.core.constant.ResponseCode;
import com.volka.searchengine.domain.indexing.service.IndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 검색 컨트롤러
 *
 * @author volka
 */
@Validated
@RequiredArgsConstructor
@RequestMapping("/index")
@RestController
public class IndexingController {

    private final IndexService indexService;

    @PatchMapping
    public ResponseCode indexing(String word) {
        return indexService.indexing(word);
    }
}
