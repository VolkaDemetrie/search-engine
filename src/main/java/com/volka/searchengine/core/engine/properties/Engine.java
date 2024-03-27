package com.volka.searchengine.core.engine.properties;


import com.volka.searchengine.core.constant.SEARCH_DOMAIN;

import java.nio.file.Path;
import java.util.Set;

/**
 * 엔진 설정
 *
 * @author volka
 */
public class Engine {
    private Path idxDir;

    public Path getIdxDir() {
        return idxDir;
    }

    public void setIdxDir(Path idxDir) {
        this.idxDir = idxDir;
    }

}
