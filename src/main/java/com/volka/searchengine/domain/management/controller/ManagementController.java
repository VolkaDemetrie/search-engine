package com.volka.searchengine.domain.management.controller;

import com.volka.searchengine.core.constant.ResponseCode;
import com.volka.searchengine.domain.management.service.ManagementService;
import com.volka.searchengine.dto.ManagementRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/management")
@RestController
public class ManagementController {

    private final ManagementService managementService;

    /**
     * 기관별 계정과목 초기화
     * @param param
     * @return
     */
    @PostMapping("/acit/init")
    public ResponseCode initAcit(@RequestBody ManagementRequest.Acit param) {
        return managementService.initAcit(param);
    }

    @PatchMapping("/acit")
    public ResponseCode addAcit(@RequestBody ManagementRequest.Acit param) {
        return managementService.addAcit(param);
    }

    @PatchMapping("/acit/del")
    public ResponseCode delAcit(@RequestBody ManagementRequest.Acit param) {
        return managementService.delAcit(param);
    }

}
