package com.volka.searchengine.core.annotation.convert;

import com.volka.searchengine.core.engine.model.DocumentModel;
import org.apache.lucene.search.ScoreDoc;

import java.lang.annotation.*;

/**
 * Lucene Document -> DTO
 *
 * @author volka
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DocConvert {
    Class<? extends DocumentModel> model();
}
