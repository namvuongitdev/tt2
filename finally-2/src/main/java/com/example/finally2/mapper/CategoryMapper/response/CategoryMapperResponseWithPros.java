package com.example.finally2.mapper.CategoryMapper.response;

import com.example.finally2.dto.categorydto.response.CategoryResponseWithProducts;
import com.example.finally2.entity.Category;
import com.example.finally2.mapper.MapperBase;
import com.example.finally2.mapper.ProductCategoryMapper.response.ProductCategoryMapperResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = {ProductCategoryMapperResponse.class})
public interface CategoryMapperResponseWithPros extends MapperBase<Category , CategoryResponseWithProducts> {

    @Override
    @Mapping(target = "productCategoryResponses" , source = "productCategories")
    CategoryResponseWithProducts toDTO(Category category);
}
