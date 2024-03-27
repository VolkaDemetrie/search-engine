package com.volka.searchengine.core.engine.strategy.index;

import com.volka.searchengine.core.exception.BizException;
import org.apache.lucene.index.IndexWriter;

/**
 * 도메인 전략 인터페이스
 *
 * @author volka
 */
public abstract class IndexStrategy {

    public abstract void addDocument(IndexWriter indexWriter) throws BizException, Exception;

    public abstract void updateDocument(IndexWriter indexWriter) throws BizException, Exception;

}
