package com.volka.searchengine.core.util;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.volka.searchengine.core.constant.ResponseCode;
import com.volka.searchengine.core.exception.BizException;
import com.volka.searchengine.dto.RestaurantCsvDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Component
public class CsvLoader {

    public List<RestaurantCsvDTO> loadRestaurantCsv() {
        List<RestaurantCsvDTO> resultList = null;

        try (Reader reader = Files.newBufferedReader(Paths.get("/home/volka/search_engine/The_whole_country_restaurant_Info.csv"))) {

            CsvToBean csvToBean = new CsvToBeanBuilder<>(reader)
                    .withType(RestaurantCsvDTO.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            resultList = csvToBean.parse();

            return resultList;

        } catch (Exception e) {
            log.error("error :: {}", e);
            log.error("error :: {}", e.getLocalizedMessage());
            throw new BizException(ResponseCode.FAIL);

        }
    }
}
