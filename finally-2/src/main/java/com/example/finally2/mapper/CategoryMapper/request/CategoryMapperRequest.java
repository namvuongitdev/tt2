package com.example.finally2.mapper.CategoryMapper.request;

import com.example.finally2.dto.categorydto.request.CategoryRequest;
import com.example.finally2.entity.Category;
import com.example.finally2.mapper.MapperBase;
import com.example.finally2.util.data.ValueDefault;
import com.example.finally2.util.date.DateUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring" , imports = {DateUtil.class , ValueDefault.class})
public interface CategoryMapperRequest extends MapperBase<Category , CategoryRequest> {

    @Override
    @Mapping(target = "createAt" , expression = "java(DateUtil.getCurrentDate())")
    @Mapping(target = "modifiedDate" , expression = "java(DateUtil.getCurrentDate())")
    @Mapping(target = "modifiedBy" , expression = "java(ValueDefault.modifiedBy)")
    @Mapping(target = "createBy" , expression = "java(ValueDefault.modifiedBy)")
    Category toEntity(CategoryRequest request);

    @Mapping(target = "modifiedDate" , expression = "java(DateUtil.getCurrentDate())")
    @Mapping(target = "modifiedBy" , expression = "java(ValueDefault.modifiedBy)")
    @Mapping(target = "createBy" , expression = "java(ValueDefault.modifiedBy)")
    Category updateToEntity(CategoryRequest request , @MappingTarget Category category);
}
