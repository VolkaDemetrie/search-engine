package com.volka.searchengine.core.annotation;

import com.volka.searchengine.core.validator.YYYYMMDDValidator;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * yyyyMMdd 년월일 체크 커스텀 어노테이션
 *
 * @author volka
 */
@Documented
@Constraint(validatedBy = YYYYMMDDValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface YYYYMMDD {

    String message() default "년월일 형식이 아닙니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
