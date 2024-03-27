package com.volka.searchengine.core.engine;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 색인 파일 초기화
 *
 * @author volka
 */
@Slf4j
@RequiredArgsConstructor
@Deprecated
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
