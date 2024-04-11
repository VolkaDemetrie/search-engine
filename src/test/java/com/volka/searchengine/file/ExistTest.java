package com.volka.searchengine.file;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class ExistTest {


    @Test
    public void test() {
        Path path = Paths.get("/home/volka/search_engine/idx/0045/ORG17079731939650045/ACIT");
        try (
                Directory idxDir = NIOFSDirectory.open(path);
        ){
            System.out.println(Arrays.toString(idxDir.listAll()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
