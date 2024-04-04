package com.volka.searchengine.core.engine;

import com.volka.searchengine.core.constant.ResponseCode;
import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import com.volka.searchengine.core.engine.model.DocumentModel;
import com.volka.searchengine.core.engine.properties.Engine;
import com.volka.searchengine.core.engine.strategy.TermStrategyContext;
import com.volka.searchengine.core.engine.strategy.index.IndexStrategy;
import com.volka.searchengine.core.exception.BizException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.index.*;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 검색 엔진
 *
 * @author volka
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SearchEngine {

//    private final EngineProperties engineProperties;

    private final Engine engine;

//    private final IndexWriterConfig koreanIndexWriterConfig; FIXME :: DO NOT SHARE
//    private static String baseDirPath;

//    private final KoreanAnalyzer koreanAnalyzer;
//    private final IndexStrategyContext indexStrategyContext;
    private final TermStrategyContext termStrategyContext;
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(8);

    private static final int hitPerPage = 10; //FIXME :: 요건 따라 입력 받아서 결과 갯수 변동 가능할수도


    @PostConstruct
    private void init() throws Exception {
        initPath();
    }

    private void initPath() throws BizException, Exception {
        Path basePath = engine.getIdxDir();
        if (!Files.exists(basePath)) {
            mkdirs(basePath);
        }
    }

    public void mkdirs(Path path) throws BizException, Exception{
        try {
            Files.createDirectories(path);
        } catch (FileAlreadyExistsException x) {
            log.error("already exsits");
        } catch (IOException e) {
            log.error("Indexing path init failed");
            throw new BizException(ResponseCode.INIT_FAIL);
        }
    }

    public Path generatePathByOrgId(String orgId, SEARCH_DOMAIN domain) throws BizException, Exception {
        Path path = engine.getIdxDir().resolve(orgId.substring(16)).resolve(orgId).resolve(domain.toString());

        if (!Files.exists(path)) mkdirs(path);

        return path;
    }


    /**
     * 색인
     * @param domain
     * @param orgId
     * @param indexStrategy
     * @throws IOException
     */
    public void indexing(final SEARCH_DOMAIN domain, final String orgId, final IndexStrategy indexStrategy) throws IOException {
        try (
                Directory indexDir = NIOFSDirectory.open(generatePathByOrgId(orgId, domain));
                IndexWriter writer = new IndexWriter(indexDir, new IndexWriterConfig())
        ) {
            indexStrategy.addDocument(writer);
        } catch (BizException e) {
            log.error("[EXCEPTION] indexing() :: {} : {}", e.getCode(), e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            log.error("[EXCEPTION] indexing() :: {} : {}", e.getLocalizedMessage(), e.toString());
            throw new BizException(ResponseCode.FAIL, e);
        }

    }

    /**
     * 색인 업데이트
     * @param domain
     * @param orgId
     * @param indexStrategy
     */
    public void updateIndex(final SEARCH_DOMAIN domain, final String orgId, final IndexStrategy indexStrategy) {
        try (
                Directory indexDir = NIOFSDirectory.open(generatePathByOrgId(orgId, domain));
                IndexWriter writer = new IndexWriter(indexDir, new IndexWriterConfig())
        ) {
            indexStrategy.updateDocument(writer);
        } catch (BizException e) {
            log.error("[EXCEPTION] indexing() :: {} : {}", e.getCode(), e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            log.error("[EXCEPTION] indexing() :: {} : {}", e.getLocalizedMessage(), e.toString());
            throw new BizException(ResponseCode.FAIL, e);
        }
    }


    /**
     * 검색
     * @param orgId
     * @param word
     * @param domain
     * @return
     * @throws BizException
     * @throws Exception
     */
    public List<DocumentModel> search(final String orgId, final String word, final SEARCH_DOMAIN domain) throws BizException, Exception {
        try (
                IndexReader reader = DirectoryReader.open(NIOFSDirectory.open(generatePathByOrgId(orgId, domain)));
        ) {
            IndexSearcher searcher = new IndexSearcher(reader, threadPool);
            BooleanQuery query = termStrategyContext.createQuery(word, domain);

            TopDocs docs = searcher.search(query, hitPerPage);
            ScoreDoc[] hitArr = docs.scoreDocs;

            log.info("TopDocs total hits :: {}", docs.totalHits);

            return ResponseConverter.convert(searcher, hitArr, domain);

        } catch (BizException e) {
            log.error("[EXCEPTION] indexing() :: {} : {}", e.getCode(), e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            log.error("[EXCEPTION] indexing() :: {} : {}", e.getLocalizedMessage(), e.toString());
            throw new BizException(ResponseCode.FAIL, e);
        }
    }


    public void updateRank(SEARCH_DOMAIN domain, String orgId, String keyCode) {
    }
}
