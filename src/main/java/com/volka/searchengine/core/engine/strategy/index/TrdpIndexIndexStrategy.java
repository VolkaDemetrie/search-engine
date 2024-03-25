package com.volka.searchengine.core.engine.strategy.index;

import com.volka.searchengine.core.context.ApplicationContextProvider;
import com.volka.searchengine.core.engine.model.Trdp;
import com.volka.searchengine.core.engine.tokenizer.JamoToken;
import com.volka.searchengine.core.engine.tokenizer.JamoTokenizer;
import com.volka.searchengine.core.exception.BizException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;

import java.util.List;


/**
 * 거래처 색인 전략
 *
 * @author volka
 */
@Slf4j
@Getter
public class TrdpIndexIndexStrategy extends IndexStrategy {

    public TrdpIndexIndexStrategy(List<Trdp> trdpList) {
        this.trdpList = trdpList;
    }

    private final List<Trdp> trdpList;

    @Override
    public void addDocument(IndexWriter indexWriter) throws BizException, Exception {

        JamoTokenizer tokenizer = ApplicationContextProvider.getBean(JamoTokenizer.class);
        JamoToken token = null;
        for (Trdp trdp : this.trdpList) {
            Document doc = new Document();
            token = tokenizer.tokenize(trdp.getTrdpTn());

            doc.add(new TextField("chosung", token.getChosung(), Field.Store.YES));
            doc.add(new TextField("jamo", token.getJamo(), Field.Store.YES));
            doc.add(new StringField("trdpCd", trdp.getTrdpCd(), Field.Store.YES));
            doc.add(new StringField("trxTyp", trdp.getTrxTyp(), Field.Store.YES));
            doc.add(new StringField("trdpTyp", trdp.getTrdpTyp(), Field.Store.YES));
            doc.add(new StringField("trdpTn", trdp.getTrdpTn(), Field.Store.YES));
            doc.add(new StringField("trdpBrn", trdp.getTrdpBrn(), Field.Store.YES));
            doc.add(new TextField("rprtNm", trdp.getRprtNm(), Field.Store.YES));

            indexWriter.addDocument(doc);
        }
    }
}
