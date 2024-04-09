package com.volka.searchengine.core.annotation.convert;

import com.volka.searchengine.core.engine.model.DocumentModel;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 */
@Documented
@Retention(RetentionPolicy.CLASS)
public @interface DocConvert {
    Class<? extends DocumentModel> model();
}
