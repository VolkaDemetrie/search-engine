package com.volka.searchengine.core.engine.strategy.index;

import com.volka.searchengine.core.context.ApplicationContextProvider;
import com.volka.searchengine.core.engine.model.Acit;
import com.volka.searchengine.core.engine.tokenizer.JamoToken;
import com.volka.searchengine.core.engine.tokenizer.JamoTokenizer;
import com.volka.searchengine.core.exception.BizException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.LockObtainFailedException;

import java.io.IOException;
import java.util.List;


/**
 * 계정과목 색인 전략
 *
 * @author volka
 */
@Slf4j
@Getter
public class AcitIndexStrategy extends IndexStrategy {

    private final List<Acit> acitList;

    public AcitIndexStrategy(List<Acit> acitList) {
        this.acitList = acitList;
    }

    @Override
    public void addDocument(IndexWriter indexWriter) throws BizException, Exception {
        JamoTokenizer tokenizer = ApplicationContextProvider.getBean(JamoTokenizer.class);
        JamoToken token = null;
        for (Acit acit : this.acitList) {
            Document doc = new Document();
            token = tokenizer.tokenize(acit.getAcitNm());

            doc.add(new TextField("chosung", token.getChosung(), Field.Store.NO));
            doc.add(new TextField("jamo", token.getJamo(), Field.Store.NO));
            doc.add(new TextField("acitCd", acit.getAcitCd(), Field.Store.YES));

            doc.add(new StringField("acitNm", acit.getAcitNm(), Field.Store.YES));
            doc.add(new StringField("acitDivCd", acit.getAcitDivCd(), Field.Store.YES));
            doc.add(new StringField("acitClsfCd", acit.getAcitClsfCd(), Field.Store.YES));

            doc.add(new StringField("acitTyp", acit.getAcitTyp(), Field.Store.YES));
            doc.add(new StringField("useYn", acit.getUseYn(), Field.Store.YES));

            indexWriter.addDocument(doc);
        }
        indexWriter.commit();
    }

    @Override
    public void updateDocument(IndexWriter indexWriter) throws CorruptIndexException, LockObtainFailedException, IOException, BizException, Exception {

    }
}
