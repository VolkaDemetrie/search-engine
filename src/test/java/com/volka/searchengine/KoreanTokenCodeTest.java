package com.volka.searchengine;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.ko.KoreanAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.*;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;


public class KoreanTokenCodeTest {

    KoreanAutoComplete koreanAutoComplete;

    class KoreanAutoComplete {

        private Directory index;
        private Analyzer analyzer;


        public KoreanAutoComplete(String indexPath) throws IOException {
            this.index = FSDirectory.open(Paths.get(indexPath));
            this.analyzer = new KoreanAnalyzer();
        }

        public void indexWord(String word) throws IOException {
            try (IndexWriter writer = new IndexWriter(index, new IndexWriterConfig())) {
                String[] jamoArray = extractJamoArray(word);
                for (String jamo : jamoArray) {
                    Document doc = new Document();
                    FieldType fieldType = new FieldType();
                    fieldType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS);
                    fieldType.setStored(true);
                    doc.add(new Field("word", jamo, fieldType));
                    writer.addDocument(doc);
                }
            }
        }

        public String[] autoComplete(String input) throws IOException {
            try (
                    IndexReader reader = DirectoryReader.open(index);
                ) {

                IndexSearcher searcher = new IndexSearcher(reader);
                String normalizedInput = Normalizer.normalize(input, Normalizer.Form.NFD);
                BytesRef term = new BytesRef(normalizedInput.getBytes());

                TopDocs results = searcher.search(new TermQuery(new Term("word", term)), 10);
                String[] suggestions = new String[results.scoreDocs.length];
                int i = 0;
                for (ScoreDoc scoreDoc : results.scoreDocs) {
                    Document doc = searcher.doc(scoreDoc.doc);
                    suggestions[i++] = doc.get("word");
                }
                return suggestions;
            }
        }

        private String[] extractJamoArray(String input) throws IOException {
            List<String> jamoList = new ArrayList<>();
            StringBuilder jamoBuilder = new StringBuilder();

            TokenStream tokenStream = analyzer.tokenStream("word", input);
            tokenStream.reset();
            CharTermAttribute termAttribute = tokenStream.addAttribute(CharTermAttribute.class);

            while (tokenStream.incrementToken()) {
                String term = termAttribute.toString();
                for (char c : term.toCharArray()) {
                    jamoBuilder.append(c);
                    jamoList.add(jamoBuilder.toString());
                }
                jamoBuilder.setLength(0);
            }

            tokenStream.close();

            return jamoList.toArray(new String[0]);
        }
    }

    @Before
    public void init() throws IOException, IOException {
        this.koreanAutoComplete = new KoreanAutoComplete("/home/volka/search_engine/test");
    }


    @Test
    public void test() {
        try {
            KoreanAutoComplete autoComplete = koreanAutoComplete; // 인덱스 디렉터리 경로 설정
            autoComplete.indexWord("자동완성");
            autoComplete.indexWord("자바");
            autoComplete.indexWord("자동차");
            autoComplete.indexWord("자료구조");
            autoComplete.indexWord("검색");

            String input = "ㅈ"; // 사용자 입력
            String[] suggestions = autoComplete.autoComplete(input);
            System.out.println("자동완성 결과:");
            for (String suggestion : suggestions) {
                System.out.println(suggestion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
