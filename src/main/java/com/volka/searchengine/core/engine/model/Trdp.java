package com.volka.searchengine.core.engine.model;

import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.lucene.document.Document;

/**
 * 거래처 색인파일 모델
 *
 * @author volka
 */
@NoArgsConstructor
@Data
public class Trdp implements DocumentModel {
    private String trdpCd;
    private String trxTyp;
    private String trdpTyp;
    private String trdpTn;
    private String trdpBrn;
    private String rprtNm;

    @Override
    public SEARCH_DOMAIN getDomain() {
        return SEARCH_DOMAIN.TRDP;
    }

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
