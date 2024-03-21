package com.volka.searchengine.domain.indexing.controller;

import com.volka.searchengine.core.constant.ResponseCode;
import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import com.volka.searchengine.core.dto.ResponseDTO;
import com.volka.searchengine.domain.indexing.service.IndexService;
import com.volka.searchengine.dto.IndexingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 색인 컨트롤러
 *
 * @author volka
 */
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/index")
@RestController
public class IndexingController {

    private final IndexService indexService;

    @PostMapping("/{domain}")
    public ResponseDTO<ResponseCode> indexing(
            @PathVariable("domain") SEARCH_DOMAIN domain
            ,@Valid @RequestBody IndexingRequest.SaveAcit param
    ) {
        return ResponseDTO.response(indexService.indexingAcit(domain, param));
    }


    @PostMapping("/init")
    public ResponseDTO<ResponseCode> initialize(@Valid @RequestBody IndexingRequest.Init param) {
        return ResponseDTO.response(indexService.initialize(param));
    }
}
