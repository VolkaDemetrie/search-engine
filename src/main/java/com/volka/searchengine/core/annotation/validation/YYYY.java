package com.volka.searchengine.core.annotation.validation;

import com.volka.searchengine.core.validator.YYYYValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * yyyyMMdd 년월일 체크 커스텀 어노테이션
 *
 * @author volka
 */
@Documented
@Constraint(validatedBy = YYYYValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface YYYY {

    String message() default "연도 형식이 아닙니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
