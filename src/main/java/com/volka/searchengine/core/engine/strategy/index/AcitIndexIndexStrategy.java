package com.volka.searchengine.core.engine.strategy.index;

import com.volka.searchengine.core.context.ApplicationContextProvider;
import com.volka.searchengine.core.engine.model.Acit;
import com.volka.searchengine.core.engine.tokenizer.ChosungTokenizer;
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
 * 계정과목 색인 전략
 *
 * @author volka
 */
@Slf4j
@Getter
public class AcitIndexIndexStrategy extends IndexStrategy {

    private final List<Acit> acitList;

    public AcitIndexIndexStrategy(List<Acit> acitList) {
        this.acitList = acitList;
    }

    @Override
    public void addDocument(IndexWriter indexWriter) throws BizException, Exception {
        ChosungTokenizer tokenizer = ApplicationContextProvider.getBean(ChosungTokenizer.class);

        for (Acit acit : this.acitList) {
            Document doc = new Document();

            doc.add(new TextField("chosung", tokenizer.tokenize(acit.getAcitNm()), Field.Store.YES));
            doc.add(new TextField("acitCd", acit.getAcitCd(), Field.Store.YES));
            doc.add(new StringField("acitNm", acit.getAcitNm(), Field.Store.YES));
            doc.add(new StringField("acitDivCd", acit.getAcitDivCd(), Field.Store.YES));
            doc.add(new StringField("acitClsfCd", acit.getAcitClsfCd(), Field.Store.YES));

            doc.add(new StringField("acitTyp", acit.getAcitTyp(), Field.Store.YES));
            doc.add(new StringField("useYn", acit.getUseYn(), Field.Store.YES));

            indexWriter.addDocument(doc);
        }
    }
}
