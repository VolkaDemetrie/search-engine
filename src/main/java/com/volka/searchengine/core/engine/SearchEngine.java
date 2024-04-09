package com.volka.searchengine.core.engine;

import com.volka.searchengine.core.constant.ResponseCode;
import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import com.volka.searchengine.core.engine.model.DocumentModel;
import com.volka.searchengine.core.engine.properties.EngineProperties;
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
import org.apache.lucene.store.LockFactory;
import org.apache.lucene.store.NIOFSDirectory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private final EngineProperties engineProperties;

//    private final IndexWriterConfig koreanIndexWriterConfig; FIXME :: DO NOT SHARE

    private final LockFactory lockFactory;

    private final TermStrategyContext termStrategyContext;
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(8);

    private static final int hitPerPage = 10; //FIXME :: 요건 따라 입력 받아서 결과 갯수 변동 가능할수도


    @PostConstruct
    private void init() throws Exception {
        initPath();
    }

    private void initPath() throws BizException, Exception {
        Path basePath = engineProperties.getIdxDir();
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

    private Path generatePath(String orgId, SEARCH_DOMAIN domain) throws BizException, Exception {
        return engineProperties.getIdxDir().resolve(orgId.substring(16)).resolve(orgId).resolve(domain.toString());
    }


    public Path generatePathWithMkdir(String orgId, SEARCH_DOMAIN domain) throws Exception {
        Path path = generatePath(orgId, domain);

        if (!Files.exists(path)) mkdirs(path);

        return path;
    }

    private Directory getIndexDir(final SEARCH_DOMAIN domain, final String orgId) {
        try {
            return NIOFSDirectory.open(generatePathWithMkdir(orgId, domain), lockFactory);
        } catch (Exception e) {
            log.error("[EXCEPTION] getIndexDir() :: {} : {}", e.getLocalizedMessage(), e.toString());
            throw new BizException(ResponseCode.FAIL, e);
        }
    }

    /**
     * 색인
     * @param domain
     * @param orgId
     * @param indexStrategy
     */
    public void indexing(final SEARCH_DOMAIN domain, final String orgId, final IndexStrategy indexStrategy) {
        try (
                Directory indexDir = getIndexDir(domain, orgId);
                IndexWriter writer = new IndexWriter(indexDir, new IndexWriterConfig());
        ) {
            validDuplicated(indexDir, indexStrategy);
            indexStrategy.addDocument(writer);
            writer.commit();

        } catch (BizException e) {
            log.error("[EXCEPTION] indexing() :: {} : {}", e.getCode(), e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            log.error("[EXCEPTION] indexing() :: {} : {}", e.getLocalizedMessage(), e.toString());
            throw new BizException(ResponseCode.FAIL, e);
        }
    }

    /**
     * 도메인별 색인 초기화
     * @param domain
     * @param orgId
     * @param indexStrategy
     * @throws Exception
     */
    public void initializeIndexByDomain(final SEARCH_DOMAIN domain, final String orgId, final IndexStrategy indexStrategy) throws Exception {
        if (Files.exists(generatePath(orgId, domain))) deleteIndexAll(domain, orgId);
        indexing(domain, orgId, indexStrategy);
    }


    private void validDuplicated(Directory indexDir, IndexStrategy indexStrategy) {
        try (IndexReader reader = DirectoryReader.open(indexDir);) {
            if (reader.numDocs() > 0) {
                List<DocumentModel> duplicateList = indexStrategy.getDuplicateList(reader);

                if (duplicateList.size() > 0) {
                    Map<String, List<DocumentModel>> resultMap = new HashMap<>();
                    resultMap.put("duplicateList", duplicateList);
                    throw new BizException("IX0000", resultMap); //이미 존재하는 색인입니다.
                }

                duplicateList = null;
            }
        } catch (IOException e) {
            log.error("[EXCEPTION] validDuplicated() :: {}", e.getLocalizedMessage());
            throw new BizException(ResponseCode.FAIL, e);
        } catch (BizException e) {
            log.error("[EXCEPTION] validDuplicated() :: {} : {}", e.getCode(), e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            log.error("[EXCEPTION] validDuplicated() :: {} : {}", e.getLocalizedMessage(), e.toString());
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
//                Directory indexDir = NIOFSDirectory.open(generatePathWithMkdir(orgId, domain));
                Directory indexDir = getIndexDir(domain, orgId);
                IndexWriter writer = new IndexWriter(indexDir, new IndexWriterConfig())
        ) {
            indexStrategy.updateDocument(writer);
            writer.commit();
        } catch (BizException e) {
            log.error("[EXCEPTION] updateIndex() :: {} : {}", e.getCode(), e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            log.error("[EXCEPTION] updateIndex() :: {} : {}", e.getLocalizedMessage(), e.toString());
            throw new BizException(ResponseCode.FAIL, e);
        }
    }


    /**
     * 검색
     * @param orgId
     * @param word
     * @param domain
     * @return
     */
    public List<DocumentModel> search(final String orgId, final String word, final SEARCH_DOMAIN domain) {
        try (
//                IndexReader reader = DirectoryReader.open(NIOFSDirectory.open(generatePathWithMkdir(orgId, domain)));
                IndexReader reader = DirectoryReader.open(getIndexDir(domain, orgId));
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

    /**
     * 색인 선택 삭제
     *
     * @param domain
     * @param orgId
     * @param keyList
     */
    public void deleteIndexById(SEARCH_DOMAIN domain, String orgId, List<String> keyList) {
        try (
//                Directory indexDir = NIOFSDirectory.open(generatePathWithMkdir(orgId, domain));
                Directory indexDir = getIndexDir(domain, orgId);
                IndexWriter writer = new IndexWriter(indexDir, new IndexWriterConfig())
        ) {

            String fieldId = null;
            Term term = null;

            switch (domain) {
                case ACIT -> fieldId = "acitCd";
                case TRDP -> fieldId = "trdpCd";
                default -> throw new BizException("FL0002"); //정의되지 않은 도메인입니다.
            }

            for (String key : keyList) {
                term = new Term(fieldId, key);
                writer.deleteDocuments(term);
            }

            writer.commit();

        } catch (BizException e) {
            log.error("[EXCEPTION] deleteIndexAll() :: {} : {}", e.getCode(), e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            log.error("[EXCEPTION] deleteIndexAll() :: {} : {}", e.getLocalizedMessage(), e.toString());
            throw new BizException(ResponseCode.FAIL, e);
        }
    }

    private void deleteIndexAll(SEARCH_DOMAIN domain, String orgId) {
        try (
//                Directory indexDir = NIOFSDirectory.open(generatePathWithMkdir(orgId, domain));
                Directory indexDir = getIndexDir(domain, orgId);
                IndexWriter writer = new IndexWriter(indexDir, new IndexWriterConfig())
        ) {
            writer.deleteAll();
            writer.commit();
        } catch (BizException e) {
            log.error("[EXCEPTION] deleteIndexAll() :: {} : {}", e.getCode(), e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            log.error("[EXCEPTION] deleteIndexAll() :: {} : {}", e.getLocalizedMessage(), e.toString());
            throw new BizException(ResponseCode.FAIL, e);
        }
    }
}
