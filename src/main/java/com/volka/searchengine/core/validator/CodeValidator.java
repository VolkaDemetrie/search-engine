package com.volka.searchengine.core.validator;

import com.volka.searchengine.core.annotation.Code;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 공통코드 검증 어노테이션
 * @author volka
 */
@Component
public class CodeValidator extends Validator implements ConstraintValidator<Code, String> {

    @Override
    public void initialize(Code constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if(s == null || s.isBlank()) return false;

        return SYSTEM_CODE_PATTERN.matcher(s).matches();
    }

}
