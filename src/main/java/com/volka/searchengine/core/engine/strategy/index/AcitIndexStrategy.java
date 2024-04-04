package com.volka.searchengine.core.engine.strategy.index;

import com.volka.searchengine.core.context.ApplicationContextProvider;
import com.volka.searchengine.core.engine.model.Acit;
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
 * 계정과목 색인 전략
 *
 * @author volka
 */
@Slf4j
public class AcitIndexStrategy implements IndexStrategy {

    private final List<Acit> acitList;

    public AcitIndexStrategy(List<Acit> acitList) {
        this.acitList = acitList;
    }

    private Document documentation(Acit acit, JamoTokenizer tokenizer) throws BizException, Exception {
        Document doc = new Document();
        JamoToken token = tokenizer.tokenize(acit.getAcitNm());

        doc.add(new TextField("chosung", token.getChosung(), Field.Store.NO));
        doc.add(new TextField("jamo", token.getJamo(), Field.Store.NO));
        doc.add(new TextField("acitCd", acit.getAcitCd(), Field.Store.YES));

        doc.add(new StringField("acitNm", acit.getAcitNm(), Field.Store.YES));
        doc.add(new StringField("acitDivCd", acit.getAcitDivCd(), Field.Store.YES));
        doc.add(new StringField("acitClsfCd", acit.getAcitClsfCd(), Field.Store.YES));

        doc.add(new StringField("acitTyp", acit.getAcitTyp(), Field.Store.YES));
//        doc.add(new StringField("useYn", acit.getUseYn(), Field.Store.YES));

        return doc;
    }

    @Override
    public void addDocument(IndexWriter indexWriter) throws BizException, Exception {
        JamoTokenizer tokenizer = ApplicationContextProvider.getBean(JamoTokenizer.class);

        for (Acit acit : this.acitList) {
            indexWriter.addDocument(documentation(acit, tokenizer));
        }
    }

    @Override
    public void updateDocument(IndexWriter indexWriter) throws IOException, Exception {
        JamoTokenizer tokenizer = ApplicationContextProvider.getBean(JamoTokenizer.class);

        for (Acit acit : acitList) {
            Term updateTargetTerm = new Term("acitCd", acit.getAcitCd());
            indexWriter.updateDocument(updateTargetTerm, documentation(acit, tokenizer));
        }
    }

    @Override
    public boolean isExistIndex(IndexReader reader) throws IOException, Exception {

        for (Acit acit : acitList) {
            Term term = new Term("acitCd", acit.getAcitCd());
            if (reader.totalTermFreq(term) > 0) return true;
        }
        
        return false;
    }
}
