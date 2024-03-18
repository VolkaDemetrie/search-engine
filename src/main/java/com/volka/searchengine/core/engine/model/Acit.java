package com.volka.searchengine.core.engine.model;

import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.lucene.document.Document;

/**
 * 계정과목 색인파일 모델
 *
 * @author volka
 */
@NoArgsConstructor
@Data
public class Acit implements DocumentModel {

    private String acitCd;
    private String acitDivCd;
    private String aictClsfCd;
    private String acitNm;
    private String acitTyp;
    private String useYn;
    private String dbtCrdtDiv;

    private Acit(Document document) {
        this.acitCd = document.get("acitCd");
        this.acitDivCd = document.get("acitDivCd");
        this.aictClsfCd = document.get("aictClsfCd");
        this.acitNm = document.get("acitNm");
        this.acitTyp = document.get("acitTyp");
        this.useYn = document.get("useYn");
        this.dbtCrdtDiv = document.get("dbtCrdtDiv");
    }

    public static Acit of(Document document) {
        return new Acit(document);
    }

    @Override
    public SEARCH_DOMAIN getDomain() {
        return SEARCH_DOMAIN.ACIT;
    }






}
