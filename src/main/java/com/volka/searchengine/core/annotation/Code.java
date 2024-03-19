package com.volka.searchengine.core.annotation;

import com.volka.searchengine.core.validator.CodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 공통 코드 아이디 (example = "TRXTYPE001" 등)
 *
 * TODO : 어노테이션 코드 종류별 변수로 prefix, suffix 옵션 받기. :: prefix 단일, suffix [] 로 처리하면 될듯 20231219 volka
 *
 * @author volka
 */
@Documented
@Constraint(validatedBy = CodeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Code {

    String message() default "Code 형식에 맞지 않습니다";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
