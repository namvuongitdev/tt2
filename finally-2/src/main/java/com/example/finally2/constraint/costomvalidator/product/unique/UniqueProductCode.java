package com.example.finally2.constraint.costomvalidator.product.unique;

import com.example.finally2.constraint.costomvalidator.product.unique.ProductCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ProductCodeValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueProductCode {
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
