package com.volka.searchengine.core.engine;

import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import com.volka.searchengine.core.engine.model.Acit;
import com.volka.searchengine.core.engine.model.DocumentModel;
import com.volka.searchengine.core.engine.model.Trdp;
import com.volka.searchengine.core.exception.BizException;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;

import java.util.ArrayList;
import java.util.List;

/**
 * 결과 응답 변환기
 *
 * @author volka
 */
public class ResponseConverter {

    private ResponseConverter() {
        super();
    }

    public static List<DocumentModel> convert(IndexSearcher searcher, ScoreDoc[] hitArr, SEARCH_DOMAIN domain) throws BizException, Exception {
        return switch (domain) {
            case ACIT -> convertToAcit(searcher, hitArr);
            case TRDP -> convertToTrdp(searcher, hitArr);
            default -> null;
        };
    }

    private static List<DocumentModel> convertToAcit(IndexSearcher searcher, ScoreDoc[] hitArr) throws BizException, Exception {

        Document document = null;

        List<DocumentModel> resultList = new ArrayList<>(10);

        for (ScoreDoc scoreDoc : hitArr) {
            document = searcher.doc(scoreDoc.doc);
            resultList.add(Acit.of(document));
        }

        return resultList;
    }

    private static List<DocumentModel> convertToTrdp(IndexSearcher searcher, ScoreDoc[] hitArr) throws BizException, Exception {

        Document document = null;

        List<DocumentModel> resultList = new ArrayList<>(10);

        for (ScoreDoc scoreDoc : hitArr) {
            document = searcher.doc(scoreDoc.doc);
            resultList.add(Trdp.of(document));
        }

        return resultList;
    }

}
