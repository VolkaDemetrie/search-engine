package com.volka.searchengine.core.validator;

import com.volka.searchengine.core.annotation.YYYYMM;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * 년월 체크
 *
 * @author volka
 */
@Component
public class YYYYMMValidator extends AnnotationValidator implements ConstraintValidator<YYYYMM, String> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMMdd").withResolverStyle(ResolverStyle.STRICT);


//    @PostConstruct
//    private void init() {
//        formatter.setLenient(false);
//    }

    @Override
    public void initialize(YYYYMM constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (s == null || s.isEmpty()) return false;
            if (s.length() != 6) return false;
            LocalDate.parse(s, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
