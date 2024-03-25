package com.volka.searchengine.tokenizer;

import com.volka.searchengine.core.engine.tokenizer.*;
import org.junit.Test;

public class TokenTest {


    @Test
    public void test() {
        String query = "Hello 세상아 이직하고싶다";

//        String chosung = new ChosungTokenizer().tokenize(query);
//        String joongsung = new JoongsungTokenizer().tokenize(query);
//        String jongsung = new JongsungTokenizer().tokenize(query);
//        String english = new KorToEngTokenizer().tokenize(query);
        JamoToken jamo = new JamoTokenizer().tokenize(query);
//        System.out.println("원문 : [" + query + "]");
//        System.out.println("영문 : [" + english + "]");
//        System.out.println("초성 : [" + chosung + "]");
//        System.out.println("중성 : [" + joongsung + "]");
//        System.out.println("종성 : [" + jongsung + "]");
        System.out.println("자모 : [" + jamo + "]");
    }
}
