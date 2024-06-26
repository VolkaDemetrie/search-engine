package com.volka.searchengine.core.validator;

import com.volka.searchengine.core.annotation.validation.SysId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

/**
 * 공백 체크 어노테이션 체크 구현부
 * @author volka
 */
@Component
public class SysIdValidator extends AnnotationValidator implements ConstraintValidator<SysId, String> {

    @Override
    public void initialize(SysId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if (s == null || s.isBlank()) return false;

        return SYSTEM_ID_PATTERN.matcher(s).matches();
    }
}
