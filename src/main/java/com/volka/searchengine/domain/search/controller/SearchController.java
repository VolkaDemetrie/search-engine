package com.volka.searchengine.domain.search.controller;

import com.volka.searchengine.core.annotation.validation.SysId;
import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import com.volka.searchengine.core.dto.ResponseDTO;
import com.volka.searchengine.core.engine.model.DocumentModel;
import com.volka.searchengine.domain.search.service.SearchService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "검색 API", description = "검색 API")
@Validated
@RequiredArgsConstructor
@RequestMapping("/search/api/v1/search")
@RestController
public class SearchController {

    private final SearchService searchService;

    @Tag(name = "검색 API", description = "검색 API")
    @GetMapping("/{domain}/{orgId}")
    public ResponseDTO<List<DocumentModel>> searchWord(
            @Parameter(name = "domain", description = "검색 도메인", in = ParameterIn.PATH, required = true) @PathVariable(value = "domain") SEARCH_DOMAIN domain
            ,@Parameter(name = "orgId", description = "기관 아이디", in = ParameterIn.PATH, required = true) @PathVariable(value = "orgId") @SysId String orgId
            ,@Parameter(name = "word", description = "검색어", in = ParameterIn.QUERY, required = true) @RequestParam(value = "word") @NotBlank @NotNull @Size(max = 50) String word
    ) {
        return ResponseDTO.response(searchService.searchWord(domain, orgId, word));
    }
}
