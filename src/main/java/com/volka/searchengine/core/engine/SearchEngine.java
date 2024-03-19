package com.volka.searchengine.core.engine;

import com.volka.searchengine.core.constant.ResponseCode;
import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import com.volka.searchengine.core.engine.model.Acit;
import com.volka.searchengine.core.engine.model.DocumentModel;
import com.volka.searchengine.core.engine.model.Trdp;
import com.volka.searchengine.core.engine.strategy.IndexStrategyContext;
import com.volka.searchengine.core.engine.strategy.TermStrategyContext;
import com.volka.searchengine.core.engine.strategy.index.AcitIndexIndexStrategy;
import com.volka.searchengine.core.engine.strategy.index.TrdpIndexIndexStrategy;
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
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class SearchEngine {

    private final EngineProperties engineProperties;
//    private final IndexWriterConfig koreanIndexWriterConfig; FIXME :: DO NOT SHARE
    private final KoreanAnalyzer koreanAnalyzer;
    private final IndexStrategyContext indexStrategyContext;
    private final TermStrategyContext termStrategyContext;

    private static final int hitPerPage = 10; //FIXME :: 요건 따라 입력 받아서 결과 갯수 변동 가능할수도


    @PostConstruct
    private void init() throws Exception {
        EngineFileProperties fileProperties = this.engineProperties.getFile();
        File idxDir = new File(fileProperties.getIdxDir());

        initPath(idxDir);
    }

    private void initPath(File idxDir) throws BizException, Exception{
        if (!idxDir.exists()) {
            if (!idxDir.mkdirs()) {
                log.error("Indexing path init failed");
                throw new BizException(ResponseCode.INIT_FAIL);
            }
        }
    }

    private File generateIdxDirByOrgId(String orgId, SEARCH_DOMAIN domain) throws BizException, Exception {
        String loc = orgId.substring(16);
        File idxDir = new File(String.format("%s%s%s%s%s%s%s", engineProperties.getFile().getIdxDir(), File.separator, loc, File.separator, orgId, File.separator, domain));
        initPath(idxDir);

        return idxDir;
    }

    /**
     * 색인
     *
     * @param orgId
     * @param modelList
     * @param <T>
     * @throws BizException
     * @throws Exception
     */
    @SuppressWarnings(value = "unchecked")
    public <T extends DocumentModel> void indexing(String orgId, List<T> modelList) throws IOException {

        Directory indexDir = null;
        IndexWriter writer = null;

        try {
            T firstModel = modelList.get(0);
            SEARCH_DOMAIN domain = firstModel.getDomain();

            File orgIdxDir = generateIdxDirByOrgId(orgId, domain);
            indexDir = NIOFSDirectory.open(orgIdxDir.toPath());
            writer = new IndexWriter(indexDir, new IndexWriterConfig(koreanAnalyzer));

            switch (domain) {
                case ACIT :
                    if (firstModel instanceof Acit) indexStrategyContext.addDocument(new AcitIndexIndexStrategy((List<Acit>) modelList, writer));
                    break;
                case TRDP :
                    if (firstModel instanceof Trdp) indexStrategyContext.addDocument(new TrdpIndexIndexStrategy((List<Trdp>) modelList, writer));
                    break;
                default :
                    break;
            }

        } catch (BizException e) {
            log.error("[EXCEPTION] indexing() :: {} : {}", e.getCode(), e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            log.error("[EXCEPTION] indexing() :: {} : {}", e.getLocalizedMessage(), e.toString());
            throw new BizException(ResponseCode.FAIL, e);
        } finally {
            if (writer != null) writer.close();
            if (indexDir != null) indexDir.close();
        }

    }

    public List<DocumentModel> search(String orgId, String word, SEARCH_DOMAIN domain) throws BizException, Exception {

        File idxDir = generateIdxDirByOrgId(orgId, domain);

        try (
                IndexReader reader = DirectoryReader.open(NIOFSDirectory.open(idxDir.toPath()));
        ) {

            IndexSearcher searcher = new IndexSearcher(reader);
            BooleanQuery query = termStrategyContext.createQuery(word, domain);

            TopDocs docs = searcher.search(query, hitPerPage);
            ScoreDoc[] hitArr = docs.scoreDocs;

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
