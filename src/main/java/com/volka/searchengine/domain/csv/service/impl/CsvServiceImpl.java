package com.volka.searchengine.domain.csv.service.impl;

import com.volka.searchengine.core.constant.ResponseCode;
import com.volka.searchengine.core.util.CsvLoader;
import com.volka.searchengine.domain.csv.service.CsvService;
import com.volka.searchengine.dto.RestaurantCsvDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.ko.KoreanAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CsvServiceImpl implements CsvService {

    private static final String IDX_PATH = "/home/volka/search_engine/idx";

    private final CsvLoader csvLoader;

    @Override
    public ResponseCode indexingRestaurantCsv() {

        try {
            List<RestaurantCsvDTO> restaurantCsvDTOList = csvLoader.loadRestaurantCsv();

            indexRestaurantInfo(restaurantCsvDTOList);

            search("바다");

            return ResponseCode.SUCCESS;

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseCode.FAIL;
        }
    }

    // 음식점 정보를 색인하는 메소드
    private void indexRestaurantInfo(List<RestaurantCsvDTO> restaurantInfoVos) throws Exception {

        // 파일 시스템에 색인을 한다.
        Directory indexDirectory = FSDirectory.open(Paths.get(IDX_PATH));
        // 한국어 분석을 위한 분석기를 선언한다.
        KoreanAnalyzer analyzer = new KoreanAnalyzer();

        // 색인 생성을 위한 Writer 설정 정보를 구성한다. 음식점 정보를 분석하기 위해 한글 분석기를 설정한다.
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        // 색인 디렉터리와 설정 정보로 writer를 생성한다.
        IndexWriter writer = new IndexWriter(indexDirectory, config);

        // List<RestaurantInfoVo>에서 하나씩 색인한다.
        restaurantInfoVos.forEach(i -> addDocument(i, writer));
        // 색인작업이 끝난 후 writer를 종료한다.
        writer.close();
    }

    // 음식점 정보를 하나씩 색인에 쓰는 메소드
    private void addDocument(RestaurantCsvDTO restaurantInfoVo, IndexWriter writer) {
        // 도큐먼트 생성
        Document doc = new Document();

        try{
            // 음식점 명 필드 추가
            doc.add(new TextField("restaurantName", restaurantInfoVo.getRestaurantName(), Field.Store.YES));
            // category1 필드 추가
            doc.add(new StringField("category1", restaurantInfoVo.getCategory1(), Field.Store.YES));
            // category2 필드 추가
            doc.add(new StringField("category2", restaurantInfoVo.getCategory2(), Field.Store.YES));
            // category3 필드 추가
            doc.add(new StringField("category3", restaurantInfoVo.getCategory3(), Field.Store.YES));
            // region 필드 추가
            doc.add(new StringField("region", restaurantInfoVo.getRegion(), Field.Store.YES));
            // city 필드 추가
            doc.add(new StringField("city", restaurantInfoVo.getCity(), Field.Store.YES));
            // description 필드 추가
            doc.add(new TextField("description", restaurantInfoVo.getDescription(), Field.Store.YES));

            // 색인에 추가
            writer.addDocument(doc);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void search(String word) throws IOException {
        // 색인을 읽을 IndexReader 설정
        IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(IDX_PATH)));

        //읽어들인 색인을 검색하는 IndexSearcher 생성
        IndexSearcher searcher = new IndexSearcher(reader);

        //음식명과 개요에서 특정 단어로 검색하기 위해 쿼리 생성
        TermQuery termQuery = new TermQuery(new Term("restaurantName", word));
        TermQuery termQuery2 = new TermQuery(new Term("description", word));

        //검색어가 음식점명이나 개요에 있을 경우 검색이 되도록 BooleanQuery 생성 및 설정
        BooleanQuery query = new BooleanQuery.Builder()
                .add(new BooleanClause(termQuery, BooleanClause.Occur.SHOULD))
                .add(new BooleanClause(termQuery2, BooleanClause.Occur.SHOULD))
                .build();

        //검색결과 갯수 설정
        int hitPerPage = 10;

        //IndexSearch에 쿼리와 가져올 결과를 설정하고 검색
        TopDocs docs = searcher.search(query, hitPerPage);
        // 검색 결과는 ScoreDoc 배열로 가져올 수 있다.
        ScoreDoc[] hitArr = docs.scoreDocs;

        System.out.println("총 " + hitArr.length + "개 일치");

        for(int i = 0; i < hitArr.length; ++i) {
            int docId = hitArr[i].doc;
            Document d = searcher.doc(docId);
            System.out.println((i + 1) + ". " +"음식점명:"+ d.get("restaurantName")
                    + "\t카테고리1:" + d.get("category1")  + "\t카테고리2:" + d.get("category2") + "\t카테고리:" + d.get("category3")
                    + "\t지역:" + d.get("region")+ "\t도시:" + d.get("city") + "\t설명:" + d.get("description"));
        }

        //검색 완료시 IndexReader를 닫는다
        reader.close();
    }
}
