package com.volka.searchengine.core.engine.model;

import com.volka.searchengine.core.annotation.Code;
import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.lucene.document.Document;
import org.springframework.format.annotation.NumberFormat;

/**
 * 거래처 색인파일 모델
 *
 * @author volka
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Trdp extends DocumentModel {
    @Schema(description = "거래처 코드")
    @NumberFormat
    @NotBlank
    private String trdpCd;

    @Schema(description = "거래유형")
    @Code
    private String trxTyp;

    @Schema(description = "거래처 유형")
    @Code
    private String trdpTyp;

    @Schema(description = "거래처명")
    @NotBlank
    private String trdpTn;

    @Schema(description = "사업자번호")
    @NotBlank
    @NumberFormat
    private String trdpBrn;

    @Schema(description = "대표자명")
    @NotBlank
    private String rprtNm;

//    @Override
//    public SEARCH_DOMAIN getDomain() {
//        return SEARCH_DOMAIN.TRDP;
//    }

    private Trdp(Document document) {
        this.trdpCd = document.get("trdpCd");
        this.trxTyp = document.get("trxTyp");
        this.trdpTyp = document.get("trdpTyp");
        this.trdpTn = document.get("trdpTn");
        this.trdpBrn = document.get("trdpBrn");
        this.rprtNm = document.get("rprtNm");
    }

    public static Trdp of(Document document) {
        return new Trdp(document);
    }
}
