package com.example.finally2.constraint.costomvalidator.product.unique;

import com.example.finally2.repository.ProductRepository;
import com.example.finally2.util.status.ProductStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductCodeValidator implements ConstraintValidator<UniqueProductCode, String> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !productRepository.existsByProductCodeAndStatus(value , ProductStatus.ACTIVE);
    }
}
