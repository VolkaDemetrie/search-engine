package com.volka.searchengine.dto;

import com.volka.searchengine.core.annotation.SysId;
import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import com.volka.searchengine.core.engine.model.Acit;
import com.volka.searchengine.core.engine.model.Trdp;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import java.util.List;

public interface IndexingRequest {

    /**
     * 계정과목 초기화 요청
     */
    @Data
    class Save {
        private SEARCH_DOMAIN domain;

        @SysId
        private String orgId;

        @Valid
        @Nullable
        private List<Trdp> trdpList;

        @Valid
        @Nullable
        private List<Acit> acitList;
    }

    @Data
    class Init {
        @SysId
        private String orgId;

        @Valid
        private List<Trdp> trdpList;

        @Valid
        private List<Acit> acitList;
    }
}
