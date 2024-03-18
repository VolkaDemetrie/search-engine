package com.volka.searchengine.core.engine.strategy.index;

import com.volka.searchengine.core.engine.model.DocumentModel;
import com.volka.searchengine.core.exception.BizException;
import org.apache.lucene.index.IndexWriter;

import java.nio.file.Path;
import java.util.List;

/**
 * 도메인 전략 인터페이스
 *
 * @author volka
 */
public interface DomainIndexStrategy {

    void addDocument() throws BizException, Exception;
}
