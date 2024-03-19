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

}
