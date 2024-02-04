package com.volka.searchengine.dto;

import lombok.Data;

/**
 * 검색어 DTO
 */
@Data
public class Search {
    private String key;
    private String word;
    private int rank; //순위
}
