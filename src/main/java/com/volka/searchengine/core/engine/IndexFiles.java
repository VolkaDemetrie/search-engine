package com.volka.searchengine.core.engine;

import com.volka.searchengine.core.constant.ResponseCode;
import com.volka.searchengine.core.exception.ServiceException;
import com.volka.searchengine.core.properties.EngineFileProperties;
import com.volka.searchengine.core.properties.EngineProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

/**
 * 색인 파일 초기화
 *
 * @author volka
 */
@Slf4j
public class IndexFiles {

    private final EngineProperties engineProperties;


    private IndexFiles(EngineProperties engineProperties) {
        this.engineProperties = engineProperties;
    }

    public void init() {

        try {
            EngineFileProperties fileProperties = this.engineProperties.getFile();
            File doc = new File(fileProperties.getIndexPath());

            Path indexPath = Paths.get(fileProperties.getIndexPath());


            if (!doc.exists() || !doc.canRead()) {
                throw new ServiceException(ResponseCode.FAIL);
            }

            LocalDate now = LocalDate.now();

            Directory dir = FSDirectory.open(indexPath);
            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);

            indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

            IndexWriter indexWriter = new IndexWriter(dir, indexWriterConfig);

            indexWriter.close();
            analyzer.close();
            dir.close();

        } catch (ServiceException e) {
            log.error("IndexFiles init ERROR :: {} : {}", e.getCode(), e.getMsg());
        } catch (Exception e) {
            log.error("IndexFiles init ERROR :: {} : {}", e.getLocalizedMessage(), e.toString());
            throw new ServiceException(ResponseCode.INIT_FAIL, e);
        }
    }
}
