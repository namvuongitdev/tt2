package com.example.finally2.mapper.CategoryMapper.response;

import com.example.finally2.dto.categorydto.response.CategoryResponseExecl;
import com.example.finally2.entity.Category;
import com.example.finally2.mapper.MapperBase;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CategoryResponseMapperExecl extends MapperBase<Category, CategoryResponseExecl> {

}
