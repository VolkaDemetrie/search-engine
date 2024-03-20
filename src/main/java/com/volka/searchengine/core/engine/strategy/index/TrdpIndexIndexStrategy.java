package com.volka.searchengine.core.engine.strategy.index;

import com.volka.searchengine.core.engine.model.Trdp;
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
public class TrdpIndexIndexStrategy extends IndexStrategy {

    public TrdpIndexIndexStrategy(List<Trdp> trdpList, IndexWriter indexWriter) {
        this.trdpList = trdpList;
        this.indexWriter = indexWriter;
    }

    private final List<Trdp> trdpList;
    private final IndexWriter indexWriter;

    @Override
    public void addDocument() throws BizException, Exception {
        for (Trdp trdp : this.trdpList) {
            Document doc = new Document();

            doc.add(new StringField("trdpCd", trdp.getTrdpCd(), Field.Store.YES));
            doc.add(new StringField("trxTyp", trdp.getTrxTyp(), Field.Store.YES));
            doc.add(new StringField("trdpTyp", trdp.getTrdpTyp(), Field.Store.YES));
            doc.add(new TextField("trdpTn", trdp.getTrdpTn(), Field.Store.YES));
            doc.add(new StringField("trdpBrn", trdp.getTrdpBrn(), Field.Store.YES));
            doc.add(new TextField("rprtNm", trdp.getRprtNm(), Field.Store.YES));

            this.indexWriter.addDocument(doc);
        }
    }
}
