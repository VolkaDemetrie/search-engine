package com.volka.searchengine.domain.csv.service;

import com.volka.searchengine.core.constant.ResponseCode;
import org.springframework.web.multipart.MultipartFile;

public interface CsvService {
    ResponseCode indexingRestaurantCsv();
}
