package com.volka.searchengine.core.annotation;

import com.volka.searchengine.core.validator.SysIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * 시스템 코드 아이디 (example = "ORG0231835..." 등)
 *
 * @author volka
 */
@Documented
@Constraint(validatedBy = SysIdValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SysId {

    String message() default "System ID형식에 맞지 않습니다";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
