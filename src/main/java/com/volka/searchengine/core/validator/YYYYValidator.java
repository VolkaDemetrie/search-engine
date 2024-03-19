package com.volka.searchengine.core.validator;

import com.volka.searchengine.core.annotation.YYYY;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.SimpleDateFormat;

/**
 * 연도 체크
 *
 * @author volka
 */
@Component
public class YYYYValidator extends Validator implements ConstraintValidator<YYYY, String> {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy");

    @PostConstruct
    private void init() {
        formatter.setLenient(false);
    }

    @Override
    public void initialize(YYYY constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            // null check 는 별도로 여기서는 패턴만 체크
            if (s == null || s.isEmpty()) return false;

            formatter.parse(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
