package com.volka.searchengine.core.annotation.validation;

import com.volka.searchengine.core.validator.YYYYMMValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * yyyyMMdd 년월일 체크 커스텀 어노테이션
 *
 * @author volka
 */
@Documented
@Constraint(validatedBy = YYYYMMValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface YYYYMM {

    String message() default "년월 형식이 아닙니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
