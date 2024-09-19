package com.example.finally2.constraint.costomvalidator.category.exist;

import com.example.finally2.entity.Category;
import com.example.finally2.repository.CategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class CategoryExistValidator implements ConstraintValidator<CategoryExist , List<Long>> {

   @Autowired
   private CategoryRepository categoryRepository;

    @Override
    public boolean isValid(List<Long> value, ConstraintValidatorContext context) {
     List<Category> categories =  categoryRepository.findAllById(value);
      if(categories.isEmpty() || value.size() != categories.size()){
          return false;
      }
        return true;
    }
}
