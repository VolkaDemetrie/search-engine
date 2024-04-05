package com.volka.searchengine.dto;

import com.volka.searchengine.core.annotation.SysId;
import com.volka.searchengine.core.engine.model.Acit;
import com.volka.searchengine.core.engine.model.Trdp;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

public interface IndexingRequest {

    /**
     * 계정과목 색인 요청
     */
    @Data
    class SaveAcit {
        @SysId
        private String orgId;

        @NotEmpty
        @Valid
        private List<Acit> acitList;
    }

    @Data
    class SaveTrdp {
        @SysId
        private String orgId;

        @NotEmpty
        @Valid
        private List<Trdp> trdpList;
    }

    @Data
    class Init {
        @SysId
        private String orgId;

        @NotEmpty
        @Valid
        private List<Trdp> trdpList;

        @NotEmpty
        @Valid
        private List<Acit> acitList;
    }

    @Data
    class Delete {
        @SysId
        private String orgId;

        @NotNull
        @NotEmpty
        private List<String> keyList;
    }
}
