package com.volka.searchengine.core.engine;

import com.volka.searchengine.core.constant.ResponseCode;
import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import com.volka.searchengine.core.engine.model.DocumentModel;
import com.volka.searchengine.core.engine.strategy.TermStrategyContext;
import com.volka.searchengine.core.engine.strategy.index.IndexStrategy;
import com.volka.searchengine.core.exception.BizException;
import com.volka.searchengine.core.properties.EngineFileProperties;
import com.volka.searchengine.core.properties.EngineProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.ko.KoreanAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RequiredArgsConstructor
@Component
public class SearchEngine {

    private final EngineProperties engineProperties;
//    private final IndexWriterConfig koreanIndexWriterConfig; FIXME :: DO NOT SHARE
    private static String baseDirPath;
    private final KoreanAnalyzer koreanAnalyzer;
//    private final IndexStrategyContext indexStrategyContext;
    private final TermStrategyContext termStrategyContext;

    private static final ExecutorService threadPool = Executors.newFixedThreadPool(8);

    private static final int hitPerPage = 10; //FIXME :: 요건 따라 입력 받아서 결과 갯수 변동 가능할수도


    @PostConstruct
    private void init() throws Exception {
        EngineFileProperties fileProperties = this.engineProperties.getFile();
        baseDirPath = fileProperties.getIdxDir();
        File idxDir = new File(baseDirPath);

        initPath(idxDir);
    }


    public void initPath(File idxDir) throws BizException, Exception{
        if (!idxDir.exists()) {
            if (!idxDir.mkdirs()) {
                log.error("Indexing path init failed");
                throw new BizException(ResponseCode.INIT_FAIL);
            }
        }
    }


    public Path generateIdxDirPathByOrgId(String baseDirPath, String orgId, SEARCH_DOMAIN domain) throws BizException, Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(baseDirPath);
        sb.append(File.separator);
        sb.append(orgId.substring(16));
        sb.append(File.separator);
        sb.append(orgId);
        sb.append(File.separator);
        sb.append(domain);

        File idxDir = new File(sb.toString());
        initPath(idxDir);

        return idxDir.toPath();
    }


    /**
     * 색인
     * @param domain
     * @param orgId
     * @param indexStrategy
     * @throws IOException
     */
    public void indexing(final SEARCH_DOMAIN domain, final String orgId, final IndexStrategy indexStrategy) throws IOException {

        if (baseDirPath == null) baseDirPath = engineProperties.getFile().getIdxDir();

        try (
                Directory indexDir = NIOFSDirectory.open(generateIdxDirPathByOrgId(baseDirPath, orgId, domain));
                IndexWriter writer = new IndexWriter(indexDir, new IndexWriterConfig(koreanAnalyzer))
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
     * 검색
     * @param orgId
     * @param word
     * @param domain
     * @return
     * @throws BizException
     * @throws Exception
     */
    public List<DocumentModel> search(final String orgId, final String word, final SEARCH_DOMAIN domain) throws BizException, Exception {


        if (baseDirPath == null) baseDirPath = engineProperties.getFile().getIdxDir();

        try (
                IndexReader reader = DirectoryReader.open(NIOFSDirectory.open(generateIdxDirPathByOrgId(baseDirPath, orgId, domain)));
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



}
