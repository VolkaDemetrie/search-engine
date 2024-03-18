package com.volka.searchengine.dto;

import lombok.Data;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * 색인 파일 관리 요청 도메인
 *
 * @author volka
 */
public interface ManagementRequest {


    /**
     * 계정과목 초기화 요청
     */
    @Data
    class Acit {

        private String orgId;

        @NonNull
        private List<Acit> acitModelList;
    }

}
