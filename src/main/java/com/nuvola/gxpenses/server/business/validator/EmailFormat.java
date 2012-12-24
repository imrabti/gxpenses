package com.nuvola.gxpenses.server.business.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmailFormatConstraintValidator.class)
public @interface EmailFormat {
    String message() default "Email format error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
