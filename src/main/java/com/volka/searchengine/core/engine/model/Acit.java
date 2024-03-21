package com.volka.searchengine.core.engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.volka.searchengine.core.annotation.Code;
import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.lucene.document.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    private String acitCd;

    @NotNull
    @NotBlank
    private String acitNm;

    @Code
    @NotBlank
    private String acitDivCd;

    @Code
    @NotBlank
    private String acitClsfCd;

    @Code
    @NotBlank
    private String acitTyp;

    private String useYn;

//    @Code
//    private String dbtCrdtDiv;

    private Acit(Document document) {
        this.acitCd = document.get("acitCd");
        this.acitDivCd = document.get("acitDivCd");
        this.acitClsfCd = document.get("acitClsfCd");
        this.acitNm = document.get("acitNm");
        this.acitTyp = document.get("acitTyp");
        this.useYn = document.get("useYn");
    }

    public static Acit of(Document document) {
        return new Acit(document);
    }

    @Override
    public SEARCH_DOMAIN getDomain() {
        return SEARCH_DOMAIN.ACIT;
    }






}
