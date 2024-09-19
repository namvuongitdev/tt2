package com.example.finally2.mapper.ProductCategoryMapper.response;

import com.example.finally2.dto.productcategorydto.response.ProductCategoryResponse;
import com.example.finally2.entity.ProductCategory;
import com.example.finally2.mapper.CategoryMapper.response.CategoryMapperResponse;
import com.example.finally2.mapper.MapperBase;
import com.example.finally2.mapper.productmapper.response.ProdudctMapperResponseToCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = {CategoryMapperResponse.class , ProdudctMapperResponseToCategory.class})
public interface ProductCategoryMapperResponse extends MapperBase<ProductCategory , ProductCategoryResponse> {

    @Override
    @Mapping(source = "id.category" , target = "categoryResponse")
    @Mapping(source = "id.product" , target = "productResponse")
    ProductCategoryResponse toDTO(ProductCategory productCategory);
}
