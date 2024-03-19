package com.volka.searchengine.core.engine;

import com.volka.searchengine.core.constant.ResponseCode;
import com.volka.searchengine.core.constant.SEARCH_DOMAIN;
import com.volka.searchengine.core.engine.model.Acit;
import com.volka.searchengine.core.engine.model.DocumentModel;
import com.volka.searchengine.core.engine.model.Trdp;
import com.volka.searchengine.core.engine.strategy.index.AcitIndexIndexStrategy;
import com.volka.searchengine.core.engine.strategy.index.TrdpIndexIndexStrategy;
import com.volka.searchengine.core.exception.BizException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
 * 색인 파일 초기화
 *
 * @author volka
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class IndexingManager  {

//    /**
//     * 색인
//     *
//     * @param orgId
//     * @param modelList
//     * @param <T>
//     * @throws BizException
//     * @throws Exception
//     */
//    @SuppressWarnings(value = "unchecked")
//    public <T extends DocumentModel> CompletableFuture<ResponseCode> indexing(String orgId, List<T> modelList) throws BizException, Exception {
//
//        T firstModel = modelList.get(0);
//        SEARCH_DOMAIN domain = firstModel.getDomain();
//        File orgIdxDir = generateIdxDirByOrgId(orgId, domain);
//
//        Directory indexDir = NIOFSDirectory.open(orgIdxDir.toPath());
//        IndexWriter writer = new IndexWriter(indexDir, koreanIndexWriterConfig);
//
//        switch (domain) {
//            case ACIT :
//                if (firstModel instanceof Acit) {
//                    indexStrategyContext.addDocument(new AcitIndexIndexStrategy((List<Acit>) modelList, writer));
//                }
//                break;
//            case TRDP :
//                indexStrategyContext.addDocument(new TrdpIndexIndexStrategy((List<Trdp>) modelList, writer));
//                break;
//            default :
//                break;
//        }
//    }
}
