package com.volka.searchengine.core.engine.strategy.index;

import com.volka.searchengine.core.context.ApplicationContextProvider;
import com.volka.searchengine.core.engine.model.Acit;
import com.volka.searchengine.core.engine.model.Trdp;
import com.volka.searchengine.core.engine.tokenizer.JamoToken;
import com.volka.searchengine.core.engine.tokenizer.JamoTokenizer;
import com.volka.searchengine.core.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;

import java.io.IOException;
import java.util.List;


/**
 * 거래처 색인 전략
 *
 * @author volka
 */
@Slf4j
public class TrdpIndexStrategy implements IndexStrategy {

    private final List<Trdp> trdpList;

    public TrdpIndexStrategy(List<Trdp> trdpList) {
        this.trdpList = trdpList;
    }

    private Document documentation(Trdp trdp, JamoTokenizer tokenizer) throws BizException, Exception {
        Document doc = new Document();
        JamoToken token = tokenizer.tokenize(trdp.getTrdpTn());

        doc.add(new TextField("chosung", token.getChosung(), Field.Store.YES));
        doc.add(new TextField("jamo", token.getJamo(), Field.Store.YES));
        doc.add(new StringField("trdpCd", trdp.getTrdpCd(), Field.Store.YES));
        doc.add(new StringField("trxTyp", trdp.getTrxTyp(), Field.Store.YES));
        doc.add(new StringField("trdpTyp", trdp.getTrdpTyp(), Field.Store.YES));
        doc.add(new StringField("trdpTn", trdp.getTrdpTn(), Field.Store.YES));
        doc.add(new StringField("trdpBrn", trdp.getTrdpBrn(), Field.Store.YES));
        doc.add(new TextField("rprtNm", trdp.getRprtNm(), Field.Store.YES));

        return doc;
    }


    @Override
    public void addDocument(IndexWriter indexWriter) throws BizException, Exception {

        JamoTokenizer tokenizer = ApplicationContextProvider.getBean(JamoTokenizer.class);
        for (Trdp trdp : this.trdpList) {
            indexWriter.addDocument(documentation(trdp, tokenizer));
        }
        indexWriter.commit();
    }

    @Override
    public void updateDocument(IndexWriter indexWriter) throws IOException, Exception {

        JamoTokenizer tokenizer = ApplicationContextProvider.getBean(JamoTokenizer.class);

        for (Trdp trdp : trdpList) {
            Term updateTargetTerm = new Term("trdpCd", trdp.getTrdpCd());
            indexWriter.updateDocument(updateTargetTerm, documentation(trdp, tokenizer));
        }

        indexWriter.commit();
    }

    @Override
    public boolean isExistIndex(IndexReader reader) throws IOException, Exception {
        for (Trdp trdp : trdpList) {
            Term term = new Term("trdpCd", trdp.getTrdpCd());
            if (reader.totalTermFreq(term) > 0) return true;
        }

        return false;
    }
}
