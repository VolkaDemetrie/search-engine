package com.volka.searchengine.dto;

import com.volka.searchengine.core.annotation.SysId;
import com.volka.searchengine.core.engine.model.Acit;
import com.volka.searchengine.core.engine.model.Trdp;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import java.util.List;

public interface IndexingRequest {

    /**
     * 계정과목 색인 요청
     */
    @Data
    class SaveAcit {
        @SysId
        private String orgId;

        @Nullable
        @Valid
        private List<Acit> acitList;

        @Nullable
        @Valid
        private List<Trdp> trdpList;
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
