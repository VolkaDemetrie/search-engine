package com.volka.searchengine.core.engine.strategy.index;

import com.volka.searchengine.core.engine.model.Acit;
import com.volka.searchengine.core.exception.BizException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;

import java.util.List;


@Slf4j
@Getter
public class AcitIndexIndexStrategy implements DomainIndexStrategy {

    public AcitIndexIndexStrategy(List<Acit> acitList, IndexWriter indexWriter) {
        this.acitList = acitList;
        this.indexWriter = indexWriter;
    }

    private final List<Acit> acitList;
    private final IndexWriter indexWriter;

    @Override
    public void addDocument() throws BizException, Exception {
        for (Acit acit : this.acitList) {
            Document doc = new Document();

            doc.add(new StringField("acitCd", acit.getAcitCd(), Field.Store.YES));
            doc.add(new StringField("acitDivCd", acit.getAcitDivCd(), Field.Store.YES));
            doc.add(new StringField("acitClsfCd", acit.getAcitClsfCd(), Field.Store.YES));
            doc.add(new TextField("acitNm", acit.getAcitNm(), Field.Store.YES));
            doc.add(new StringField("acitTyp", acit.getAcitTyp(), Field.Store.YES));
            doc.add(new StringField("useYn", acit.getUseYn(), Field.Store.YES));
//            doc.add(new StringField("dbtCrdtDiv", acit.getDbtCrdtDiv(), Field.Store.YES));

            this.indexWriter.addDocument(doc);
        }
    }
}
