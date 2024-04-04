package com.volka.searchengine.domain.csv.controller;

import com.volka.searchengine.core.constant.ResponseCode;
import com.volka.searchengine.domain.csv.service.CsvService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/search/api/v1/search/csv")
@RestController
public class CsvController {

    private final CsvService csvService;

    @PostMapping("/restaurant")
    public ResponseCode indexingRestaurantCsv() {
        return csvService.indexingRestaurantCsv();
    }
}
