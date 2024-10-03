package com.example.finally2.constraint.costomvalidator.category.unique;

import com.example.finally2.repository.CategoryRepository;
import com.example.finally2.util.status.CategoryStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class CategoryCodeValidator implements ConstraintValidator<UniqueCategoryCode, String> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !categoryRepository.existsByCategoryCodeAndStatus(value , CategoryStatus.ACTIVE);
    }

}
