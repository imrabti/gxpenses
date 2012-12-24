package com.nuvola.gxpenses.server.business.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailFormatConstraintValidator implements ConstraintValidator<EmailFormat, String> {
    private final String EMAIL_REGEXP = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"+
            "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";

    @Override
    public void initialize(EmailFormat constraintAnnotation) { }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || value.trim().equals("")) return true;
        return value.matches(EMAIL_REGEXP);
    }
}
