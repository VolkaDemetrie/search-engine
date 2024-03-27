package com.volka.searchengine.tokenizer;

import com.volka.searchengine.core.engine.tokenizer.*;
import org.junit.Test;

public class TokenTest {


    @Test
    public void test() {
        String query = "Hello 세상아 이직하고싶다";

        JamoToken jamo = new JamoTokenizer().tokenize(query);
        System.out.println("자모 : [" + jamo + "]");
    }
}
