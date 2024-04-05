package com.volka.searchengine.dto;

import com.volka.searchengine.core.annotation.SysId;
import com.volka.searchengine.core.engine.model.Acit;
import com.volka.searchengine.core.engine.model.Trdp;
import io.swagger.v3.oas.annotations.media.Schema;
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
        @Schema(description = "기관아이디", requiredMode = Schema.RequiredMode.REQUIRED)
        @SysId
        private String orgId;
        
        @Schema(description = "계정과목 목록", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotEmpty
        @Valid
        private List<Acit> acitList;
    }

    @Data
    class SaveTrdp {
        @SysId
        @Schema(description = "기관 아이디", requiredMode = Schema.RequiredMode.REQUIRED)
        private String orgId;

        @NotEmpty
        @Valid
        @Schema(description = "거래처 목록", requiredMode = Schema.RequiredMode.REQUIRED)
        private List<Trdp> trdpList;
    }

    @Data
    class Init {
        @SysId
        @Schema(description = "기관 아이디", requiredMode = Schema.RequiredMode.REQUIRED)
        private String orgId;

        @NotEmpty
        @Valid
        @Schema(description = "거래처 목록", requiredMode = Schema.RequiredMode.REQUIRED)
        private List<Trdp> trdpList;

        @NotEmpty
        @Valid
        @Schema(description = "계정과목 목록", requiredMode = Schema.RequiredMode.REQUIRED)
        private List<Acit> acitList;
    }

    @Data
    class Delete {
        @SysId
        @Schema(description = "기관 아이디", requiredMode = Schema.RequiredMode.REQUIRED)
        private String orgId;

        @NotNull
        @NotEmpty
        @Schema(description = "키목록 (계정과목 acitCd, 거래처 trdpCd)", requiredMode = Schema.RequiredMode.REQUIRED)
        private List<String> keyList;
    }
}
