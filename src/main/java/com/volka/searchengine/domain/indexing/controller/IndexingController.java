package com.volka.searchengine.domain.indexing.controller;

import com.volka.searchengine.core.constant.ResponseCode;
import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import com.volka.searchengine.core.dto.ResponseDTO;
import com.volka.searchengine.domain.indexing.service.IndexService;
import com.volka.searchengine.dto.IndexingRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 색인 컨트롤러
 *
 * @author volka
 */
@Tag(name = "색인 API", description = "색인 API")
@Validated
@RequiredArgsConstructor
@RequestMapping("/search/api/v1/index")
@RestController
public class IndexingController {

    private final IndexService indexService;

    @Tag(name = "색인 API", description = "색인 API")
    @Operation(summary = "계정과목 색인 추가", description = "계정과목 색인 추가")
    @PostMapping("/ACIT")
    public ResponseDTO<ResponseCode> indexAcit(@Valid @RequestBody IndexingRequest.SaveAcit param) {
        return ResponseDTO.response(indexService.indexing(SEARCH_DOMAIN.ACIT, param.getOrgId(), param.getAcitList()));
    }

    @Tag(name = "색인 API", description = "색인 API")
    @Operation(summary = "거래처 색인 추가", description = "거래처 색인 추가")
    @PostMapping("/TRDP")
    public ResponseDTO<ResponseCode> indexTrdp(@Valid @RequestBody IndexingRequest.SaveTrdp param) {
        return ResponseDTO.response(indexService.indexing(SEARCH_DOMAIN.TRDP, param.getOrgId(), param.getTrdpList()));
    }

    @Tag(name = "색인 API", description = "색인 API")
    @Operation(summary = "계정과목 색인 수정", description = "계정과목 색인 수정")
    @PatchMapping("/ACIT")
    public ResponseDTO<ResponseCode> updateAcitIndex(@Valid @RequestBody IndexingRequest.SaveAcit param) {
        return ResponseDTO.response(indexService.updateIndex(SEARCH_DOMAIN.ACIT, param.getOrgId(), param.getAcitList()));
    }

    @Tag(name = "색인 API", description = "색인 API")
    @Operation(summary = "거래처 색인 수정", description = "거래처 색인 수정")
    @PatchMapping("/TRDP")
    public ResponseDTO<ResponseCode> updateTrdpIndex(@Valid @RequestBody IndexingRequest.SaveTrdp param) {
        return ResponseDTO.response(indexService.updateIndex(SEARCH_DOMAIN.TRDP, param.getOrgId(), param.getTrdpList()));
    }

    @Tag(name = "색인 API", description = "색인 API")
    @PutMapping("/del/{domain}")
    public ResponseDTO<ResponseCode> deleteIndex(
            @PathVariable("domain") SEARCH_DOMAIN domain
            ,@Valid @RequestBody IndexingRequest.Delete param
    ) {
        return ResponseDTO.response(indexService.deleteIndexById(domain, param));
    }


    @Tag(name = "색인 API", description = "색인 API")
    @Operation(summary = "색인 초기화", description = "색인 초기화")
    @PostMapping("/init")
    public ResponseDTO<ResponseCode> initialize(@Valid @RequestBody IndexingRequest.Init param) {
        return ResponseDTO.response(indexService.initialize(param));
    }
}
