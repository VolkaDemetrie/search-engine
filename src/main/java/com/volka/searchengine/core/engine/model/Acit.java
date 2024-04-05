package com.volka.searchengine.core.engine.model;

import com.volka.searchengine.core.annotation.Code;
import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.lucene.document.Document;
import org.springframework.format.annotation.NumberFormat;


/**
 * 계정과목 색인파일 모델
 *
 * @author volka
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Acit extends DocumentModel {

    @NotBlank
    @NumberFormat
    private String acitCd;

    @NotBlank
    private String acitNm;

    @NotBlank
    @Code
    private String acitDivCd;

    @NotBlank
    @NumberFormat
    private String acitClsfCd;

    @NotBlank
    @Code
    private String acitTyp;

//    private String useYn;
//    @Code
//    private String dbtCrdtDiv;

    private Acit(Document document) {
        this.acitCd = document.get("acitCd");
        this.acitDivCd = document.get("acitDivCd");
        this.acitClsfCd = document.get("acitClsfCd");
        this.acitNm = document.get("acitNm");
        this.acitTyp = document.get("acitTyp");
//        this.useYn = document.get("useYn");
    }

    public static Acit of(Document document) {
        return new Acit(document);
    }

    @Override
    public SEARCH_DOMAIN getDomain() {
        return SEARCH_DOMAIN.ACIT;
    }

}
