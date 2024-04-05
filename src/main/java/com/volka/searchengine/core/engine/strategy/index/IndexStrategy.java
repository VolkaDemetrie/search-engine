package com.volka.searchengine.core.engine.strategy.index;

import com.volka.searchengine.core.engine.model.DocumentModel;
import com.volka.searchengine.core.exception.BizException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;

import java.io.IOException;
import java.util.List;

/**
 * 도메인 전략 인터페이스
 *
 * TODO : 전략패턴 -> 다른 패턴 전환 ? 20240302 volka
 *
 * @author volka
 */
public interface IndexStrategy {

    void addDocument(IndexWriter indexWriter) throws BizException, Exception;

    void updateDocument(IndexWriter indexWriter) throws IOException, Exception;

    List<DocumentModel> getDuplicateList(IndexReader reader) throws IOException, Exception;
}
