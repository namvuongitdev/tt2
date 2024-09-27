package com.example.finally2.mapper.CategoryMapper.response;

import com.example.finally2.dto.categorydto.response.CategoryResponse;
import com.example.finally2.dto.categorydto.response.CategoryResponseWithProducts;
import com.example.finally2.mapper.MapperBase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryResponseMapperExecl extends MapperBase<CategoryResponseWithProducts,CategoryResponse > {
}
